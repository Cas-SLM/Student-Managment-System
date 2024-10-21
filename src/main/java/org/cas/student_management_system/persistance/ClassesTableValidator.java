package org.cas.student_management_system.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassesTableValidator extends AbstractTableValidator {

    public ClassesTableValidator() {
        super("Classes");
    }

    @Override
    public boolean validatePrimaryKey(ResultSet rs) throws SQLException {
        if (!rs.next()) return false;
        return rs.getString("COLUMN_NAME").equals("code");
    }

    @Override
    public boolean validateForeignKey(ResultSet rs) throws SQLException {
        boolean validTeacherForeignKey = false;
        boolean validSubjectsForeignKey = false;
        while (rs.next()) {
            switch (rs.getString("PKTABLE_NAME")) {
                case "Teachers":
                    validTeacherForeignKey = rs.getString("PKCOLUMN_NAME").equals("id") &&
                            rs.getString("FKTABLE_NAME").equals("Classes") &&
                            rs.getString("FKCOLUMN_NAME").equals("teacher_id");
                    break;
                case "Subjects":
                    validSubjectsForeignKey = rs.getString("PKCOLUMN_NAME").equals("code") &&
                            rs.getString("FKTABLE_NAME").equals("Classes") &&
                            rs.getString("FKCOLUMN_NAME").equals("subject_code");
                    break;
                default:
                    return false;

            }
        }
        return validTeacherForeignKey && validSubjectsForeignKey;
    }

    @Override
    protected boolean isColumnValid(String column, ResultSet rs) throws SQLException {
        return switch (column) {
            case "id" -> rs.getString("TYPE_NAME").equals("INTEGER") &&
                    rs.getString("IS_NULLABLE").equals("NO") &&
                    rs.getString("IS_AUTOINCREMENT").equals("YES");
            case "teacher_id" -> rs.getString("TYPE_NAME").equals("INTEGER") &&
                    rs.getString("IS_NULLABLE").equals("NO");
            case "subject_code" -> rs.getString("TYPE_NAME").equals("VARCHAR") &&
                    rs.getString("IS_NULLABLE").equals("NO");
            default -> false;
        };
    }
}
