/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package attendance.mark;

import connection.MySQL;
import gui.attendance.NameButtonTag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author sava
 */
public class AttendanceMakingProcess {

    public void markAttendance(List<NameButtonTag> buttons, int class_id) {
         LocalDate selectedDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        selectedDate.format(formatter);
        
        try (Connection conn = MySQL.createConnection()) {
            // Step 1: Ensure date exists
            int dateId = getOrCreateDateId(conn, selectedDate);

            // Step 2: Loop through buttons
            String sql = "INSERT INTO attendance (student_id, date_id, status,class_id) VALUES (?, ?, ?, ?) "
                    + "ON DUPLICATE KEY UPDATE status = VALUES(status)";
            PreparedStatement ps = conn.prepareStatement(sql);

            for (NameButtonTag btn : buttons) {
                int studentId = btn.getId(); // Assume your custom button has this method
                int status = btn.isSelected() ? 1 : 0;

                ps.setInt(1, studentId);
                ps.setInt(2, dateId);
                ps.setInt(3, status);
                ps.setInt(4, class_id);
                ps.addBatch();
            }

            ps.executeBatch();
            JOptionPane.showMessageDialog(null, "Attendance marked successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private int getOrCreateDateId(Connection conn, LocalDate date) throws SQLException {
        String selectSql = "SELECT id FROM date WHERE date = ?";
        PreparedStatement selectPs = conn.prepareStatement(selectSql);
        selectPs.setDate(1, java.sql.Date.valueOf(date));
        ResultSet rs = selectPs.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");
        } else {
            String insertSql = "INSERT INTO date (date) VALUES (?)";
            PreparedStatement insertPs = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            insertPs.setDate(1, java.sql.Date.valueOf(date));
            insertPs.executeUpdate();
            ResultSet keys = insertPs.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve date_id.");
            }
        }
    }
}
