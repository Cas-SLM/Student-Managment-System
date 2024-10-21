package org.cas.student_management_system.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GradesTableValidator extends AbstractTableValidator{

    public GradesTableValidator() {
        super("Grades");
    }

    @Override
    public boolean validatePrimaryKey(ResultSet rs) throws SQLException {
        boolean validStudentKey = false;
        boolean validSubjectKey = false;
        while (rs.next()) {
            switch (rs.getString("COLUMN_NAME")) {
                case "student_id":
                    validStudentKey = true;
                    break;
                case "subject_id":
                    validSubjectKey = true;
                    break;
                default:
                    return false;
            }
        }
        return validStudentKey && validSubjectKey;
    }

    @Override
    public boolean validateForeignKey(ResultSet rs) throws SQLException {
        boolean validStudentForeignKey = false;
        boolean validClassesForeignKey = false;
        boolean validSubjectForeignKey = false;
        while (rs.next()) {
            switch (rs.getString("PKTABLE_NAME")) {
                case "Students":
                    validStudentForeignKey = rs.getString("PKCOLUMN_NAME").equals("id") &&
                        rs.getString("FKTABLE_NAME").equals("Grades") &&
                        rs.getString("FKCOLUMN_NAME").equals("student_id");
                    break;
                case "Classes":
                    validClassesForeignKey = rs.getString("PKCOLUMN_NAME").equals("id") &&
                            rs.getString("FKTABLE_NAME").equals("Grades") &&
                            rs.getString("FKCOLUMN_NAME").equals("class_id");
                    break;
                case "Subjects":
                    validSubjectForeignKey = rs.getString("PKCOLUMN_NAME").equals("code") &&
                            rs.getString("FKTABLE_NAME").equals("Grades") &&
                            rs.getString("FKCOLUMN_NAME").equals("subject_code");
                    break;
                default:
                    return false;
            }
        }
        return validStudentForeignKey && validClassesForeignKey && validSubjectForeignKey;
    }

    @Override
    protected boolean isColumnValid(String column, ResultSet rs) throws SQLException {
        return switch (column) {
            case "student_id" -> rs.getString("TYPE_NAME").equals("INTEGER") &&
                    rs.getString("IS_NULLABLE").equals("NO");
            case "subject_code" -> rs.getString("TYPE_NAME").equals("VARCHAR") &&
                    rs.getString("IS_NULLABLE").equals("NO");
            case "grade" -> rs.getString("TYPE_NAME").equals("INTEGER") &&
                    rs.getString("IS_NULLABLE").equals("NO") &&
                    rs.getString("COLUMN_DEF").equals("0");
            default -> false;
        };
    }
}
