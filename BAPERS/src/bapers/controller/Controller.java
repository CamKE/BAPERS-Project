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
import bapers.payment.PaymentDetails;
import bapers.user.UserDetails;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
                stdJobs.add(new StandardJob(result.getString("code"), result.getString("job_description"), result.getDouble("price")));
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
    public boolean createStandardJob(String code1, String job_description1, double price1) {
        boolean success = false;
        String SQL = "INSERT INTO STANDARDJOB(code, job_description, price) VALUES ('" + code1 + "','" + job_description1 + "','" + price1 + "');";

        try {
            database.write(SQL, conn);
            success = true;
        } catch (Exception e) {
            System.out.println("create standard job Error");
        }
        return success;
    }

    public boolean acceptJob(String customerId, List<Material> materials, List<StandardJob> stdJobs, double total, UserDetails user, String specialInstructions, String completionTime, String surcharge, String priority) {
        int userId = user.getAccount_no();
        int statusId = 0;
        String sql = "INSERT INTO status(status_type) VALUES ('Not started');";

        if (database.write(sql, conn) != 0) {
            sql = "SELECT status_id FROM status ORDER BY status_id DESC LIMIT 1";
            try (ResultSet result = database.read(sql, conn)) {
                if (result.next()) {
                    statusId = result.getInt("status_id");
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        //CONTINUE HERE. PUT IN THE NORMAL COMPLETION TIME ENTRY, THEN WRAP THE SQL AND WRITE IN AN IF THE DEADLINE IS STIPULATED, THEN WRITE, OTHERWISE, ITS ALREADY THERE, THEN CONTINUE TRYING TO MAKE UP THE JOBS DATA
        String completiontime = completionTime.split("[\\s]")[0];
        int value = Integer.parseInt(surcharge.replaceAll("[\\D]", ""));
        if (priority.equals("Stipulated")) {
            sql = "INSERT INTO completiontime(completion_time, surcharge, Priority_priority_description) VALUES ('" + completiontime + ":0" + "','" + value + "','" + priority + "');";
            database.write(sql, conn);
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, Integer.parseInt(completiontime));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String deadline = dateFormat.format(cal.getTime());
        sql = "INSERT INTO deadline(deadline, CompletionTime_completion_time) VALUES ('" + deadline + "','" + completiontime + ":0" + "');";

        String dateReceived = null;
        if (database.write(sql, conn) != 0) {
            sql = "SELECT date_received FROM deadline ORDER BY date_received DESC LIMIT 1";
            try (ResultSet result = database.read(sql, conn)) {
                if (result.next()) {
                    dateReceived = result.getString("date_received");
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        
        sql = "INSERT INTO job(User_account_no, Customer_account_no, Status_status_id,Deadline_date_received,Deadline_CompletionTime_completion_time,special_instructions) VALUES ('" + userId + "','" + customerId + "','" + statusId + "','" + dateReceived + "','" + completiontime + ":0" + "','" + specialInstructions + "');";

        return database.write(sql, conn) != 0;
    }
}
