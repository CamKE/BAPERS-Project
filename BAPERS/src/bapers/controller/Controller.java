/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.controller;

import bapers.acct.Customer;
import bapers.acct.Invoice;
import bapers.database.DBImpl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author CameronE
 */
public class Controller {

    private final DBImpl database;
    private final Connection conn;

    public Controller() {
        database = new DBImpl();
        conn = database.connect();
    }
    
    public int numStandardJobs() {
        String sql = "SELECT COUNT(*) AS rowcount FROM standardjob";
        try {
            ResultSet rs = database.read(sql, conn);
            rs.next();
            return rs.getInt("rowcount");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public String[] getStandardJobs() {
        // ArrayList roles = new ArrayList();
        //should there be this much code in a controller????
        String[] roles = new String[numStandardJobs()];
        int i = 0;
        String sql = "select job_description from standardjob";

        //close resultset after use
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) {
                roles[i] = result.getString("job_description");
                i++;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return roles;
    }
    
    public void createCustomerAccount(Customer cust) {
        String sql = "INSERT INTO CUSTOMER VALUES";
    }
    
    public ArrayList<Invoice> getInvoices() throws ParseException {
        String sql = "";
        
        // putting data into array list
        ArrayList<Invoice> invoices = new ArrayList<>();
       
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("2012-12-13 14:54:30"); 
        
        Invoice i;
        i = new Invoice(2, 2, 20, new Date(), "Awaiting payment", "Test");
        
        invoices.add(i);
        return invoices;
    }
}
