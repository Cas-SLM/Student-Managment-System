package org.cas.student_management_system.database;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractTableValidator {
    private final String tableName;

//    public abstract boolean validateColumns(ResultSet rs) throws SQLException;
    public abstract boolean validatePrimaryKey(ResultSet rs) throws SQLException;
    public abstract boolean validateForeignKey(ResultSet rs) throws SQLException;
    protected abstract boolean isColumnValid(String column, ResultSet rs) throws SQLException;

    AbstractTableValidator(String tableName) {
        this.tableName = tableName;
    }

    protected boolean validateColumns(ResultSet rs) throws SQLException {
        while (rs.next()) {
            if (!isColumnValid(rs.getString("COLUMN_NAME"), rs)) return false;
        }
        return true;
    }

    private boolean verifyTableExists(DatabaseMetaData metaData) throws SQLException {
        try (final ResultSet resultSet = metaData.getTables(null, "sqlite_master", tableName, new String[]{"TABLE"})) {
            if (resultSet.next()) return true;
        }
        return false;
    }

    private boolean verifyTableColumns(DatabaseMetaData metaData) throws SQLException {
        try (final ResultSet resultSet = metaData.getColumns(null, "sqlite_master", tableName, null)) {
            if (validateColumns(resultSet)) return true;
        }
        return false;
    }

    private boolean verifyPrimaryKeys(DatabaseMetaData metaData) throws SQLException {
        try (final ResultSet resultSet = metaData.getPrimaryKeys(null, "sqlite_master", tableName)) {
            if (validatePrimaryKey(resultSet)) return true;
        }
        return false;
    }

    private boolean verifyForeignKeys(DatabaseMetaData metaData) throws SQLException {
        try (final ResultSet resultSet = metaData.getImportedKeys(null, "sqlite_master", tableName)) {
            if (validateForeignKey(resultSet)) return true;
        }
        return false;
    }

    public boolean validata(DatabaseMetaData metaData) throws SQLException {
        return verifyTableExists(metaData) &&
                verifyTableColumns(metaData) &&
                verifyPrimaryKeys(metaData) &&
                verifyForeignKeys(metaData);
    }


}
