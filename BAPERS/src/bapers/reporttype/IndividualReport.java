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
public class IndividualReport extends Report {

    String info;

    public IndividualReport(String[] reportPeriod, String info) {
        super(reportPeriod);
        this.info = info;
    }

    @Override
    public ArrayList<Object[][]> generate(DBImpl db, Connection conn) {
        ArrayList<Object[][]> data = new ArrayList<>();
        Object[][] rows;
        int count = 0;

        String sql = "SELECT *,(SELECT COUNT(staff_individual_performance.account_no) FROM staff_individual_performance) AS numrows,(SELECT SUM(TIMESTAMPDIFF(MINUTE,staff_individual_performance.start,staff_individual_performance.finish)) FROM staff_individual_performance WHERE staff_individual_performance.account_no = s1.account_no) AS totalTime, TIMESTAMPDIFF(MINUTE,s1.start,s1.finish) AS timeTaken FROM staff_individual_performance s1 WHERE s1.finish BETWEEN '" + reportPeriod[0] + "' AND '" + reportPeriod[1] + "'";

        if (!info.equals("Select user... (optional)")) {
            String[] parts = info.split("\\:");
            sql += " AND account_no = " + parts[2];
        }
            sql += " ORDER BY s1.account_no, s1.start";
        //close resultset after use
        try (ResultSet result = db.read(sql, conn)) {
            result.next();
            rows = new Object[result.getInt("numrows") + 1][8];
            int totalEffort = 0;
            do {
                rows[count][0] = result.getString("firstname") + " " + result.getString("lastname");
                rows[count][1] = result.getString("Job_StandardJobs_StandardJob_code");
                rows[count][2] = result.getInt("Task_task_id");
                rows[count][3] = result.getString("department_name");
                rows[count][4] = result.getDate("start");
                rows[count][5] = result.getTime("start");
                rows[count][6] = result.getInt("timeTaken");
                rows[count][7] = result.getInt("totalTime");
                totalEffort += (Integer) rows[count][6];
                System.out.println(rows[count][0] + " : " + rows[count][5]);
                count++;

            } while (result.next());
            rows[count][0] = "Total effort";
            rows[count][7] = totalEffort;
            data.add(rows);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return data;
    }
}
