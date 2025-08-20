/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.timetable.process;

/**
 *
 * @author sava
 */
import connection.MySQL1;
import javax.swing.*;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class ComboBoxLoader {

    public Map<String, String> loadCombo(JComboBox<String> combo, String table) {
        Map<String, String> map = new LinkedHashMap<>();
        combo.removeAllItems();
        String query;
        boolean isTeacher = table.equalsIgnoreCase("teacher");
        if (isTeacher) {
            query = "SELECT nic, fname, lname FROM teacher";
        } else {
            query = "SELECT id, name FROM " + table;
        }
        try (Connection conn = createConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String id = isTeacher ? rs.getString("nic") : rs.getString("id");
                String name = isTeacher ? rs.getString("fname") + " " + rs.getString("lname"): rs.getString("name");
                combo.addItem(name);
                map.put(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
    private Connection createConnection() throws SQLException {
        return MySQL1.createConnection();
    }
}
