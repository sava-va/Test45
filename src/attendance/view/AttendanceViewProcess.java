/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package attendance.view;

import connection.MySQL;
import gui.attendance.NameLabelTag;
import gui.attendance.NameTagBar;
import gui.attendance.OneZeroMarks;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author sava
 */
public class AttendanceViewProcess {

    public void loadNameTagBars(int class_id, JPanel gridPanel) {

        LocalDate today = LocalDate.now();
        LocalDate twentyDaysAgo = today.minusDays(20);
        String date = "SELECT * FROM date WHERE date BETWEEN '" + twentyDaysAgo + "' AND  '" + today + "' LIMIT 20";
        String dateCount = "SELECT  COUNT(id) as id_count FROM date WHERE date BETWEEN '" + twentyDaysAgo + "' AND  '" + today + "' LIMIT 20";
        String studentCount = "SELECT COUNT(class_name_id) AS id FROM class WHERE class_name_id = " + class_id + "";

        try {
            ResultSet dateRs = MySQL.execute(date);
            ResultSet dateCountRs = MySQL.execute(dateCount);
            if (dateCountRs.next()) {
                int aInt = dateCountRs.getInt("id_count");
                int resultDifference = 20 - aInt;
                if (resultDifference > 0) {
                    ResultSet studentRs = MySQL.execute(studentCount);
                    int CountStudent = 0;
                    if (studentRs.next()) {
                        CountStudent = studentRs.getInt("id");
                        for (int i = 0; i < resultDifference; i++) {
                            NameTagBar ntb1 = new NameTagBar();
                            for (int j = 0; j < CountStudent; j++) {
                                ntb1.getGridPanel().add(new OneZeroMarks("0"));

                            }
                            gridPanel.add(ntb1);
                        }
                    }
                }
            }
            while (dateRs.next()) {
                String marks = "SELECT * FROM attendance_student WHERE date_id = " + dateRs.getString("id") + " AND class_id = " + class_id + "";
                ResultSet marksRs = MySQL.execute(marks);
                NameTagBar ntb = new NameTagBar();
                while (marksRs.next()) {
                    ntb.getGridPanel().add(new OneZeroMarks(marksRs.getString("status")));
                }
                gridPanel.add(ntb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceViewProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadName(int class_id, JPanel namePanel) {
        String student= "SELECT id, fname, lname FROM student INNER JOIN class ON "
                + "student.id = class.student_id WHERE class.class_name_id = " + class_id + "";
        try {
            ResultSet rs = MySQL.execute(student);
            while (rs.next()) {
                NameLabelTag nameLabelTag = new NameLabelTag(rs.getInt("id"),rs.getString("fname"),rs.getString("lname"));
                namePanel.add(nameLabelTag);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceViewProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
