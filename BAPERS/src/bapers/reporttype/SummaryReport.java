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
    public Object[][] generate(DBImpl db, Connection conn) {
        Object[][] rows;
        int count = 0;
        String previousFinishDate = null;
        String sql = "SELECT (SELECT COUNT(DISTINCT summary_performance.finish) FROM summary_performance) AS numrows, SUM(TIMESTAMPDIFF(MINUTE,summary_performance.start,summary_performance.finish)) AS totalduration, summary_performance.department_code, summary_performance.finish FROM summary_performance WHERE summary_performance.finish BETWEEN '" + reportPeriod[0] + "' AND '" + reportPeriod[1] + "' GROUP BY summary_performance.department_name, summary_performance.finish ORDER BY summary_performance.finish;";
        //close resultset after use

        try (ResultSet result = db.read(sql, conn)) {
            result.next();
            rows = new Object[result.getInt("numrows")][5];

            do {
                String department = result.getString("department_code");
                String finishDate = result.getString("finish");
                int duration = result.getInt("totalduration");
                System.out.println(department + " : " + finishDate + " : " + duration);

                rows[count][0] = finishDate;
                result.next();
                previousFinishDate = finishDate;
                finishDate = result.getString("finish");

                while (previousFinishDate.equals(finishDate)) {

                    department = result.getString("department_code");
                    duration = result.getInt("totalduration");

                    switch (department) {
                        case "CR":
                            rows[count][1] = duration;
                            break;
                        case "DA":
                            rows[count][2] = duration;
                            break;
                        case "FR":
                            rows[count][3] = duration;
                            break;
                        case "PD":
                            rows[count][4] = duration;
                            break;
                        default:
                            break;
                    }
                    
                    result.next();          
                    previousFinishDate = finishDate;
                    finishDate = result.getString("finish");
                }

                count++;
            } while (!result.isAfterLast());

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return null;
    }
}
