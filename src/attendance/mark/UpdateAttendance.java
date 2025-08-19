/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package attendance.mark;

import connection.MySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.jfree.chart.plot.dial.StandardDialRange;

/**
 *
 * @author sava
 */
public class UpdateAttendance {

    HashMap<String, Integer> studentMap = new HashMap();
    HashMap<String, Integer> dateMap = new HashMap();
    JComboBox dateCombo;
    JComboBox studentCombo;
    JComboBox statusCombo;

    public UpdateAttendance(JComboBox dateCombo, JComboBox studentCombo, JComboBox statusCombo) {
        this.dateCombo = dateCombo;
        this.studentCombo = studentCombo;
        this.statusCombo = statusCombo;
        statusCombo.addItem("Present");
        statusCombo.addItem("Absent");
    }

    public void loadComboData() {

        try  {
             Connection conn = MySQL.createConnection();
            // Load dates
            ResultSet rsDate = conn.createStatement().executeQuery("SELECT id, date FROM date");
            while (rsDate.next()) {
                String dateStr = rsDate.getDate("date").toString();
                dateCombo.addItem(dateStr);
                dateMap.put(dateStr, rsDate.getInt("id"));
            }

            // Load students
            ResultSet rsStudent = conn.createStatement().executeQuery("SELECT id, fname, lname FROM student");
            while (rsStudent.next()) {
                String name = rsStudent.getString("fname") + " " + rsStudent.getString("lname");
                studentCombo.addItem(name);
                studentMap.put(name, rsStudent.getInt("id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data: " + ex.getMessage());
        }
    }

    public void updateAttendance() {
        String selectedDate = (String) dateCombo.getSelectedItem();
        String selectedStudent = (String) studentCombo.getSelectedItem();
        String selectedStatus = (String) statusCombo.getSelectedItem();

        int dateId = dateMap.get(selectedDate);
        int studentId = studentMap.get(selectedStudent);
        int status = selectedStatus.equals("Present") ? 1 : 0;

        try {
            Connection conn = MySQL.createConnection();
            String sql = "UPDATE attendance SET  status = ? WHERE student_id = ? AND date_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(2, studentId);
            ps.setInt(3, dateId);
            ps.setInt(1, status);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Attendance updated successfully.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating attendance: " + ex.getMessage());
        }
    }

    
}
