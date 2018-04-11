/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.reporttype;

import bapers.database.DBImpl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author CameronE
 */
public class SummaryReport extends Report {

    public SummaryReport(String[] reportPeriod) {
        super(reportPeriod);

    }

    @Override
    public void generate() {

    }

    public void getData(DBImpl db, Connection conn) {
        String sql = "SELECT SUM(TIMESTAMPDIFF(MINUTE,summary_performance.start,summary_performance.finish)) AS totalduration, summary_performance.department_code, summary_performance.finish FROM summary_performance WHERE summary_performance.finish BETWEEN '" + reportPeriod[0] + "' AND '" + reportPeriod[1] + "' GROUP BY summary_performance.department_name, summary_performance.finish ORDER BY summary_performance.finish;";
        //close resultset after use

        try (ResultSet result = db.read(sql, conn)) {
            while (result.next()) {
                String department = result.getString("department_code");
                String finish = result.getString("finish");
                int duration = result.getInt("totalduration");
                System.out.println(department + " : " + finish  + " : " + duration);

                if (department.equals("CR")) {

                } else if (department.equals("DA")) {

                } else if (department.equals("DR")) {

                } else if (department.equals("FR")) {

                } else if (department.equals("PD")) {

                } else if (department.equals("PR"))
                {
                    
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
