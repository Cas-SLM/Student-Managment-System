package org.cas.student_management_system.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DataManipulator extends DAO {

    public DataManipulator() throws SQLException{
        super("TestDB.db", true);
    }

//    public DataAccess(String dbUrl, String dbUser, String dbPassword) {}
    public DataManipulator(String dbPath) throws SQLException {
        super(dbPath);
    }

    public void initialiseDatabase() throws SQLException {
    }
}
