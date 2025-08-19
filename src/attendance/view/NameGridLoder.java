/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package attendance.view;

import connection.MySQL;
import gui.attendance.NameLabelTag;
import gui.attendance.ViewNameLabelTag;
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

    public void loadNames(String name, JPanel namePanel) {
        String query;
        switch (name) {
            case "principal" ->
                query = "SELECT nic, fname, lname FROM principle";
            case "teacher" ->
                query = "SELECT nic, fname, lname FROM teacher";
            case "admin" ->
                query = "SELECT nic, fname, lname FROM admin";
            default ->
                throw new AssertionError();
        }
        try {
            namePanel.removeAll();
            ResultSet rs = MySQL.execute(query);
            while (rs.next()) {
                ViewNameLabelTag nameLabelTag = new ViewNameLabelTag(rs.getString("fname"), rs.getString("lname"));
                namePanel.add(nameLabelTag);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceViewProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
