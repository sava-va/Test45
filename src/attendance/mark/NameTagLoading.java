/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package attendance.mark;

import connection.MySQL;
import gui.attendance.NameButtonTag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author sava
 */
public class NameTagLoading {

    JPanel attendenMarkedPanel;
    int class_id;

    public NameTagLoading(JPanel attendenMarkedPanel, int ClASS_ID) {
        this.class_id = ClASS_ID;
        this.attendenMarkedPanel = attendenMarkedPanel;
      
    }

    public List loadNameButtonTag() {
        List<NameButtonTag> buttons = new ArrayList<>();
        try {
            ResultSet rs = MySQL.execute("SELECT id, fname, lname FROM student INNER JOIN class ON "
                    + "student.id = class.student_id WHERE class.class_name_id ="+ class_id+"" );
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("fname") + " " + rs.getString("lname");
                NameButtonTag nbt = new NameButtonTag(id, name);
                attendenMarkedPanel.add(nbt);
                buttons.add(nbt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return buttons;
    }
}
