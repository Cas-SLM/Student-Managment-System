package org.cas.student_management_system.persistance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    protected String[] readFilesInSourceResources(String... filenames) throws IOException{
        ArrayList<String> contents = new ArrayList<>();
        for (String filename : filenames) {
            contents.addAll(Arrays.stream(readFileInSourceResources(filename)).toList());
        }

        return contents.toArray(String[]::new);
    }
    protected String[] readFileInSourceResources(String filename) throws IOException {
        if (isTest) return readFile(getTestFile(filename));
        else return readFile(getFile(filename));
    }

    private Path getFile(String filename) {
        return Paths.get("src", "main", "resources", "sql", filename);
    }

    private Path getTestFile(String filename) {
        return Paths.get("src", "test", "resources", "sql", filename);
    }

    protected String[] readFile(Path path) throws IOException {
        List<String> contents = getSQLStatements(path);
        return contents.stream().map(String::trim).toArray(String[]::new);
    }

    protected static List<String> getSQLStatements(Path path) throws IOException {
        return Files.readAllLines(path);
    }

    protected PreparedStatement[] getPreparedStatements(String... statements) throws SQLException{
        ArrayList<PreparedStatement> preparedStatements = new ArrayList<>();
        for (String sql : statements) {
            preparedStatements.add(getPreparedStatement(sql));
        }
        return preparedStatements.toArray(PreparedStatement[]::new);
    }

    protected PreparedStatement getPreparedStatement(String statement) throws SQLException {
        return connection.prepareStatement(statement);
    }

    public boolean executeSql(PreparedStatement statement) {
        try {
            int result = statement.executeUpdate();
            return result == 0;
        } catch (SQLException e) {
            return false;
        }
    }

}
