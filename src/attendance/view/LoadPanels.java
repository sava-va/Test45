/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package attendance.view;

import gui.attendance.ViewAllAttendence;

/**
 *
 * @author sava
 */
public class LoadPanels {

    private static LoadPanels loadPanels;
    private final ViewAllAttendence viewAllAttendence;
    private final NameGridLoder nameGridLoder;
    private final BarGraphLoder barGraphLoder;

    private LoadPanels(ViewAllAttendence viewAllAttendence) {
        this.viewAllAttendence = viewAllAttendence;
        nameGridLoder = new NameGridLoder(viewAllAttendence);
        barGraphLoder = new BarGraphLoder();
    }

    public static LoadPanels getInstance(ViewAllAttendence viewAllAttendence) {
        if (loadPanels == null) {
            loadPanels = new LoadPanels(viewAllAttendence);
        }
        return loadPanels;
    }

    public String loadPanels(String nic, String period, String table) {
        String query = buildAttendanceQuery(nic, period, table);
        nameGridLoder.loadNames(table, viewAllAttendence.getNameGridPanel());
        barGraphLoder.getAttendanceCountByDate(viewAllAttendence.getGraphPanel(), table, query);

        return table;
    }

    public String buildAttendanceQuery(String nic, String period, String table) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        if ("day".equalsIgnoreCase(period)) {
            query.append("LPAD(DAY(date), 2, '0') AS period, ");
        } else if ("month".equalsIgnoreCase(period)) {
            query.append("MONTHNAME(date) AS period, ");
        }
        query.append("COUNT(*) AS amount ");
        query.append("FROM ");
        query.append("attendance_");
        query.append(table);
        query.append(" INNER JOIN date ON ");
        query.append("attendance_");
        query.append(table);
        query.append(".date_id = date.id ");
        if (nic != null && !table.equalsIgnoreCase("student")) {
            query.append("WHERE ").append(table).append("_nic = ").append("'").append(nic).append("'").append(" AND status='1'");

        }
        if (nic != null && table.equalsIgnoreCase("student")) {
            query.append("WHERE ").append("class_id = ").append("'").append(nic).append("'").append(" AND status='1'");
        }
        if ("day".equalsIgnoreCase(period)) {
            query.append("GROUP BY date ");
            query.append("ORDER BY DAY(date)");
        } else if ("month".equalsIgnoreCase(period)) {
            query.append("GROUP BY MONTHNAME(date) ");
            query.append("ORDER BY MONTHNAME(date)");
        }
        return query.toString();
    }

    public void loadUser(String id) {
        viewAllAttendence.setNic(id);
        loadPanels(id, viewAllAttendence.getPeriod(), viewAllAttendence.getTable());
    }
}
