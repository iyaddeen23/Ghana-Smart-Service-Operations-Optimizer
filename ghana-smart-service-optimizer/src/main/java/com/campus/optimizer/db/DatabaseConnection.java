package com.campus.optimizer.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Owns the single JDBC connection to the SQLite database file and knows how
 * to (re)build the schema from schema.sql. All other classes go through
 * getConnection() rather than opening their own connections.
 */
public final class DatabaseConnection {

    private static Connection connection;
    private static String dbPath = "campus_optimizer.db";

    private DatabaseConnection() { }

    /** Allows tests to point at a temp/in-memory DB file before first connect(). */
    public static void setDbPath(String path) {
        dbPath = path;
        connection = null; // force reconnect on next getConnection()
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
                try (Statement st = connection.createStatement()) {
                    st.execute("PRAGMA foreign_keys = ON;");
                }
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException("Failed to open SQLite connection at " + dbPath, e);
            }
        }
        return connection;
    }

    /** Executes schema.sql against the current connection, dropping/recreating all tables. */
    public static void initializeSchema(String schemaFilePath) {
        Connection conn = getConnection();
        String script;
        try {
            script = Files.readString(Paths.get(schemaFilePath));
        } catch (IOException e) {
            throw new RuntimeException("Could not read schema file: " + schemaFilePath, e);
        }

        String cleaned = stripComments(script);
        try (Statement st = conn.createStatement()) {
            for (String rawStatement : cleaned.split(";")) {
                String sql = rawStatement.trim();
                if (!sql.isEmpty()) {
                    st.execute(sql);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize schema from " + schemaFilePath, e);
        }
    }

    private static String stripComments(String sqlBlock) {
        StringBuilder cleaned = new StringBuilder();
        for (String line : sqlBlock.split("\n")) {
            String trimmed = line.trim();
            if (trimmed.startsWith("--")) {
                continue;
            }
            int dashDash = line.indexOf("--");
            String sqlPart = (dashDash == -1) ? line : line.substring(0, dashDash);
            cleaned.append(sqlPart).append("\n");
        }
        return cleaned.toString();
    }

    public static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // best-effort close on shutdown
            } finally {
                connection = null;
            }
        }
    }

    public static Path resolveNear(Class<?> anchor, String relativePath) {
        return Paths.get(relativePath).toAbsolutePath();
    }
}
