/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.controller;

import bapers.acct.Customer;
import bapers.acct.Invoice;
import bapers.acct.Payment;
import bapers.acct.PaymentCard;
import bapers.acct.PaymentCash;
import bapers.database.DBImpl;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
        String[] roles = new String[numStandardJobs()];
        int i = 0;
        String sql = "select * from standardjob";

        //close resultset after use
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            roles[i] = result.getString("code");
            System.out.println(roles[i]);
//            while (result.next()) {
//                roles[i] = result.getString("code");
//                 System.out.println(roles[i]);
//                i++;
//               
//                String[] token;
//                
//            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return roles;
    }
    
    public void createCustomerAccount(Customer cust) {
        String sql = "INSERT INTO CUSTOMER VALUES";
    }
    
    
    
    
    
    
    
    
    
    public ArrayList<Invoice> getInvoices() throws ParseException {
        String sql = "select * from Invoice";
        
        // putting data into array list
        ArrayList<Invoice> invoices = new ArrayList<>();
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) {
                Invoice invoice = new Invoice(result.getInt("Invoice_no"), result.getInt("Job_job_no"), 
                result.getInt("total_payable"), result.getDate("date_issued"), result.getString("invoice_status"),
                result.getString("invoice_location"));
                if(invoice.getInvoiceStatus().equals(Invoice.Status.AWAITINGPAYMENT))
                    invoices.add(invoice);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return invoices;
    }
    
    public void recordPayment(Payment p, String type) {
        String sql = "";
        if (type.equals("Card")) {
            final int paymentNo = p.getPaymentNo();
            final double total = p.getTotal();
            final String paymentType = p.getPaymentType();
            String paymentDate =  p.getPaymentDate();
            final int[] invoiceNumber = p.getInvoiceNumber();
            //final String cardType = ((PaymentCard) p).getCardType();
            final String cardDetailsLast4digits = ((PaymentCard) p).getCardDetailsLast4digits();
            final String cardDetailsExpiryDate = ((PaymentCard) p).getCardDetailsExpiryDate();
            
            sql = "INSERT INTO PaymentRecord VALUES (" 
                    + paymentNo 
                    + ',' + total 
                    + ", \"" + paymentType 
                    + "\"," + '\'' + paymentDate + '\''
                    + ',' + invoiceNumber[0] + ","
                    + "\"" +  cardDetailsLast4digits
                    + "\"," + "\"" + cardDetailsExpiryDate + "\"" + ");";
            //System.out.println(sql);
            
            try {
                database.write(sql, conn);
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
            
            String sql2 = "UPDATE bapers_data.invoice SET invoice_status='Paid on time' WHERE `Invoice_no`= " 
                    + '\'' + paymentNo + '\'' +";" ;
            try {
                database.write(sql2, conn);
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
        } else if (type.equals("Cash")) {
            final int paymentNo = p.getPaymentNo();
            final double total = p.getTotal();
            final String paymentType = p.getPaymentType();
            String paymentDate =  p.getPaymentDate();
            final int[] invoiceNumber = p.getInvoiceNumber();
            
            sql = "INSERT INTO CashPaymentRecord VALUES (" 
                    + paymentNo 
                    + ',' + total 
                    + ", \"" + paymentType 
                    + "\"," + '\'' + paymentDate + '\''
                    + ',' + invoiceNumber[0] + ");";
            System.out.println(sql);
            
            try {
                database.write(sql, conn);
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
            
            String sql2 = "UPDATE bapers_data.invoice SET invoice_status='Paid on time' WHERE `Invoice_no`= " 
                    + '\'' + paymentNo + '\'' +";" ;
            try {
                database.write(sql2, conn);
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
        }
    }
}
