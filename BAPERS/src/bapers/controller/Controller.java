/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.controller;

import bapers.AutoBackupConfig;
import bapers.customer.CustomerDetails;
import bapers.database.DBImpl;
import bapers.job.Invoice;
import bapers.job.Job;
import bapers.job.JobStandardJob;
import bapers.job.JobStandardJobTask;
import bapers.job.Material;
import bapers.job.StandardJob;
import bapers.job.Task;
import bapers.payment.Payment;
import bapers.payment.PaymentDetails;
import bapers.reporttype.CustomerReport;
import bapers.reporttype.IndividualReport;
import bapers.reporttype.SummaryReport;
import bapers.user.UserDetails;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import bapers.payment.Card;
import bapers.payment.PaymentCard;

/**
 *
 * @author CameronE
 */
public class Controller {

    final private DBImpl database;
    final private Connection conn;

    public Controller() {
        database = new DBImpl();
        conn = database.connect();
    }

    // Attempts to login the user into the system, given that their details are valid
    public UserDetails login(String userID, String password) {
        // the hash for the password entered is created and stored. (passsword hash field has to not be unique. password hashes will clash otherwise)
        int hashPassword = password.hashCode();
        // Check user details sql query
        String SQL = "SELECT user.account_no, user.firstname, user.lastname, user.password_hash, user.registration_date, role.role_description FROM role INNER JOIN user ON role.role_id = user.Role_role_id WHERE user.username ='" + userID + "' and user.password_hash = cast('" + hashPassword + "' as BINARY(32));";

        // Closes resultset after use
        try (ResultSet rs = database.read(SQL, conn)) {
            //If user details are valid
            if (rs.next()) {
                UserDetails user = new UserDetails(rs.getInt("account_no"), rs.getString("firstname"), rs.getString("lastname"), rs.getBlob("password_hash"), rs.getTimestamp("registration_date"), rs.getString("role_description"));
                return user;
            }
        } catch (Exception ex) {
            System.out.println("Log in Error: " + ex);
        }
        return null;
    }

    public boolean checkIfUsernameExists(String username) {

        // Check user details sql query
        String SQL = "SELECT username from user WHERE username = '" + username + "';";

        // Closes resultset after use
        try (ResultSet rs = database.read(SQL, conn)) {
            //If user details are valid
            if (rs.next()) {

                return true;
            }
        } catch (Exception ex) {
            System.out.println("Check username exists Error: " + ex);
        }
        return false;
    }

    // Attempts to create a new user in the system, given the details are valid
    public boolean createUser(String firstname, String lastName, String password, int roleId, String username) {
        int hashPassword = password.hashCode();
        // Write the user details into the database
        String userSQL = "INSERT INTO USER (firstname,lastname,password_hash,Role_role_id,username) VALUES ('" + firstname + "','" + lastName + "','" + hashPassword + "','" + roleId + "','" + username + "');";

        // Return whether or not rows have been affected
        return database.write(userSQL, conn) != 0;
    }

    public int getUserID(String password) {
        int hashPassword = password.hashCode();
        int userID = 0;
        String sql = "SELECT account_no FROM user WHERE password_hash = cast('" + hashPassword + "' as BINARY(32))";

        try (ResultSet rs = database.read(sql, conn)) {
            if (rs.next()) {
                userID = rs.getInt("account_no");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return userID;
    }

    // takes a roles description, and returns its id in the role table
    public int getRoleID(String role) {
        int roleID = 0;
        String sql = "SELECT role_id FROM role WHERE role_description = '" + role + "'";
        try (ResultSet rs = database.read(sql, conn)) {
            if (rs.next()) {
                roleID = rs.getInt("role_id");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return roleID;
    }

    public ArrayList<CustomerDetails> findCustomer(String customerNumber, String cFirstName, String cLastName, String accountHName, String streetName, String postCode, String city, String phone, String customerType, String customerStatus, String inDefault, String regDate) {
        StringBuilder sb = new StringBuilder();
        // 1=1 is ignored by sql. it allows for ease of adding conditions to the statement (AND)
        sb.append("SELECT * from customer WHERE 1=1 ");

        // The sql statement is constructed based on the values given
        if (!customerNumber.isEmpty()) {
            sb.append("AND account_no = ").append(Integer.parseInt(customerNumber));
        } else {
            if (!cFirstName.isEmpty()) {
                sb.append(" AND firstname = '").append(cFirstName).append("'");
            }
            if (!cLastName.isEmpty()) {
                sb.append(" AND lastname = '").append(cLastName).append("'");
            }
            if (!accountHName.isEmpty()) {
                sb.append(" AND account_holder_name = '").append(accountHName).append("'");
            }
            if (!streetName.isEmpty()) {
                sb.append(" AND street_name = '").append(streetName).append("'");
            }
            if (!postCode.isEmpty()) {
                sb.append(" AND postcode = '").append(postCode).append("'");
            }
            if (!city.isEmpty()) {
                sb.append(" AND city = '").append(city).append("'");
            }
            if (!phone.isEmpty()) {
                sb.append(" AND phone = '").append(phone).append("'");
            }
            if (!customerType.equals("Any")) {
                sb.append(" AND is_valued = ").append(customerType.equals("Valued"));
            }
            if (!customerStatus.equals("Any")) {
                sb.append(" AND is_suspended = ").append(customerStatus.equals("Suspended"));
            }
            if (!inDefault.equals("Any")) {
                sb.append(" AND in_default = ").append(inDefault.equals("True"));
            }
            //probably doesnt work atm
            if (!regDate.equals("dd-MMM-yyyy")) {
                sb.append(" AND registration_date = '").append(regDate).append("'");
            }
        }
        System.out.println(sb.toString());
        // stores all the customers returned
        ArrayList<CustomerDetails> customerList = new ArrayList<>();
        //stores a customer's details
        CustomerDetails customer;

        try (ResultSet rs = database.read(sb.toString(), conn)) {
            //for each user in the results returned
            while (rs.next()) {
                // place their details into a userdetails object
                customer = new CustomerDetails(rs.getInt("account_no"), rs.getString("account_holder_name"), rs.getString("prefix"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("street_name"), rs.getString("postcode"), rs.getString("city"), rs.getString("phone"), rs.getBoolean("is_suspended"), rs.getBoolean("in_default"), rs.getBoolean("is_valued"), rs.getTimestamp("registration_date"), rs.getInt("building_no"), rs.getString("email_contact"));
                //add their userdetails object to the arraylist of users
                customerList.add(customer);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return customerList;
    }

    // finds all the users for the given search criteria. returns all users if no criteria is given
    public ArrayList<UserDetails> findUser(String userNumber, String firstName, String lastName, String role) {
        StringBuilder sb = new StringBuilder();
        // 1=1 is ignored by sql. it allows for ease of adding conditions to the statement (AND)
        sb.append("SELECT user.account_no, user.firstname, user.lastname, user.password_hash, user.registration_date, role.role_description from user INNER JOIN role ON user.Role_role_id = role.role_id WHERE 1=1 ");

        // The sql statement is constructed based on the values given
        if (!userNumber.isEmpty()) {
            sb.append("AND account_no = ").append(Integer.parseInt(userNumber));
        } else {
            if (!firstName.isEmpty()) {
                sb.append(" AND firstname = '").append(firstName).append("'");
            }
            if (!lastName.isEmpty()) {
                sb.append(" AND lastname = '").append(lastName).append("'");
            }
            if (!role.equals("Any")) {
                int role_id = getRoleID(role);
                sb.append(" AND Role_role_id = ").append(role_id);
            }
        }

        // stores all the users returned
        ArrayList<UserDetails> userList = new ArrayList<>();
        //stores a user's details
        UserDetails user;

        try (ResultSet rs = database.read(sb.toString(), conn)) {
            //for each user in the results returned
            while (rs.next()) {
                // place their details into a userdetails object
                user = new UserDetails(rs.getInt("account_no"), rs.getString("firstname"), rs.getString("lastname"), rs.getBlob("password_hash"), rs.getTimestamp("registration_date"), rs.getString("role_description"));
                //add their userdetails object to the arraylist of users
                userList.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return userList;
    }

    // Removes a user from the user table
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM user WHERE account_no = " + userId;

        return database.write(sql, conn) != 0;
    }

    // Updates a given user's role
    public boolean updateUserRole(int userId, int newRole) {
        String sql = "UPDATE user SET Role_role_id = " + newRole + " WHERE account_no = " + userId;

        return database.write(sql, conn) != 0;
    }

    public boolean createNewTask(String description1, int duration1, double price1, String department1, int shelf_slot1) {
        boolean success = false;
        //swapped shelf slots position in the query relative to how it is in the table in workbench (may or may not make a difference)
        String departmentCode = "";
        switch (department1) {
            case "Copy room":
                departmentCode = "CR";
                break; // optional
            case "Dark room":
                departmentCode = "DR";
                break; // optional
            case "Development area":
                departmentCode = "DA";
                break; // optional
            case "Printing room":
                departmentCode = "PR";
                break; // optional
            case "Finshing room":
                departmentCode = "FR";
                break; // optional
            case "Packaging department":
                departmentCode = "PD";
                break; // optional
        }
        //gets the data from the SQL database  
        String SQL = "INSERT INTO TASK(description,duration_min,shelf_slot,price,Department_department_code) VALUES ('" + description1 + "','" + duration1 + "','" + shelf_slot1 + "','" + price1 + "','" + departmentCode + "');";
        try {
            database.write(SQL, conn);
            success = true;
        } catch (Exception e) {
            System.out.println("create new task Error");
        }
        return success;
    }

    // A loop that allows for 100 different shelf slots
    public String[] getShelfSlots() {
        String[] shelfSlots = new String[100];

        for (int i = 0; i < 100; i++) {
            shelfSlots[i] = String.valueOf(i + 1);
        }

        return shelfSlots;
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

    public List<StandardJob> getStandardJobs() {
        List<StandardJob> stdJobs = new ArrayList<>();
        String sql = "select * from standardjob";

        //close resultset after use
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) {
                stdJobs.add(new StandardJob(result.getString("code"), result.getString("job_description"), result.getDouble("price"), result.getInt("duration_min")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return stdJobs;
    }

    public CustomerDetails getLastCustomer() {
        String sql = "SELECT * FROM customer ORDER BY account_no DESC LIMIT 1";

        CustomerDetails customer = null;

        try (ResultSet result = database.read(sql, conn)) {
            if (result.next()) {
                customer = new CustomerDetails(result.getInt("account_no"), result.getString("account_holder_name"), result.getString("prefix"), result.getString("firstname"), result.getString("lastname"), result.getString("street_name"), result.getString("postcode"), result.getString("city"), result.getString("phone"), result.getBoolean("is_suspended"), result.getBoolean("in_default"), result.getBoolean("is_valued"), result.getTimestamp("registration_date"), result.getInt("building_no"), result.getString("email_contact"));

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return customer;
    }

    public boolean createCustomerAccount(String cFirstName, String cLastName, String accountHName, String streetName, String postCode, String city, String phone, String prefix, String buildingNo) {
        String sql = "insert into customer(account_holder_name, prefix,firstname,lastname, street_name,postcode,city,phone) values('" + accountHName + "','" + prefix + "','" + cFirstName + "','" + cLastName + "','" + streetName + "','" + postCode + "','" + city + "','" + phone + "');";

        return database.write(sql, conn) != 0;
    }

    public void recordPayment(PaymentDetails p) {
        String sql = "";
    }

    //Creates a new standard job using the input from the GUI 
    public boolean createStandardJob(String code1, String job_description1, double price1, List<Task> selectedTasks) {
        String sql = "INSERT INTO STANDARDJOB(code, job_description, price) VALUES ('" + code1 + "','" + job_description1 + "','" + price1 + "');";

        if (database.write(sql, conn) != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO standardjob_tasks(StandardJob_code,Task_task_id) VALUES");
            for (Task t : selectedTasks) {
                sb.append("('").append(code1).append("',").append(t.getTaskId()).append("),");
            }
            sb.deleteCharAt(sb.length() - 1);
            database.write(sb.toString(), conn);
            return true;
        }
        return false;
    }

    public boolean acceptJob(String customerId, List<Material> materials, List<StandardJob> stdJobs, double total, UserDetails user, String specialInstructions, String completionTime, String surchargeText, String priority) {
        //get user id user account
        int userId = user.getAccount_no();
        // initialise a statusid variable to store the status id from the results
        String sql;

        int surcharge = Integer.parseInt(surchargeText);
        String[] completiontime = completionTime.split("[\\s]");
        System.out.println(completiontime[0] + completiontime[2] + "00");
        if (priority.equals("Stipulated")) {
            sql = "INSERT INTO completiontime(completion_time, surcharge, Priority_priority_description) VALUES ('" + completiontime[0] + ":" + completiontime[2] + "','" + surcharge + "','" + priority + "');";
            database.write(sql, conn);
        }

        sql = "INSERT INTO job(User_account_no, Customer_account_no,Deadline_CompletionTime_completion_time,special_instructions) VALUES ('" + userId + "','" + customerId + "','" + completiontime[0] + ":" + completiontime[2] + "','" + specialInstructions + "');";

        if (database.write(sql, conn) != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO job_standardjobs(StandardJob_code) VALUES");
            for (StandardJob j : stdJobs) {
                sb.append("('").append(j.getCode()).append("'),");
            }
            sb.deleteCharAt(sb.length() - 1);
            database.write(sb.toString(), conn);

            sb = new StringBuilder();
            sb.append("INSERT INTO material(material_description) VALUES");
            for (Material j : materials) {
                sb.append("('").append(j.getMaterialDescription()).append("'),");
            }
            sb.deleteCharAt(sb.length() - 1);
            database.write(sb.toString(), conn);

            System.out.println(sb.toString());

            return createInvoice(total, surcharge);
        }
        return false;
    }
//to do: we need to generate pdf for invoice, then store it. we also need to print a receipt, but obviously we wont have a printer so just generate a xml page for the receipt or
    //another pdf. then we must alert the office and shift managers of the new job.
    //after this, just implement lukas' branch code and fix it up
    //do report gen stuff tomorrow
    //move on to games tech cw

    private boolean createInvoice(double totalPayable, int surcharge) {
        System.out.println("£" + totalPayable);
        System.out.println(surcharge + " surcharge");

        String sql = "INSERT INTO invoice(total_payable,invoice_location) VALUES (" + totalPayable + ",'invoice copy for customer to take')";

        return database.write(sql, conn) != 0;
    }

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "select * from task";

        //close resultset after use
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) {
                String shelfslot = result.getString("Department_department_code") + result.getString("shelf_slot");
                tasks.add(new Task(result.getInt("task_id"), result.getString("description"), result.getInt("duration_min"), shelfslot, result.getDouble("price")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return tasks;
    }

    public ArrayList<Object[][]> createReport(int reportIndex, String[] reportPeriod, String info) {
        String startDate = reportPeriod[0];
        String finishDate = reportPeriod[1];
        ArrayList<Object[][]> objects = new ArrayList<>();

        switch (reportIndex) {
            case 1:
                System.out.println("Individual performance report");

                IndividualReport iReport = new IndividualReport(new String[]{startDate, finishDate}, info);
                objects = iReport.generate(database, conn);
                break; // optional
            case 2:
                System.out.println("Summary performance report");

                SummaryReport sReport = new SummaryReport(new String[]{startDate, finishDate});
                objects = sReport.generate(database, conn);
                break; // optional
            case 3:
                System.out.println("Customer report");

                CustomerReport cReport = new CustomerReport(new String[]{startDate, finishDate}, info);
                objects = cReport.generate(database, conn);
                break; // optional
        }

        return objects;
    }
    
    /*
    
    
    -----kelvin-------
    
    
    */
    
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
    
    public CustomerDetails[] getAllInDefault() {
        CustomerDetails[] defaultCustomers = new CustomerDetails[getInDefaultSize()];
        
        int i = 0;
        final String sql = "select * from Customer where in_default = 1;";
        
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) {
                CustomerDetails customerInfo = new CustomerDetails(
                        result.getInt("account_no"),
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
                        result.getTimestamp("registration_date"),
                        result.getInt("building_no"),
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
    
    public void reactivateDefaultAccount(CustomerDetails customer, int status) {
        final int customerNo = customer.getAccountNo();
        final String sql = "UPDATE `bapers_data`.`Customer` SET `in_default`=\'"
                + status + "\', "
                + "`is_suspended`=\'"
                + status + "\' "
                + "WHERE `account_no`=\'" + customerNo + "';" ;
        
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
    
    public CustomerDetails[] getAllCustomers() {
        CustomerDetails[] customers = new CustomerDetails[getCustomerListSize()];
        
        int i = 0;
        final String sql = "select * from Customer";
        
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) {
                CustomerDetails customerInfo = new CustomerDetails(
                        result.getInt("account_no"),
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
                        result.getTimestamp("registration_date"),
                        result.getInt("building_no"),
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
    
    public CustomerDetails getSpecificCustomer(CustomerDetails customer) throws SQLException {
        CustomerDetails foundCustomer = null;
        final String sql = "select * from customer where account_no =\'"
                + customer.getAccountNo() + "\'"
                + ";";
        
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            foundCustomer = new CustomerDetails(
                    result.getInt("account_no"),
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
                    result.getTimestamp("registration_date"),
                    result.getInt("building_no"),
                    result.getString("email_contact")
                ); 
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return foundCustomer;
    }
    
    public String getCustomerDiscountType(final CustomerDetails customer) {
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
    
    public void deleteAccount(CustomerDetails customer){
        final String sql = "delete from`customer` where `account_no`=\'"
                + customer.getAccountNo()
                + "\';";
        try {
            database.write(sql, conn);
        } catch (Exception e) {
            System.out.println("Exception error: " + e);
        }
    }
    
    public ArrayList<Invoice> viewAllCustomerInvoce(CustomerDetails customer) {
        ArrayList<Invoice> customerInvoice = new ArrayList<>();
//        final String sql = select * from Invoice where 
        return customerInvoice;
    }
    
    
    
    
    
    
    
    
    
    
    // apply discount 
    public boolean checkCustomerHasDiscountPlan(final CustomerDetails customer) {
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
    
    public void applyDiscountPlan(final CustomerDetails customer, final UserDetails user, final String discountPlan) {
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
                    + user.getAccount_no()+ "\', \'"
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
    
    public void applyFixedDiscountRate(final int fixedDiscountPercentage, final CustomerDetails customer) {
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
    
    public ArrayList<Job> getAllJobsFromCustomer(final CustomerDetails customer) {
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
                        result.getInt("task_id"),
                        result.getString("description"),
                        result.getInt("duration_min"),
                        result.getString("Department_department_code")+result.getString("shelf_slot"),
                        result.getDouble("price")
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
                        result.getInt("task_id"),
                        result.getString("description"),
                        result.getInt("duration_min"),
                        result.getString("Department_department_code")+result.getString("shelf_slot"),
                        result.getDouble("price")
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
