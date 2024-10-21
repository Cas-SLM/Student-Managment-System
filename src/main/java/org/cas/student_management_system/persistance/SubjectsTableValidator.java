package org.cas.student_management_system.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectsTableValidator extends AbstractTableValidator{

    public SubjectsTableValidator() {
        super("subjects");
    }

    /*@Override
    public boolean validateColumns(ResultSet rs) throws SQLException {
        while (rs.next()) {
            if (!isColumnValid(rs.getString("COLUMN_NAME"), rs)) return false;
        }
        return true;
    }*/

    @Override
    public boolean validatePrimaryKey(ResultSet rs) throws SQLException {
        if (!rs.next()) return false;
        return rs.getString("COLUMN_NAME").equals("code");
    }

    @Override
    public boolean validateForeignKey(ResultSet rs) throws SQLException {
        return !rs.next();
    }

    @Override
    protected boolean isColumnValid(String column, ResultSet rs) throws SQLException {
        return switch (column) {
            case "code" -> rs.getString("TYPE_NAME").equals("VARCHAR") &&
                    rs.getString("IS_NULLABLE").equals("NO");
            case "name" -> rs.getString("TYPE_NAME").equals("TEXT") &&
                    rs.getString("IS_NULLABLE").equals("NO");
            default -> false;
        };
    }
}
