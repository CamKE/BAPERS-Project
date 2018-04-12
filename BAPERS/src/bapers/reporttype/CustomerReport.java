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
public class CustomerReport extends Report {

    String accountNo;
    
    public CustomerReport(String[] reportPeriod, String info) {
        super(reportPeriod);
        this.accountNo = info.split("\\:")[2];
    }

    @Override
    public ArrayList<Object[][]> generate(DBImpl db, Connection conn) {
        ArrayList<Object[][]> data = new ArrayList<>();
        Object[][] rows;
        int count = 0;

        String sql = "SELECT *, (SELECT COUNT(customer_report.Customer_account_no) FROM customer_report) AS numrows FROM customer_report WHERE finish BETWEEN '" + reportPeriod[0] + "' AND '" + reportPeriod[1] + "' AND customer_report.Customer_account_no = " + accountNo;

        //close resultset after use
        try (ResultSet result = db.read(sql, conn)) {
            result.next();
            rows = new Object[result.getInt("numrows")][5];
            do {
                rows[count][0] = result.getInt("job_no");
                rows[count][1] = result.getDate("Deadline_date_received");
                rows[count][2] = result.getDate("finish");
                rows[count][3] = result.getInt("total_payable");
                rows[count][4] = result.getString("invoice_status");

                count++;
            } while (result.next());

            data.add(rows);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return data;
    }
}
