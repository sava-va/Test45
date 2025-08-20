/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.timetable;

/**
 *
 * @author sava
 */
import connection.MySQL1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import java.util.Map;
import javax.swing.JOptionPane;

public class TimeTableDataLoader {

    private final TimeTableCombos timeTableCombos;

    public TimeTableDataLoader(TimeTableCombos combos) {
        this.timeTableCombos = combos;
    }

    public void loadSelectedData() {
        // Get selected names from combo boxes
        String selectedTeacher = (String) timeTableCombos.getTeacherCombo().getSelectedItem();
        String selectedSubject = (String) timeTableCombos.getSubjectCombo().getSelectedItem();
        String selectedClass = (String) timeTableCombos.getClassCombo().getSelectedItem();
        String selectedDate = (String) timeTableCombos.getDateCombo().getSelectedItem();

        // Map names back to IDs
        String teacherNic = getIdFromMap(timeTableCombos.getTeacherMap(), selectedTeacher);
        String subjectId = getIdFromMap(timeTableCombos.getSubjectMap(), selectedSubject);
        String classId = getIdFromMap(timeTableCombos.getClassMap(), selectedClass);
        String dateId = getIdFromMap(timeTableCombos.getDaysMap(), selectedDate);

        String sql = "INSERT INTO time_table (class_name_id, teacher_nic, subject_id, time_table_days_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = MySQL1.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, classId);
            stmt.setString(2, teacherNic);
            stmt.setString(3, subjectId);
            stmt.setString(4, dateId);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null,"Time table entry inserted successfully.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String getIdFromMap(Map<String, String> map, String value) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null; // or throw exception if preferred
    }

    private Connection createConnection() throws SQLException {
        return MySQL1.createConnection();
    }
}
