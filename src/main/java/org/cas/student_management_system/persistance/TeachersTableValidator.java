package org.cas.student_management_system.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeachersTableValidator extends AbstractTableValidator {

    public TeachersTableValidator() {
        super("Teachers");
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
        return rs.getString("COLUMN_NAME").equals("id");
    }

    @Override
    public boolean validateForeignKey(ResultSet rs) throws SQLException {
        return !rs.next();
    }

    @Override
    protected boolean isColumnValid(String column, ResultSet rs) throws SQLException{
        return switch (column) {
            case "id" -> rs.getString("TYPE_NAME").equals("INTEGER") &&
                    rs.getString("IS_NULLABLE").equals("NO") &&
                    rs.getString("IS_AUTOINCREMENT").equals("YES");
            case "name" -> rs.getString("TYPE_NAME").equals("TEXT") &&
                    rs.getString("IS_NULLABLE").equals("NO");
            case "email" -> rs.getString("TYPE_NAME").equals("VARCHAR") &&
                    rs.getString("IS_NULLABLE").equals("NO");
            case "date_of_birth" -> rs.getString("TYPE_NAME").equals("DATE") &&
                    rs.getString("IS_NULLABLE").equals("NO");
            default -> false;
        };
    }
}
