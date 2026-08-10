package com.campus.optimizer.db;

import com.campus.optimizer.db.models.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SchemaSqlTest {

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        Path dbFile = tempDir.resolve("schema_test.db");
        DatabaseConnection.setDbPath(dbFile.toString());
    }

    @AfterEach
    void tearDown() {
        DatabaseConnection.close();
    }

    @Test
    void initializeSchema_realSchemaFile_noExceptions() throws IOException {
        Path schemaPath = Path.of("schema.sql");
        if (!Files.exists(schemaPath)) {
            schemaPath = Path.of("../schema.sql");
        }
        assertTrue(Files.exists(schemaPath), "schema.sql must exist at project root");
        final Path finalSchemaPath = schemaPath;
        assertDoesNotThrow(() -> DatabaseConnection.initializeSchema(finalSchemaPath.toString()));
    }

    @Test
    void initializeSchema_realSchemaFile_tablesAndIndexesCreated() throws IOException {
        Path schemaPath = Path.of("schema.sql");
        if (!Files.exists(schemaPath)) {
            schemaPath = Path.of("../schema.sql");
        }
        final Path finalSchemaPath = schemaPath;
        DatabaseConnection.initializeSchema(finalSchemaPath.toString());

        java.sql.Connection conn = DatabaseConnection.getConnection();
        try (var ps = conn.prepareStatement(
                "SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%' ORDER BY name")) {
            try (var rs = ps.executeQuery()) {
                var tables = new java.util.ArrayList<String>();
                while (rs.next()) tables.add(rs.getString(1));
                assertEquals(6, tables.size(), "Expected 6 tables per PRD Section 7");
                assertTrue(tables.contains("locations"));
                assertTrue(tables.contains("roads"));
                assertTrue(tables.contains("service_requests"));
                assertTrue(tables.contains("resources"));
                assertTrue(tables.contains("algorithm_runs"));
                assertTrue(tables.contains("audit_events"));
            }
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }

        try (var ps = conn.prepareStatement(
                "SELECT name FROM sqlite_master WHERE type='index' AND name LIKE 'idx_%' ORDER BY name")) {
            try (var rs = ps.executeQuery()) {
                var indexes = new java.util.ArrayList<String>();
                while (rs.next()) indexes.add(rs.getString(1));
                assertEquals(4, indexes.size(), "Expected 4 idx_* indexes at bottom of schema.sql");
                assertTrue(indexes.contains("idx_roads_from"));
                assertTrue(indexes.contains("idx_roads_to"));
                assertTrue(indexes.contains("idx_requests_status"));
                assertTrue(indexes.contains("idx_requests_urgency"));
            }
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void initializeSchema_realSchemaFile_canInsertAndFKsEnforced() throws IOException {
        Path schemaPath = Path.of("schema.sql");
        if (!Files.exists(schemaPath)) {
            schemaPath = Path.of("../schema.sql");
        }
        final Path finalSchemaPath = schemaPath;
        DatabaseConnection.initializeSchema(finalSchemaPath.toString());
        java.sql.Connection conn = DatabaseConnection.getConnection();

        try (var ps = conn.prepareStatement(
                "INSERT INTO locations (locationId, name, area, type, coordinates) VALUES (?,?,?,?,?)")) {
            ps.setString(1, "L999");
            ps.setString(2, "Test Loc");
            ps.setString(3, "Test Area");
            ps.setString(4, "Academic");
            ps.setString(5, "0;0");
            assertEquals(1, ps.executeUpdate());
        } catch (java.sql.SQLException e) {
            fail("Insert into locations should succeed, got: " + e.getMessage());
        }

        try (var ps = conn.prepareStatement(
                "INSERT INTO roads (fromLocationId, toLocationId, distance, travelTime, roadConditionWeight) VALUES (?,?,?,?,?)")) {
            ps.setString(1, "L999");
            ps.setString(2, "L000_DOES_NOT_EXIST");
            ps.setDouble(3, 1.0);
            ps.setDouble(4, 2.0);
            ps.setInt(5, 3);
            ps.executeUpdate();
            fail("Expected FK violation for unknown toLocationId");
        } catch (java.sql.SQLException expected) {
            // good - FK enforcement is on (PRAGMA foreign_keys was in schema.sql)
        }
    }
}
