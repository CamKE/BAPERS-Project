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
            while (result.next()) {
                roles[i] = result.getString("code");
                System.out.println(roles[i]);
                i++;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return roles;
    }
    
    public void createCustomerAccount(Customer cust) {
        
    }
    
    
    
    
    // auto backup code
    public boolean checkAutoBackupConfigExist() {
        final String sql = "select * from BackupSettings;";
        int size = 0;
        
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) ++size;
            //result.close();
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
            //result.close();
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
    
    
    
    
    
    
    
    
    
    
    
    //late payment code
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
            
            final String paymentNo = p.getPaymentNo();
            final double total = p.getTotal();
            final String paymentType = p.getPaymentType();
            final String paymentDate =  p.getPaymentDate();
            final int invoiceNumber = p.getInvoiceNumber();
            final String cardDetailsLast4digits = ((PaymentCard) p).getCardDetailsLast4Digits();
            final String cardDetailsExpiryDate = ((PaymentCard) p).getCardDetailsExpiryDate();
            
            final String sql = "insert into PaymentRecord values (" 
                    + paymentNo + "," 
                    + total + ", \'" 
                    + paymentType + "\', \'" 
                    + paymentDate + "\', \'" 
                    + invoiceNumber + "\', \'" 
                    + cardDetailsLast4digits + "\', \'" 
                    + cardDetailsExpiryDate + "\');";
            
            try {
                database.write(sql, conn);
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
            
            paymentRecorded = true;
            //System.out.println(paymentRecorded);
            changeInvoiceStatus(paymentRecorded, p, i);
        }
    }
    
    public void recordPayment(final Payment p, final String type, final Invoice i) {
        String sql;
        boolean paymentRecorded;
        
        if (type.equals("Cash")) {
            final String paymentNo = p.getPaymentNo();
            final double total = p.getTotal();
            final String paymentType = p.getPaymentType();
            final String paymentDate =  p.getPaymentDate();
            final int invoiceNumber = p.getInvoiceNumber();
            
            sql = "insert into PaymentRecord values (" 
                    + paymentNo + "," 
                    + total + ", \'" 
                    + paymentType + "\', \'" 
                    + paymentDate + "\', \'" 
                    + invoiceNumber + "\', "
                    + null + ", "  
                    + null + ");"
                    ;
            
            try {
                database.write(sql, conn);
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
            
            paymentRecorded = true;
            //System.out.println(paymentRecorded);
            changeInvoiceStatus(paymentRecorded, p, i);
        }
    }
    
    public void recordCardDetails(final Payment p, final Invoice invoice, final Card card) {
        final boolean cardRecorded = checkCardAlreadyRecorded(card);
        
        if (cardRecorded == false) {
            String sql = "insert into CardDetails values(" + "\'"
                    + ((PaymentCard) p).getCardDetailsLast4Digits()+ "\', \'"
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
        String customerNo = "";
        final String sql = "select * from Job where job_no = " + invoice.getJobJobNo();
        
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            customerNo = result.getString("Customer_account_no");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        //System.out.println(customerNo);
        return customerNo;
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
            //System.out.println(status);
            
            final String sql = "update bapers_data.invoice set invoice_status = \'"
                    + status
                    + "\' where `Invoice_no` = " + '\'' 
                    + p.getInvoiceNumber()+ '\'' + ";" // here is the problem, no number to reference.
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
        final Date paymentDate = new Date(p.getPaymentDate());
        final Date invoiceDate = i.getDateIssued();
        
        if ( paymentDate.compareTo(invoiceDate) > 0 )
            invoiceStatus = "Paid late";
        else 
            invoiceStatus = "Paid on time";
        return invoiceStatus;
    }
    
    
    
    
    
    
    
    
    
    
    
    // re-activate customer
    public int getInDefaultSize() {
        int size = 0;
                
        final String sql = "select count(*) as size from Customer where in_default = 1;";
        
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            size = result.getInt("size");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return size;
    }
    
    public Customer[] getAllInDefault() {
        Customer[] defaultCustomers = new Customer[getInDefaultSize()];
        
        int i = 0;
        final String sql = "select * from Customer where in_default = 1;";
        
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) {
                Customer customerInfo = new Customer(
                        result.getString("account_no"),
                        result.getString("account_holder_name"),
                        result.getString("prefix"),
                        result.getString("firstname"),
                        result.getString("lastname"),
                        result.getString("street_name"),
                        result.getString("postcode"),
                        result.getString("city"),
                        result.getString("phone"),
                        result.getBoolean("is_suspended"),
                        result.getBoolean("in_default"),
                        result.getBoolean("is_valued"),
                        result.getDate("registration_date"),
                        result.getString("building_no"),
                        result.getString("email_contact")
                );
                defaultCustomers[i] = customerInfo;
                
                ++i;
                //15
            }   
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return defaultCustomers;
    }
    
    public void reactivateDefaultAccount(String customer) {
        final String customerNo = customer;
        final String sql = "UPDATE `bapers_data`.`Customer` SET `in_default`='0' WHERE `account_no`=\'" + customerNo + "';" ;
        
        try {
            database.write(sql, conn);
        } catch (Exception e) {
            System.out.println("Exception error: " + e);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // view all customers
    public int getCustomerListSize() {
        int size = 0;
        
        final String sql = "select count(*) as size from Customer;";
        
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            size = result.getInt("size");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return size;
    }
    
    public Customer[] getAllCustomers() {
        Customer[] customers = new Customer[getCustomerListSize()];
        
        int i = 0;
        final String sql = "select * from Customer";
        
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) {
                Customer customerInfo = new Customer(
                        result.getString("account_no"),
                        result.getString("account_holder_name"),
                        result.getString("prefix"),
                        result.getString("firstname"),
                        result.getString("lastname"),
                        result.getString("street_name"),
                        result.getString("postcode"),
                        result.getString("city"),
                        result.getString("phone"),
                        result.getBoolean("is_suspended"),
                        result.getBoolean("in_default"),
                        result.getBoolean("is_valued"),
                        result.getDate("registration_date"),
                        result.getString("building_no"),
                        result.getString("email_contact")
                );
                customers[i] = customerInfo;
                
                ++i;
                //15
            }   
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return customers;
    }
    
    public String getCustomerDiscountType(final Customer customer) {
        String type = null;
        final String sql = "select * from DiscountPlan where DiscountPlan.Customer_account_no =\'" 
                + customer.getAccountNo() + "\';";
        
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            type = result.getString("discount_type");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return type;
    }
    
    
    
    
    
    
    
    
    
    
    // apply discount 
    public boolean checkCustomerHasDiscountPlan(final Customer customer) {
        final String sql = "select count(*) as 'Has Discount' from DiscountPlan where DiscountPlan.Customer_account_no =" 
                + customer.getAccountNo() + ";";
        
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            return result.getBoolean("Has Discount");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
    
    public void clearExistingRowBasedOn() {
        final String sql = "DELETE FROM `bapers_data`.`FixedDiscount` WHERE `DiscountPlan_Customer_account_no`='00001';";
    }
    
    public void applyDiscountPlan(final Customer customer, final User user, final String discountPlan) {
        final boolean hasDiscountPlan = checkCustomerHasDiscountPlan(customer);
        String sql;
        
        // update or insert discount plan
        if (hasDiscountPlan == true) 
            sql = "update `bapers_data`.`DiscountPlan` set `discount_type`=\'" 
                    + discountPlan + "\' where `Customer_account_no`=\'" 
                    + customer.getAccountNo() + "\';";
        else 
            sql = "insert into DiscountPlan values (\'" 
                    + customer.getAccountNo() + "\', \'"
                    + user.getAccountNo() + "\', \'"
                    + discountPlan + "\');" ;
            
        try {
            database.write(sql, conn);
        } catch (Exception e) {
            System.out.println("Exception error: " + e);
        }
        
//        switch (discountPlan) {
//            case "Fixed" :
//                applyFixedDiscountRate();
//                break;
//            case "Variable" : 
//                applyVariableDiscountRate();
//                break;
//            case "Flexible" : 
//                applyFlexibleDiscountRate();
//                break;
//            default : break;
//        }
    }
    
    public void applyFixedDiscountRate(final int fixedDiscountPercentage, final Customer customer) {
        final String sql = "insert into bapers_data.FixedDiscount values( \'" 
                + fixedDiscountPercentage + "\', \'"
                + customer.getAccountNo() + "\'"
                + ");";
        
        try {
            database.write(sql, conn);
        } catch (Exception e) {
            System.out.println("Exception error: " + e);
        }
    }
    
    public void applyVariableDiscountRate() {
        final String sql = "insert into Task_DiscountPlans values(" 
                + ");";
        
        try {
            database.write(sql, conn);
        } catch (Exception e) {
            System.out.println("Exception error: " + e);
        }
    }
    
    public ArrayList<Job> getAllJobsFromCustomer(final Customer customer) {
        ArrayList<Job> jobs = new ArrayList<>();
        final String sql = "select * from bapers_data.Job\n" 
                + "inner join Status\n" 
                + "where  Status.status_id = Job.Status_status_id\n" 
                + "and Job.Customer_account_no =\'" 
                + customer.getAccountNo() + "\';";
        
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) {
                Job job = new Job(
                        result.getString("job_no"),
                        result.getString("User_account_no"),
                        result.getString("Customer_account_no"),
                        result.getString("status_type"),
                        result.getBoolean("is_collected"),
                        result.getDate("Deadline_date_received"),
                        result.getDate("Deadline_CompletionTime_completion_time"),
                        result.getString("special_instructions")
                );
                
                jobs.add(job);
            }   
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return jobs;
    }
    
    public ArrayList<JobStandardJob> getAllStandardJobFromJob(final Job jobNo) {
        ArrayList<JobStandardJob> stdJobs = new ArrayList<>();
        final String sql = "select * from Job_StandardJobs " 
                + "inner join Status " 
                + "where Job_StandardJobs.Status_status_id = Status.status_id " 
                + "and Job_StandardJobs.Job_job_no =\'" 
                + jobNo.getJobNo()+ "\';";
        
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) {
                JobStandardJob jobStandardJob = new JobStandardJob(
                        result.getString("Job_job_no"),
                        result.getString("StandardJob_code"),
                        result.getString("status_type")
                );
                
                stdJobs.add(jobStandardJob);
            }   
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return stdJobs;
    }
    
    public ArrayList<JobStandardJobTask> getAllStandardJobTasksReferenceFromJobStandardJob(final JobStandardJob jobStandardJob) {
        ArrayList<JobStandardJobTask> jobStdJobTasks = new ArrayList<>();
        final String sql = "select * from Job_StandardJobs_Tasks where Job_StandardJobs_StandardJob_code =\'" 
                + jobStandardJob.getStandardJobCode() + "\';";
        
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) {
                JobStandardJobTask jobStandardJobTask = new JobStandardJobTask(
                        result.getString("Job_StandardJobs_Job_job_no"),
                        result.getString("Job_StandardJobs_StandardJob_code"),
                        result.getString("Task_task_id"),
                        result.getString("Status_status_id"),
                        result.getString("User_account_no")
                );
                
                jobStdJobTasks.add(jobStandardJobTask);
            }   
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return jobStdJobTasks;
    }
    
    public ArrayList<Task> getAllTasksFromStandardJobTasks(ArrayList<JobStandardJobTask> tasksRef) {
        ArrayList<Task> tasks = new ArrayList<>();
        final int TASKSREFSIZE = tasksRef.size();
        for (int i = 0; i < TASKSREFSIZE; ++i) {
                final String sql = "select * from Task where task_id = \'"
                        + tasksRef.get(i).getTaskID() + "\';";

                try (ResultSet result = database.read(sql, conn)) {
                result.next();
                Task task = new Task(
                        result.getString("task_id"),
                        result.getString("description"),
                        result.getInt("duration_min"),
                        result.getString("shelf_slot"),
                        result.getString("price"),
                        result.getString("Department_department_code")
                );
                tasks.add(task);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        
        return tasks;
    }
    
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        final String sql = "select * from Task";
        
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) {
                Task task = new Task(
                        result.getString("task_id"),
                        result.getString("description"),
                        result.getInt("duration_min"),
                        result.getString("shelf_slot"),
                        result.getString("price"),
                        result.getString("Department_department_code")
                );
                tasks.add(task);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return tasks;
    }
    
    public void applyFlexibleDiscountRate() {
        
    }
}
