package org.cas.student_management_system.persistance;

import java.sql.SQLException;

public class DatabaseManager extends DAO{

    public DatabaseManager() throws SQLException {
        super("TestDB.db", true);
    }

    public DatabaseManager(String dbPath) throws SQLException {
        super(dbPath);
    }
}
