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

class DataLoaderTest {

    @TempDir
    Path tempDir;

    private Path schemaPath;

    @BeforeEach
    void setUp() throws IOException {
        // Point at a throwaway DB file per test run, then build the schema from the real schema.sql.
        Path dbFile = tempDir.resolve("test.db");
        DatabaseConnection.setDbPath(dbFile.toString());

        schemaPath = Path.of("schema.sql");
        if (!Files.exists(schemaPath)) {
            // allow running tests from a different working directory
            schemaPath = Path.of("../schema.sql");
        }
        DatabaseConnection.initializeSchema(schemaPath.toString());
    }

    @AfterEach
    void tearDown() {
        DatabaseConnection.close();
    }

    private Path writeCsv(String filename, String content) throws IOException {
        Path file = tempDir.resolve(filename);
        Files.writeString(file, content);
        return file;
    }

    @Test
    void loadLocations_validRowsAllPersisted() throws IOException {
        Path csv = writeCsv("locations.csv",
                "locationId,name,area,type,coordinates\n" +
                "L001,Balme Library,Central Campus,Academic,5.65;-0.187\n" +
                "L002,Commonwealth Hall,Commonwealth Precinct,Hostel,5.651;-0.186\n");

        DataLoader loader = new DataLoader();
        DataLoader.LoadReport report = loader.loadLocations(csv.toString());

        assertEquals(2, report.totalRows);
        assertEquals(2, report.validRows);
        assertEquals(0, report.errors.size());
        assertEquals(2, loader.getLocations().size());

        Location found = loader.getLocationIndex().get("L001");
        assertNotNull(found);
        assertEquals("Balme Library", found.getName());
    }

    @Test
    void loadLocations_rejectsRowMissingRequiredField() throws IOException {
        Path csv = writeCsv("locations.csv",
                "locationId,name,area,type,coordinates\n" +
                "L001,,Central Campus,Academic,5.65;-0.187\n"); // missing name

        DataLoader loader = new DataLoader();
        DataLoader.LoadReport report = loader.loadLocations(csv.toString());

        assertEquals(1, report.totalRows);
        assertEquals(0, report.validRows);
        assertEquals(1, report.errors.size());
    }

    @Test
    void loadLocations_rejectsDuplicateLocationId() throws IOException {
        Path csv = writeCsv("locations.csv",
                "locationId,name,area,type,coordinates\n" +
                "L001,Balme Library,Central Campus,Academic,5.65;-0.187\n" +
                "L001,Duplicate Entry,Central Campus,Academic,5.65;-0.187\n");

        DataLoader loader = new DataLoader();
        DataLoader.LoadReport report = loader.loadLocations(csv.toString());

        assertEquals(2, report.totalRows);
        assertEquals(1, report.validRows);
        assertEquals(1, report.errors.size());
    }

    @Test
    void loadRoads_rejectsUnknownLocationReference() throws IOException {
        Path locCsv = writeCsv("locations.csv",
                "locationId,name,area,type,coordinates\n" +
                "L001,Balme Library,Central Campus,Academic,5.65;-0.187\n");
        Path roadCsv = writeCsv("roads.csv",
                "fromLocationId,toLocationId,distance,travelTime,roadConditionWeight\n" +
                "L001,L999,1.2,5.0,2\n"); // L999 does not exist -> referential integrity violation

        DataLoader loader = new DataLoader();
        loader.loadLocations(locCsv.toString());
        DataLoader.LoadReport report = loader.loadRoads(roadCsv.toString());

        assertEquals(1, report.totalRows);
        assertEquals(0, report.validRows);
        assertEquals(1, report.errors.size());
    }

    @Test
    void loadRoads_validRowsPersistedAfterLocationsLoaded() throws IOException {
        Path locCsv = writeCsv("locations.csv",
                "locationId,name,area,type,coordinates\n" +
                "L001,Balme Library,Central Campus,Academic,5.65;-0.187\n" +
                "L002,Commonwealth Hall,Commonwealth Precinct,Hostel,5.651;-0.186\n");
        Path roadCsv = writeCsv("roads.csv",
                "fromLocationId,toLocationId,distance,travelTime,roadConditionWeight\n" +
                "L001,L002,1.2,5.0,2\n");

        DataLoader loader = new DataLoader();
        loader.loadLocations(locCsv.toString());
        DataLoader.LoadReport report = loader.loadRoads(roadCsv.toString());

        assertEquals(1, report.validRows);
        assertEquals(1, loader.getRoads().size());
    }

    @Test
    void edgeCase_emptyCsvProducesEmptyReport() throws IOException {
        Path csv = writeCsv("locations.csv", "locationId,name,area,type,coordinates\n");
        DataLoader loader = new DataLoader();
        DataLoader.LoadReport report = loader.loadLocations(csv.toString());
        assertEquals(0, report.totalRows);
        assertEquals(0, report.validRows);
    }
}
