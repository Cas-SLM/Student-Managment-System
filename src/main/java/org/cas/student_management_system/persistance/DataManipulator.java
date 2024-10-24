package org.cas.student_management_system.persistance;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataManipulator extends DAO {

    public DataManipulator() throws SQLException{
        super("TestDB.db", true);
    }

    public DataManipulator(String dbPath) throws SQLException {
        super(dbPath);
    }

    public boolean initialiseDatabase() throws SQLException {
        try {
            return executeBatch(getPreparedStatements(readFilesInSourceResources("create_students.sql",
                    "create_teachers.sql",
                    "create_subjects.sql",
                    "create_classes.sql",
                    "create_grades.sql")));
        } catch (IOException e) {
            return false;
        }
    }

    public boolean executeSql(String filename) throws IOException, SQLException {
        String[] sql = readFileInSourceResources(filename);
        return executeBatch(getPreparedStatements(sql));
    }

    public boolean executeBatch(PreparedStatement... batch) {
        for (PreparedStatement statement : batch) {
            if (!executeSql(statement)) return false;
        }
        return true;
    }
}
