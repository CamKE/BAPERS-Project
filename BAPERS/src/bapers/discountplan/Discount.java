/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.discountplan;

import bapers.database.DBImpl;
import bapers.job.Job;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author CameronE
 */
public abstract class Discount {

    int accountNo;
    String discountType;
    StringBuilder sb;

    public Discount(int accountNo, String discountType) {
        this.accountNo = accountNo;
        this.discountType = discountType;
        sb = new StringBuilder();
    }

    public boolean create(DBImpl db, Connection conn, int userAccountNo) {
        String sql = "INSERT INTO discountplan(Customer_account_no, User_account_no, discount_type) VALUES(" + accountNo + "," + userAccountNo + ",'" + discountType + "')";

        if (db.write(sql, conn) != 0) {
            buildQuery();

            return db.write(sb.toString(), conn) != 0;
        }
        JOptionPane.showMessageDialog(null, "This customer already has a discount plan.");
        return false;
    }
    
    abstract protected void buildQuery();
    abstract public double applyDiscount(Job job);

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

}
