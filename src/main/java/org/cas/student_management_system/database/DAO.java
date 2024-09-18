package org.cas.student_management_system.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DAO {
    protected  Connection connection;
    protected boolean isTest;

    protected DAO(String dbPath) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        this.isTest = false;
    }

    protected DAO(String dbPath, boolean isTest) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        this.isTest = isTest;
    }

    public Connection getConnection() {
        return connection;
    }

    public void shutdown() throws SQLException {
        connection.close();
    }

    protected String readFileInSourceResources(String filename) throws IOException {
        if (isTest) return readFile(getTestFile(filename));
        else return readFile(getFile(filename));
    }

    private Path getFile(String filename) {
        return Paths.get("src", "main", "resources", "sql", filename);
    }

    private Path getTestFile(String filename) {
        return Paths.get("src", "test", "resources", "sql", filename);
    }

    protected String readFile(Path path) throws IOException {
        List<String> contents = getSQLStatements(path);
        return contents.stream().map(String::trim).collect(Collectors.joining(" "));
    }

    protected static List<String> getSQLStatements(Path path) throws IOException {
        return Files.readAllLines(path);
    }

}
