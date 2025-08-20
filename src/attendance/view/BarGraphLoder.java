/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package attendance.view;

import connection.MySQL;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author sava
 */
public class BarGraphLoder {

    private JPanel createChart(Map<String, Integer> monthAmountMap, String chartTitle) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Populate dataset
        for (Map.Entry<String, Integer> entry : monthAmountMap.entrySet()) {
            dataset.addValue(entry.getValue(), "Amount", entry.getKey());
        }

        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
                chartTitle, // Chart title
                "Month", // X-axis label
                "Amount", // Y-axis label
                dataset // Data
        );

        // Customize plot
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.GRAY);

        // Optional: Customize bar appearance
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(70, 130, 180)); // Steel blue

        // Wrap in panel
        return new ChartPanel(chart);
    }

    public void getAttendanceCountByDate(JPanel panel, String title, String query) {
    

        Map<String, Integer> attendanceMap = new LinkedHashMap<>();

        Connection conn = MySQL.createConnection();
        try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String date = rs.getString("period");
                int amount = rs.getInt("amount");
                attendanceMap.put(date, amount);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BarGraphLoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        panel.removeAll();
        JPanel createChart = createChart(attendanceMap, title);
        panel.add(createChart);
        panel.revalidate(); // Recalculates layout
        panel.repaint();
    }

}
