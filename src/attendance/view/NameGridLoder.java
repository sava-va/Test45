/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package attendance.view;

import connection.MySQL;
import gui.attendance.ViewAllAttendence;
import gui.attendance.ViewNameButtonTag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author sava
 */
public class NameGridLoder {

    private ViewAllAttendence viewAllAttendence;

    public NameGridLoder(ViewAllAttendence viewAllAttendence) {
        this.viewAllAttendence = viewAllAttendence;
    }
    
    public void loadNames(String name, JPanel namePanel) {
        String query;
        switch (name) {
            case "principal" -> query = "SELECT nic, fname, lname FROM principle";
            case "teacher" -> query = "SELECT nic, fname, lname FROM teacher";
            case "admin" -> query = "SELECT nic, fname, lname FROM admin";
            case "student" -> {
                loadClassNames(namePanel);
                return;
            }
            default -> throw new AssertionError();
        }
        try {
            namePanel.removeAll();
            ResultSet rs = MySQL.execute(query);
            while (rs.next()) {
                String id = rs.getString("nic");
                String username = rs.getString("fname")+" "+rs.getString("lname");
                ViewNameButtonTag nameLabelTag = new ViewNameButtonTag(id,username);
                namePanel.add(nameLabelTag);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceViewProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        namePanel.revalidate(); // Recalculates layout
        namePanel.repaint();
    }

    public void loadClassNames(JPanel namePanel) {
        String query = "SELECT * FROM class_name";
        try {
            namePanel.removeAll();
            ResultSet rs = MySQL.execute(query);
            while (rs.next()) {
                ViewNameButtonTag nameLabelTag = new ViewNameButtonTag(rs.getString("id"),rs.getString("name"));
                namePanel.add(nameLabelTag);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceViewProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        namePanel.revalidate(); // Recalculates layout
        namePanel.repaint();
    }
}
