/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.controller;

import bapers.customer.CustomerDetails;
import bapers.database.DBImpl;
import bapers.job.Invoice;
import bapers.job.Material;
import bapers.job.StandardJob;
import bapers.job.Task;
import bapers.payment.PaymentDetails;
import bapers.user.UserDetails;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        String SQL = "SELECT user.account_no, user.firstname, user.lastname, user.password_hash, user.registration_date, role.role_description FROM role INNER JOIN user ON role.role_id = user.Role_role_id WHERE user.account_no ='" + userID + "' and user.password_hash = cast('" + hashPassword + "' as BINARY(32));";

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

    // Attempts to create a new user in the system, given the details are valid
    public boolean createUser(String firstname, String lastName, String password, int roleId) {
        int hashPassword = password.hashCode();
        // Write the user details into the database
        String userSQL = "INSERT INTO USER (firstname,lastname,password_hash,Role_role_id) VALUES ('" + firstname + "','" + lastName + "','" + hashPassword + "','" + roleId + "');";

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
                customer = new CustomerDetails(rs.getInt("account_no"), rs.getString("account_holder_name"), rs.getString("prefix"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("street_name"), rs.getString("postcode"), rs.getString("city"), rs.getString("phone"), rs.getBoolean("is_suspended"), rs.getBoolean("in_default"), rs.getBoolean("is_valued"), rs.getTimestamp("registration_date"), rs.getInt("building_no"));
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
                customer = new CustomerDetails(result.getInt("account_no"), result.getString("account_holder_name"), result.getString("prefix"), result.getString("firstname"), result.getString("lastname"), result.getString("street_name"), result.getString("postcode"), result.getString("city"), result.getString("phone"), result.getBoolean("is_suspended"), result.getBoolean("in_default"), result.getBoolean("is_valued"), result.getTimestamp("registration_date"), result.getInt("building_no"));

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

    public ArrayList<Invoice> getInvoices() throws ParseException {
        String sql = "";

        // putting data into array list
        ArrayList<Invoice> invoices = new ArrayList<>();

        //may be causing depreciated error
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("2012-12-13 14:54:30");

        Invoice i;
        i = new Invoice(2, 2, 20, new Date(), "Awaiting payment", "Test");

        invoices.add(i);
        return invoices;
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

    public boolean createReport(int reportIndex, Date[] reportPeriod, String info) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = sdf.format(reportPeriod[0]);
        String finishDate = sdf.format(reportPeriod[1]);
        String sql;

        System.out.println(finishDate);
        switch (reportIndex) {
            case 1:
                System.out.println("Individual performance report");
                // '2010-01-30 14:15:55'
                // '2010-09-29 10:15:55'
                // need to put time on start date and finish date for the different types of shifts!!!!!
                sql = "SELECT * FROM staff_individual_performance WHERE finish BETWEEN '" + startDate + "' AND '" + finishDate + "'";
                if (!info.equals("Select user... (optional)")) {
                    String[] parts = info.split("\\:");
                    sql += "AND account_no = " + parts[2];
                }
                //close resultset after use
                try (ResultSet result = database.read(sql, conn)) {
                    while (result.next()) {
                        String name = result.getString("firstname") + " " + result.getString("lastname");
                        int taskId = result.getInt("Task_task_id");
                        String stdJobCode = result.getString("Job_StandardJobs_StandardJob_code");
                        String departmentName = result.getString("department_name");
                        System.out.println(name + " : " + taskId + " : " + departmentName);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                break; // optional
            case 2:
                System.out.println("Summary performance report");

                sql = "SELECT * FROM summary_performance WHERE summary_performance.finish BETWEEN '" + startDate + "' AND '" + finishDate + "' ORDER BY summary_performance.finish AND summary_performance.department_name;;";
                //close resultset after use
                try (ResultSet result = database.read(sql, conn)) {
                    while (result.next()) {
                        String department = result.getString("department_name");
                        String start = result.getString("start");
                        String finish = result.getString("finish");
                        System.out.println(department + " : " + start + " : " + finish);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                break; // optional
            case 3:
                System.out.println("Customer report");
                String[] parts = info.split("\\:");
                sql = "SELECT * FROM customer_report WHERE finish BETWEEN '" + startDate + "' AND '" + finishDate + "' AND Customer_account_no = " + parts[2];

                //close resultset after use
                try (ResultSet result = database.read(sql, conn)) {
                    while (result.next()) {
//                         PUT ALL DATA HERE FROM THE CUSTOMER REPORT VIEW, TEST IT WORKS WHEN SUBMITTED IN THE APPLICATION. THEN START CREATING PDF REPORTS BASED ON THE QUERIRES.
//                         ADD MORE DATA TO THE DB TO THOROUGHLY TEST IT WORKS.
                        String dateReceived = result.getString("Deadline_date_received");
                        int jobNo = result.getInt("job_no");
                        double totalPayable = result.getDouble("total_payable");
                        String invoiceStatus = result.getString("invoice_status");
                        System.out.println(jobNo + " : " + dateReceived + " : " + totalPayable + " : " + invoiceStatus);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                break; // optional
        }

        return true;
    }
}
