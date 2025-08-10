/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.registration.charts;

import javax.swing.JPanel;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author sava
 */
public class ChartCreator {

    public void createBarChart(JPanel panelLineChart, DefaultCategoryDataset dataset) {
        BarChart barChart = new BarChart();
        barChart.showBarChart(panelLineChart, dataset);

    }

    public void  createPieChart(JPanel panelLineChart, DefaultPieDataset dataset) {
        PiChart piChart = new PiChart();
        piChart.showPieChart(panelLineChart, dataset);

    }
}
