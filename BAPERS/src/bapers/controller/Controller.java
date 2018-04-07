/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.controller;

import bapers.acct.*;
import bapers.database.DBImpl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
        
    }
    
    
    
    
    // auto backup
    public boolean checkAutoBackupConfigExist() {
        final String sql = "select * from BackupSettings;";
        int size = 0;
        
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) ++size;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return size > 0;
    }
    
    public AutoBackupConfig getAutoBackupConfigData() {
        final String sql = "select * from BackupSettings;";
        AutoBackupConfig autoData = null;
        
        try (ResultSet result = database.read(sql, conn)) {
            result.next(); // gets only the first row in the table
            autoData = new AutoBackupConfig(
                    result.getString("backup_mode"), 
                    result.getString("backup_frequency"), 
                    result.getString("backup_location")
            );
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return autoData;
    }
    
    public void setAutoBackupConfig(final AutoBackupConfig config) {
        if (checkAutoBackupConfigExist() == false) {
            final String sql = "insert into `bapers_data`.`BackupSettings` (`backup_mode`, `backup_frequency`, `backup_location`) values (" 
                    + "\'" + config.getBackupMode().toLowerCase() + "\', " 
                    + "\'" + config.getBackupFrequency().toLowerCase() + "\', " 
                    + "\'" + config.getBackupLocation() + "\'" + ");";

            try {
                database.write(sql, conn);
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
        } else {
            final AutoBackupConfig oldConfigData = getAutoBackupConfigData();
            final String sql = "UPDATE `BAPERS_data`.`BackupSettings` SET"
                    + "`backup_mode`=" + "\'" + config.getBackupMode() + "\', " 
                    + "`backup_frequency`=" + "\'" + config.getBackupFrequency() + "\', " 
                    + "`backup_location`=" + "\'" + config.getBackupLocation() + "\'" 
                    + "WHERE `backup_mode`=" + "\'" + oldConfigData.getBackupMode() + "\'"
                    + ";";

            try {
                database.write(sql, conn);
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    //late payment
    public ArrayList<Invoice> getInvoices() throws ParseException {
        final String sql = "select * from Invoice";
        
        // putting data into array list
        final ArrayList<Invoice> invoices = new ArrayList<>();
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) {
                Invoice invoice = new Invoice(
                        result.getInt("Invoice_no"), 
                        result.getInt("Job_job_no"), 
                        result.getInt("total_payable"), 
                        result.getDate("date_issued"), 
                        result.getString("invoice_status"),
                        result.getString("invoice_location")
                );
                if(invoice.getInvoiceStatus().equals(Invoice.Status.AWAITINGPAYMENT))
                    invoices.add(invoice);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return invoices;
    }
    
    public void recordPayment(final Payment p, final String type, final Invoice i, final Card card) {
        boolean paymentRecorded;
        
        if (type.equals("Card")) {
            recordCardDetails(p, i, card);
            
            final int paymentNo = p.getPaymentNo();
            final double total = p.getTotal();
            final String paymentType = p.getPaymentType();
            final Date paymentDate =  p.getPaymentDate();
            final int invoiceNumber = p.getInvoiceNumber();
            final String cardDetailsLast4digits = ((PaymentCard) p).cardDetailsLast4Digits();
            final String cardDetailsExpiryDate = ((PaymentCard) p).getCardDetailsExpiryDate();
            
            final String sql = "insert into PaymentRecord values (" 
                    + paymentNo 
                    + ',' + total 
                    + ", \"" + paymentType 
                    + "\"," + '\'' + paymentDate + '\''
                    + ',' + invoiceNumber + ","
                    + "\"" +  cardDetailsLast4digits
                    + "\"," + "\"" + cardDetailsExpiryDate + "\"" + ");";
            
            try {
                database.write(sql, conn);
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
            
            paymentRecorded = true;
            changeInvoiceStatus(paymentRecorded, p, i);
        }
    }
    
    public void recordPayment(final Payment p, final String type, final Invoice i) {
        String sql;
        boolean paymentRecorded;
        
        if (type.equals("Cash")) {
            final int paymentNo = p.getPaymentNo();
            final double total = p.getTotal();
            final String paymentType = p.getPaymentType();
            final Date paymentDate =  p.getPaymentDate();
            final int invoiceNumber = p.getInvoiceNumber();
            
            sql = "insert into PaymentRecord values (" 
                    + paymentNo 
                    + ',' + total 
                    + ", \"" + paymentType 
                    + "\"," + '\'' + paymentDate + '\''
                    + ", " + invoiceNumber 
                    + ", "  + null
                    + ", "  + null
                    + ");"
                    ;
            
            try {
                database.write(sql, conn);
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
            
            paymentRecorded = true;
            changeInvoiceStatus(paymentRecorded, p, i);
        }
    }
    
    public void recordCardDetails(final Payment p, final Invoice invoice, final Card card) {
        final boolean cardRecorded = checkCardAlreadyRecorded(card);
        
        if (cardRecorded == false) {
            String sql = "insert into CardDetails values(" + "\'"
                    + ((PaymentCard) p).cardDetailsLast4Digits()+ "\', \'"
                    + ((PaymentCard) p).getCardType() + "\', \'"
                    + ((PaymentCard) p).getCardDetailsExpiryDate() + "\', \'" 
                    + findAssociatedCustomerFromInvoce(invoice) + "\'"
                    + ");"
                    ;

            try {
                database.write(sql, conn);
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
        }
    }
    
    public String findAssociatedCustomerFromInvoce(final Invoice invoice) {
        String customer = "";
        final String sql = "select * from Job where job_no = " + invoice.getJobJobNo();
        
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            customer = result.getString("Customer_account_no");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return customer;
    }
    
    public boolean checkCardAlreadyRecorded(final Card card) {
        boolean cardRecorded = false;
        final String sql = "select count(*) from CardDetails where last4digits = \'" 
                + card.getLast4Digits() + "\' and card_type = \'"
                + card.getCardType() + "\' and expiry_date = \'"
                + card.getExpiryDate() + "\';"
                ;
        
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            cardRecorded = result.getBoolean("count(*)");
            //System.out.println(cardRecorded);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return cardRecorded;
    }
    
    public void changeInvoiceStatus(final boolean paymentRecorded, final Payment p, final Invoice i) {
        if (paymentRecorded == true) {
            final String status = paidOnTIme(p, i);
            final String sql = "update bapers_data.invoice set invoice_status = \'"
                    + status
                    + "\' where `Invoice_no` = " + '\'' 
                    + p.getPaymentNo() + '\'' + ";"
                    ;
            try {
                database.write(sql, conn);
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
        }
    }
    
    public String paidOnTIme(final Payment p, final Invoice i) {
        String invoiceStatus;
        
        if (p.getPaymentDate().compareTo(i.getDateIssued()) > 0)
            invoiceStatus = "Paid late";
        else 
            invoiceStatus = "Paid on time";
        return invoiceStatus;
    }
}
