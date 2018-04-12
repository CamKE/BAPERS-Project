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
import java.util.ArrayList;

/**
 *
 * @author CameronE
 */
public class SummaryReport extends Report {

    public SummaryReport(String[] reportPeriod) {
        super(reportPeriod);

    }

    @Override
    public ArrayList<Object[][]> generate(DBImpl db, Connection conn) {
        ArrayList<Object[][]> shiftData = new ArrayList<>();
        String[][] shifts = new String[][]{{"05:00:00", "14:30:00"}, {"14:30:00", "22:00:00"}, {"22:00:00", "05:00:00"}};
        for (String[] shift : shifts) {
            System.out.println("BETWEEN '" + reportPeriod[0] + " " + shift[0] + "' AND '" + reportPeriod[1] + " " + shift[1]);
            String sql = "SELECT (SELECT COUNT(DISTINCT summary_performance.finish) FROM summary_performance) AS numrows, SUM(TIMESTAMPDIFF(MINUTE,summary_performance.start,summary_performance.finish)) AS totalduration, summary_performance.department_code, summary_performance.finish FROM summary_performance WHERE (CAST(summary_performance.finish AS DATE) BETWEEN '" + reportPeriod[0] + "' AND '" + reportPeriod[1] + "') AND (CAST(summary_performance.finish AS TIME) BETWEEN '" + shift[0] + "' AND '" + shift[1] + "') GROUP BY summary_performance.department_name, summary_performance.finish ORDER BY summary_performance.finish;";
            shiftData.add(getData(db, conn, sql));
        }
        return shiftData;
    }

    private Object[][] getData(DBImpl db, Connection conn, String sql) {
        Object[][] rows = null;
        int count = 0;
        String previousFinishDate = null;
        int totalCR = 0;
        int totalDA = 0;
        int totalFR = 0;
        int totalPD = 0;
        //close resultset after use
        try (ResultSet result = db.read(sql, conn)) {
            if (result.next()) {
                int numRows = result.getInt("numrows");
                rows = new Object[numRows][5];

                String department = result.getString("department_code");
                String finishDate = result.getString("finish");
                int duration = result.getInt("totalduration");

                rows[count][1] = 0;
                rows[count][2] = 0;
                rows[count][3] = 0;
                rows[count][4] = 0;

                do {
                    System.out.println(department + " : " + finishDate + " : " + duration);

                    rows[count][0] = finishDate;

                    switch (department) {
                        case "CR":
                            rows[count][1] = duration;
                            totalCR += (Integer) rows[count][1];
                            break;
                        case "DA":
                            rows[count][2] = duration;
                            totalDA += (Integer) rows[count][2];
                            break;
                        case "FR":
                            rows[count][3] = duration;
                            totalFR += (Integer) rows[count][3];
                            break;
                        case "PD":
                            rows[count][4] = duration;
                            totalPD += (Integer) rows[count][4];
                            break;
                        default:
                            break;
                    }

                    result.next();
                    previousFinishDate = finishDate;
                    finishDate = result.getString("finish");
                    department = result.getString("department_code");
                    duration = result.getInt("totalduration");
                    if (!finishDate.equals(previousFinishDate)) {
                        count++;
                        rows[count][1] = 0;
                        rows[count][2] = 0;
                        rows[count][3] = 0;
                        rows[count][4] = 0;
                    }
                } while (!result.isAfterLast());
                
                rows[count][0] = "Total";
                rows[count][1] = (Integer) totalCR;
                rows[count][2] = (Integer) totalDA;
                rows[count][3] = (Integer) totalFR;
                rows[count][4] = (Integer) totalPD;
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }

        return rows;
    }
}
