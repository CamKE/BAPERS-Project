/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.controller;

import bapers.AutoBackupConfigData;
import bapers.customer.CustomerDetails;
import bapers.database.DBImpl;
import bapers.discountplan.Discount;
import bapers.discountplan.DiscountBand;
import bapers.discountplan.FixedDiscount;
import bapers.discountplan.FlexibleDiscount;
import bapers.discountplan.VariableDiscount;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

import bapers.payment.Card;
import bapers.payment.PaymentCard;

/*Joseph imports*/
import java.sql.Time;
//import java.sql.Date;
import bapers.payment.LatePaymentInvoice;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author CameronE
 */
public class Controller {

    final private DBImpl database;
    final private Connection conn;
    private ResultSet rs;

    // Joseph: 
    //Create array list of JobDetails to store into table
    private ArrayList<Job> jobList = new ArrayList<>();
    //Create array list of tasks to store into standard job
    private ArrayList<Task> standardJobTasksList = new ArrayList<>();
    private ArrayList<Task> taskList = new ArrayList<>();
    //Create array list of standardJobs to store into job
    private ArrayList<StandardJob> standardJobList = new ArrayList<>();

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
    public boolean createUser(String firstname, String lastName, String password, int roleId, String username, String securityAnswer) {
        int hashPassword = password.hashCode();
        // Write the user details into the database
        String userSQL = "INSERT INTO USER (firstname,lastname,password_hash,Role_role_id,username,security_answer) \n"
                + "VALUES ('" + firstname + "','" + lastName + "','" + hashPassword + "','" + roleId + "','" + username + "','" + securityAnswer + "' );";

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

    public ArrayList<CustomerDetails> findCustomer(String customerNumber, String cFirstName, String cLastName, String accountHName, String streetName, String postCode, String city, String phone, String customerType, String customerStatus, String inDefault, Date regDate) {
        StringBuilder sb = new StringBuilder();
        customerNumber = customerNumber.replaceAll("\\s+", "");
        // 1=1 is ignored by sql. it allows for ease of adding conditions to the statement (AND)
        sb.append("SELECT * from customer WHERE 1=1 ");

        // The sql statement is constructed based on the values given
        if (!customerNumber.isEmpty()) {
            sb.append("AND account_no = ").append(Integer.parseInt(customerNumber));
        } else {
            if (!cFirstName.isEmpty()) {
                sb.append(" AND firstname LIKE '%").append(cFirstName).append("%'");
            }
            if (!cLastName.isEmpty()) {
                sb.append(" AND lastname LIKE '%").append(cLastName).append("%'");
            }
            if (!accountHName.isEmpty()) {
                sb.append(" AND account_holder_name LIKE '%").append(accountHName).append("%'");
            }
            if (!streetName.isEmpty()) {
                sb.append(" AND street_name LIKE '%").append(streetName).append("%'");
            }
            if (!postCode.isEmpty()) {
                sb.append(" AND postcode = '").append(postCode).append("'");
            }
            if (!city.isEmpty()) {
                sb.append(" AND city LIKE '%").append(city).append("%'");
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (regDate != null) {
                String regdate = sdf.format(regDate);
                sb.append(" AND DATE(registration_date) = '").append(regdate).append("'");
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
                sb.append(" AND firstname LIKE '%").append(firstName).append("%'");
            }
            if (!lastName.isEmpty()) {
                sb.append(" AND lastname LIKE '%").append(lastName).append("%'");
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
            case "Development area":
                departmentCode = "DR";
                break; // optional
            case "Finishing room":
                departmentCode = "FR";
                break; // optional
            case "Packaging department":
                departmentCode = "PR";
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
    public String[] getShelfSlots(String department) {
        List<String> shelfSlots = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            shelfSlots.add(String.valueOf(i + 1));
        }

        List<String> takenSlots = new ArrayList<>();

        String sql = "SELECT shelf_slot FROM task WHERE Department_department_code = '" + department + "';";

        try (ResultSet rs = database.read(sql, conn)) {
            while (rs.next()) {
                takenSlots.add(rs.getString("shelf_slot"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        for (String takenSlot : takenSlots) {
            shelfSlots.remove(takenSlot);
        }

        String[] slotsArr = new String[shelfSlots.size()];
        slotsArr = shelfSlots.toArray(slotsArr);

        return slotsArr;
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
        List<Task> tasks = new ArrayList<>();
        String sql = "select * from standardjobs";

        //close resultset after use
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            String stdJobCode = result.getString("code");
            String jobDescription = result.getString("job_description");
            double price = result.getDouble("total_price");
            int durationMin = result.getInt("total_duration");
            tasks.add(new Task(result.getInt("task_id"), result.getString("description"), result.getInt("duration_min"), result.getString("Department_department_code") + result.getString("shelf_slot"), result.getDouble("price")));

            while (result.next()) {
                if (stdJobCode.equals(result.getString("code"))) {
                    tasks.add(new Task(result.getInt("task_id"), result.getString("description"), result.getInt("duration_min"), result.getString("Department_department_code") + result.getString("shelf_slot"), result.getDouble("price")));
                } else {
                    stdJobs.add(new StandardJob(stdJobCode, jobDescription, price, durationMin, tasks));
                    stdJobCode = result.getString("code");
                    jobDescription = result.getString("job_description");
                    durationMin = result.getInt("duration_min");
                    price = result.getDouble("total_price");
                    tasks = new ArrayList<>();
                    tasks.add(new Task(result.getInt("task_id"), result.getString("description"), result.getInt("duration_min"), result.getString("Department_department_code") + result.getString("shelf_slot"), result.getDouble("price")));
                }
            }
            stdJobs.add(new StandardJob(stdJobCode, jobDescription, price, durationMin, tasks));

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

    public boolean createCustomerAccount(String cFirstName, String cLastName, String accountHName, String streetName, String postCode, String city, String phone, String prefix, String buildingNo,String email) {
        String sql = "insert into customer(account_holder_name, prefix,firstname,lastname, street_name,postcode,city,phone,email_contact) values('" + accountHName + "','" + prefix + "','" + cFirstName + "','" + cLastName + "','" + streetName + "','" + postCode + "','" + city + "','" + phone + "','" + email + "');";

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

    public boolean acceptJob(String customerId, List<Material> materials, List<StandardJob> stdJobs, UserDetails user, String specialInstructions, String completionTime, String surchargeText, String priority) {
        //get user id user account
        int userId = user.getAccount_no();
        int surcharge = Integer.parseInt(surchargeText);
        String[] completiontime = completionTime.split("[\\s]");
        Job job = new Job(materials, stdJobs, customerId, userId, specialInstructions, completiontime, surcharge, priority);
        double total = applyDiscount(job);
        int response = JOptionPane.showConfirmDialog(null, "Total : £" + String.format("%.2f", total) + " (including surcharge " + surcharge + "% , VAT (20%) and applicable discount). Is the customer happy to proceed?");
        if (response == 0) {
            // initialise a statusid variable to store the status id from the results
            return job.create(database, conn, total);
        }
        return false;
    }
//to do: we need to generate pdf for invoice, then store it. we also need to print a receipt, but obviously we wont have a printer so just generate a xml page for the receipt or
    //another pdf. then we must alert the office and shift managers of the new job.
    //after this, just implement lukas' branch code and fix it up
    //do report gen stuff tomorrow
    //move on to games tech cw

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

	    public boolean createFixedDiscount(int accountNo, float fixedDiscount, int userAccountNo) {
        FixedDiscount discount = new FixedDiscount(accountNo, fixedDiscount);
        return discount.create(database, conn, userAccountNo);
    }
	
	    public boolean createFlexibleDiscount(int accountNo, List<DiscountBand> bands, int userAccountNo) {
        FlexibleDiscount discount = new FlexibleDiscount(accountNo, bands);
        return discount.create(database, conn, userAccountNo);
			
    }
	
	    public boolean createVariableDiscount(int accountNo, List<Task> tasks, int userAccountNo) {
        VariableDiscount discount = new VariableDiscount(accountNo, tasks);
        return discount.create(database, conn, userAccountNo);
				
    }
	
	    public double applyDiscount(Job job) {
        CustomerDetails selectedCustomer = findCustomer(job.getCustomerAccountNo(), null, null, null, null, null, null, null, null, null, null, null).get(0);

        if (selectedCustomer.getIsValued()) {
            Discount custDiscountPlan = findDiscountPlan(selectedCustomer.getAccountNo());
										 

            if (custDiscountPlan != null) {
                return custDiscountPlan.applyDiscount(job);												
            }
        }
        return job.calculateTotal();
    }
	
	    public Discount findDiscountPlan(int accountNo) {
        String sql = "select * from discountplan where Customer_account_no =" + accountNo;
        Discount d = null;
        //close resultset after use
										  
        try (ResultSet result = database.read(sql, conn)) {
            if (result.next()) {
                switch (result.getString("discount_type")) {
                    case "Fixed":
                        d = getFixedDiscount(accountNo);
                        break;
                    case "Flexible":
                        String currentBand = result.getString("current_band");
                        int input = 0;
                        if (currentBand == null) {
                            input = JOptionPane.showConfirmDialog(null, "The current applicable discount band has not been set. Would you like it to be calculated?");
                            if (input == 0) {
                                calculateBand(accountNo);
                            } else {
                                JOptionPane.showMessageDialog(null, "No discount has been applied.");
                            }
                        }
                        if (currentBand != null || input == 0) {
                            d = getFlexibleDiscount(accountNo);
                        }
                        break;
                    case "Variable":
                        d = getVariableDiscount(accountNo);
                        break;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
						
	 

        return d; 
    }
	
	    private void calculateBand(int accountNo) {
        String sql = "SELECT SUM(total_payable) AS total FROM invoice INNER JOIN job ON job.job_no = invoice.Job_job_no WHERE job.Customer_account_no = " + accountNo + " AND invoice.date_issued BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 MONTH) AND CURDATE()";
        double total = 0;

        try (ResultSet result = database.read(sql, conn)) {
            if (result.next()) {
                total = result.getDouble("total");

            }
								 
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println(total);
        sql = "UPDATE discountplan SET current_band = (SELECT band.band_no FROM band WHERE DiscountPlan_Customer_account_no = " + accountNo + " AND ((band.lower_bound < " + total + " AND band.upper_bound >= " + total + ") OR (band.lower_bound < " + total + " AND band.upper_bound is NULL) OR (band.lower_bound is NULL AND band.upper_bound >= " + total + ")) ORDER BY band.discount_rate DESC LIMIT 1) WHERE discountplan.Customer_account_no = " + accountNo + ";";

        database.write(sql, conn);
    }
	
	    public FixedDiscount getFixedDiscount(int accountNo) {
        FixedDiscount fd = null;
        String sql = "select * from fixeddiscount where DiscountPlan_Customer_account_no =" + accountNo;
        //close resultset after use
																		  

        try (ResultSet result = database.read(sql, conn)) {
            if (result.next()) {
                fd = new FixedDiscount(accountNo, result.getFloat("discount_rate"));	
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return fd;
    }
	
	    public FlexibleDiscount getFlexibleDiscount(int accountNo) {
        FlexibleDiscount fd = null;
        String sql = "select * from band INNER JOIN discountplan ON discountplan.Customer_account_no = band.DiscountPlan_Customer_account_no where band.DiscountPlan_Customer_account_no =" + accountNo;
        List<DiscountBand> bands = new ArrayList<>();
							  
        int currentBand = 0;
        //close resultset after use
																	

        try (ResultSet result = database.read(sql, conn)) {
											   
            while (result.next()) {
																   
                currentBand = result.getInt("current_band");
                bands.add(new DiscountBand(result.getDouble("lower_bound"), result.getDouble("upper_bound"), result.getFloat("discount_rate")));
                bands.get(bands.size() - 1).setBandNo(result.getInt("band_no"));
	
            }

            //these two lines arent executed due to end of result set, and it will no execute in the catch. figure it out, and make sure this discount works. then do stuff from notepad++
            fd = new FlexibleDiscount(accountNo, bands);
            fd.setCurrentBand(currentBand);
            System.out.println("DONE");

        } catch (SQLException ex) {
            System.out.println(ex);					   
        }

        return fd;
    }
	
	    public VariableDiscount getVariableDiscount(int accountNo) {
        VariableDiscount vd = null;
        String sql = "select * from discountplan_tasks where DiscountPlan_Customer_account_no =" + accountNo;
        List<Task> tasks = getTasks();

        //close resultset after use
															  
        try (ResultSet result = database.read(sql, conn)) {
            int i = 0;
            while (result.next()) {
                tasks.get(i).setDiscountRate(result.getFloat("discount_rate"));
                i++;
 
            }
            vd = new VariableDiscount(accountNo, tasks);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return vd;
 
    }
	
	    public void sendAlert(int roleID, String message, String alertType) {
        String SQL = "INSERT IGNORE INTO alert (alert_type,role,message)\n"
                + "VALUES ('" + alertType + "','" + roleID + "','" + message + "');";
        database.write(SQL, conn);
    }
	
	    public String getAlerts(int roleID, String alertType) {
        StringBuilder messages = new StringBuilder();
        String message = "";
        switch (alertType) {
            case "New job alert":
                message = "The following customers have had jobs accepted: ";
                messages.append(message).append("\n");
                break;
            case "Job deadline alert":
                message = "The following job are likely to exceed their deadlines: ";
                messages.append(message).append("\n");
                break;
            case "Late Payment":
                message = "Updates: ";
                messages.append(message).append("\n");
                break;
        }

        String SQL = "SELECT * FROM alert WHERE role = '" + roleID + "' AND alert_type = '" + alertType + "';";
        int alertCount = 0;
        try (ResultSet rs = database.read(SQL, conn)) {
            while (rs.next()) {
                message = rs.getString("message");
                messages.append(message).append("\n");
                alertCount++;
            }
        } catch (Exception e) {
            System.out.println("View Alert error");
        }

        if (alertCount == 0) {
            return null;
        } else {
            return messages.toString();
        }
    }
	
	    public void deleteAlerts(int roleID) {
        String SQL = "DELETE FROM alert WHERE role = '" + roleID + "';";
        database.write(SQL, conn);
    }
	
	    public void checkDeadlineApproaching() {
        String sql = "SELECT job_no, deadline.deadline FROM job INNER JOIN status ON status.status_id = job.Status_status_id INNER JOIN deadline ON deadline.date_received = job.Deadline_date_received WHERE status.status_type <> 'Completed'";
        Map<Integer, Timestamp> jobs = new HashMap<>();

        try (ResultSet rs = database.read(sql, conn)) {
            while (rs.next()) {
                jobs.put(rs.getInt("job_no"), rs.getTimestamp("deadline"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        for (Map.Entry<Integer, Timestamp> entry : jobs.entrySet()) {
            System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
            int remainingDuration = 0;
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Calendar c = Calendar.getInstance();

            sql = "SELECT SUM(duration_min) as totaljobtime FROM job_standardjobs_tasks INNER JOIN status ON status.status_id = job_standardjobs_tasks.Status_status_id INNER JOIN task ON task.task_id = job_standardjobs_tasks.Task_task_id WHERE status.status_type <> 'Completed' AND Job_StandardJobs_Job_job_no = " + entry.getKey() + " GROUP BY task.Department_department_code ORDER BY totaljobtime DESC LIMIT 1";

            try (ResultSet rs = database.read(sql, conn)) {
                if (rs.next()) {
                    remainingDuration = rs.getInt("totaljobtime");
                }
            } catch (Exception e) {
                System.out.println("View Alert error");
            }

            c.add(Calendar.MINUTE, remainingDuration);
            Timestamp estimatedDuration = new Timestamp(c.getTimeInMillis());
            if (estimatedDuration.after(entry.getValue())) {
                sendAlert(1, "Job no : " + entry.getKey() + ", Deadline: " + dateFormat.format(entry.getValue()), "Job deadline alert");
                sendAlert(2, "Job no : " + entry.getKey() + ", Deadline: " + dateFormat.format(entry.getValue()), "Job deadline alert");
            }
        }

    }
	
	    public int getTotalJobTime(List<StandardJob> stdJobs) {
        StringBuilder sb = new StringBuilder();
        int jobTime = 0;

        sb.append("SELECT SUM(duration_min) AS totaljobtime FROM task INNER JOIN standardjob_tasks ON Task.task_id = standardjob_tasks.Task_task_id WHERE StandardJob_code IN(");
        for (StandardJob s : stdJobs) {
            sb.append("'").append(s.getCode()).append("',");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(") GROUP BY task.Department_department_code ORDER BY totaljobtime DESC LIMIT 1;");

        try (ResultSet rs = database.read(sb.toString(), conn)) {
            if (rs.next()) {
                jobTime = rs.getInt("totaljobtime");
            }
        } catch (Exception e) {
            System.out.println("View Alert error");
        }

        return jobTime;
    }
    /*
    
    
    -----kelvin-------
    
    
     */
            
    // auto backup code
    public boolean checkAutoBackupConfigExist() {
        final String sql = "select * from backupsettings;";
        int size = 0;
        
        
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) ++size;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return size > 0;
    }
    
    //Get auto back up config data from database and set it into class
    public AutoBackupConfigData getAutoBackupConfigData() {
        final String sql = "select * from backupsettings;";
        AutoBackupConfigData autoData = null;
        
        try (ResultSet result = database.read(sql, conn)) {
            result.next(); // gets only the first row in the table
            autoData = new AutoBackupConfigData(
                    result.getString("backup_mode"), 
                    result.getString("backup_frequency"), 
                    result.getString("backup_location"),
                    result.getDate("date_performed"),
                    result.getDate("target_date")
            );
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return autoData;
    }
    
    //Record back up config data
    public boolean setAutoBackupConfig(final AutoBackupConfigData config) {
        boolean complete = false;
        final String datePerformed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(config.getDatePerformed());
        
        //If nothing exists then record new data
        if (checkAutoBackupConfigExist() == false) {
            final String sql = "INSERT INTO `bapers_data`.`backupsettings` "
                    + "(`backup_mode`, `backup_frequency`, `backup_location`, `date_performed`, `target_date`) "
                    + "VALUES (\'"
                    + config.getBackupMode().toLowerCase() + "\', \'"
                    + config.getBackupFrequency().toLowerCase() + "\', \'"
                    + config.getBackupLocation() + "\', \'"
                    + datePerformed + "\', '"
                    + datePerformed + "\');";

            try {
                database.write(sql, conn);
                complete = true;
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
        } else {
            //update current data
            final AutoBackupConfigData oldConfigData = getAutoBackupConfigData();
            final String sql = "update `BAPERS_data`.`backupsettings` set"
                    + "`backup_mode`=" + "\'" + config.getBackupMode() + "\', " 
                    + "`backup_frequency`=" + "\'" + config.getBackupFrequency() + "\', " 
                    + "`backup_location`=" + "\'" + config.getBackupLocation() + "\', "
                    + "`date_performed`= " + "\'" + datePerformed + "\', " 
                    + "`target_date`= " + "\'" + datePerformed + "\'"
                    + "where `backup_mode`=" + "\'" + oldConfigData.getBackupMode() + "\'"
                    + ";";

            try {
                database.write(sql, conn);
                complete = true;
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
        }
        return complete;
    }
    
    
    
    
    
    
    
    
    
    
    
    // payment code
    public ArrayList<Invoice> getInvoices() throws ParseException {
        // sql select statemnet to get invoice
        final String sql = "select * from bapers_data.invoice;";
        
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
                // only adds if the status of the invoice is AWAITINGPAYMENT.
                if(invoice.getInvoiceStatus().equals(Invoice.Status.AWAITINGPAYMENT))
                    invoices.add(invoice);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return invoices;
    }
    
    public boolean recordPayment(final Payment p, final String type, final Invoice i, final Card card) {
        boolean completed = false;
        // boolean variable too check if payment is recorded before changed the status of its invoice
        boolean paymentRecorded = false;
        
        // checks to see if payment type is card
        if (type.equals("Card")) {
            
            // records card details if needed.
            recordCardDetails(p, i, card);

            // standard payment information
            final String paymentNo = p.getPaymentNo();
            final double total = p.getTotal();
            final String paymentType = p.getPaymentType();
            final String paymentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(p.getPaymentDate());
            final int invoiceNumber = p.getInvoiceNumber();

            // card information
            final String cardDetailsLast4digits = ((PaymentCard) p).getCardDetailsLast4Digits();
            final String cardDetailsExpiryDate = ((PaymentCard) p).getCardDetailsExpiryDate();

            // sql insert statement for card payment
            final String sql = "insert into paymentrecord values ("
                    + paymentNo + ","
                    + total + ", \'"
                    + paymentType + "\', \'"
                    + paymentDate + "\', \'"
                    + invoiceNumber + "\', \'"
                    + cardDetailsLast4digits + "\', \'"
                    + cardDetailsExpiryDate + "\');";
            
            try {
                database.write(sql, conn);
                // set payment to recorded to true & changes invoice status
                paymentRecorded = true;
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
            
            //System.out.println(paymentRecorded);
            if (changeInvoiceStatus(paymentRecorded, p, i))
                completed = true;
        }
        
        return completed;
    }
    
    public boolean recordPayment(final Payment p, final String type, final Invoice i) {
        boolean completed = false;
        // boolean variable too check if payment is recorded before changed the status of its invoice
        boolean paymentRecorded = false;
        
        // checks to see if payment type is cash 
        if (type.equals("Cash")) {
            // standard payment information
            final String paymentNo = p.getPaymentNo();
            final double total = p.getTotal();
            final String paymentType = p.getPaymentType();
            final String paymentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(p.getPaymentDate());
            final int invoiceNumber = p.getInvoiceNumber();
            
            // sql insert statement for cash payment
            final String sql = "insert into paymentrecord values (" 
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
                // set payment to recorded to true & changes invoice status
                paymentRecorded = true;
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
            
            //System.out.println(paymentRecorded);
            if (changeInvoiceStatus(paymentRecorded, p, i))
                completed = true;
        }
        
        return completed;
    }
    
    public void recordCardDetails(final Payment p, final Invoice invoice, final Card card) {
        // returns boolean value to check if card details exist in database
        final boolean cardRecorded = checkCardAlreadyRecorded(card);
        
        if (cardRecorded == false) {
            // sql insert statement to record card details
            final String sql = "insert into carddetails values(" + "\'"
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
        // sql select statement to get job based on invoice
        final String sql = "select * from job where job_no = " + invoice.getJobJobNo();
        
        // gets customer based on the job of the invoice 
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
        // sql select statement to check if there is exist a card detail with information provided by card arg
        final String sql = "select count(*) from carddetails where last4digits = \'" 
                + card.getLast4Digits() + "\' and card_type = \'"
                + card.getCardType() + "\' and expiry_date = \'"
                + card.getExpiryDate() + "\';"
                ;
        
        // sets cardRecorded variable to true or false based on count result (0 = false and 1||> = true)
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            cardRecorded = result.getBoolean("count(*)");
            //System.out.println(cardRecorded);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return cardRecorded;
    }
    
    public boolean changeInvoiceStatus(final boolean paymentRecorded, final Payment p, final Invoice i) {
        boolean completed = false;
        // checks to see if payment is recorded
        if (paymentRecorded == true) {
            final String status = paidOnTIme(p, i);
            //System.out.println(status);
            
            // sql update statement to change the selected invoice with new status
            final String sql = "update bapers_data.invoice set invoice_status = \'"
                    + status
                    + "\' where `Invoice_no` = " + '\'' 
                    + p.getInvoiceNumber()+ '\'' + ";" // here is the problem, no number to reference.
                    ;
            try {
                database.write(sql, conn);
                completed = true;
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
        }
        
        return completed;
    }
    
    public String paidOnTIme(final Payment p, final Invoice i) {
        // string variable used to determine the value of the status in the invoice table
        String invoiceStatus;
        
        // gets the current date based on the time payment 
        final Date paymentDate = p.getPaymentDate();
        
        // gets and sets the invoice date to be used to check late payment
        final Date invoiceDate = (Date) i.getDateIssued();
        final DateFormat invoiceDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        final String newInvoiceDate = invoiceDateFormat.format(invoiceDate);
        
        // splits the date to tokens based on "-".
        // Month is already increase by 1 which allows late payment to be checked
        String[] token = newInvoiceDate.split("-");
        final int year = Integer.parseInt(token[0]);
        final int month = Integer.parseInt(token[1]);
        final int hour = Integer.parseInt(token[3]);
        final int min = Integer.parseInt(token[4]);
        final int sec = Integer.parseInt(token[5]);
        
        
        // sets new Date variable to next month of the invoice date, 
        // used to compare with payment date.
        Calendar tenthNxtInvoceMonth = Calendar.getInstance();
        // date
        tenthNxtInvoceMonth.set(Calendar.DATE, 10);
        tenthNxtInvoceMonth.set(Calendar.MONTH, month);
        tenthNxtInvoceMonth.set(Calendar.YEAR, year);
        // time
        tenthNxtInvoceMonth.set(Calendar.HOUR, hour);
        tenthNxtInvoceMonth.set(Calendar.MINUTE, min);
        tenthNxtInvoceMonth.set(Calendar.SECOND, sec);
        
        // compares if current payment date(today) and next 10th month,
        // to see if the payment date exceeds the next 10th month date
        // if that is true, sets invoiceStatus variable to Paid late and
        // if false sets the invoiceStatus variable to Paid on time.
        if ( paymentDate.compareTo(tenthNxtInvoceMonth.getTime()) > 0 )
            invoiceStatus = "Paid late";
        else 
            invoiceStatus = "Paid on time";
        return invoiceStatus;
    }
    
    
    
    
    
    
    
    
    
    
    
    // re-activate customer
    public int getInDefaultSize() {
        // int variable used to determine size of customers who are in default.
        int size = 0;
                
        // sql select statement used to count number of customers in default.
        final String sql = "select count(*) as size from customer where in_default = 1;";
        
        // reads first result and assigns size variable with value attained from sql statement
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            size = result.getInt("size");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return size;
    }
    
    public CustomerDetails[] getAllInDefault() {
        // CustomerDetails variable used to store all default customers.
        // Size is given from the getInDefaultSize() method/function.
        CustomerDetails[] defaultCustomers = new CustomerDetails[getInDefaultSize()];
        
        int i = 0; // int variable to index current location in the array
        
        // sql select statement to get all customers that are in default
        final String sql = "select * from customer where in_default = 1;";
        
        // loops through each row to find all results of customers that are in default
        // gets the column values based on the name of the column from the table based on the row
        // and passes that as an arguement for the CustomerDetails class.
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
                
                // adds the created customer details to the array on the based index
                defaultCustomers[i] = customerInfo;
                
                ++i; // increase the index
                //15
            }   
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return defaultCustomers;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    // view all customers
    public int getCustomerListSize() {
        // int variable to determine amount of customers in customer table.
        int size = 0;
        
        // sql select statement used to count the amount of customers.
        final String sql = "select count(*) as size from customer;";
        
        // gets the result and passes it on to the size variable.
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            size = result.getInt("size");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return size;
    }
    
    public CustomerDetails[] getAllCustomers() {
        // CustomerDetails variable used to store all customers.
        // Size is given from the getCustomerListSize() method/function.
        CustomerDetails[] customers = new CustomerDetails[getCustomerListSize()];
        
        int i = 0; // int variable to index current location in the array.
        
        // sql select statement to get all customers.
        final String sql = "select * from customer";
        
        // loops through each row to find all results of customers and gets the 
        // column values based on the name of the column from the table based on the row
        // and passes that as an arguement for the CustomerDetails class.
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
                // adds the created customer details to the array on the based index.
                customers[i] = customerInfo;
                
                ++i; // increase the index.
                //15
            }   
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return customers;
    }
    
    public CustomerDetails getSpecificCustomer(CustomerDetails customer) throws SQLException {
        // customer variable used to return the specific found in the database.
        CustomerDetails foundCustomer = null;
        
        // sql select statement to get the customer based on the customer No from
        // the customer given in the method/function arg.
        final String sql = "select * from customer where account_no =\'"
                + customer.getAccountNo() + "\'"
                + ";";
        
        // records the found customer to a CustomerDetail class and returns the customer.
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
        final String sql = "select * from discountplan where discountplan.customer_account_no =\'" 
                + customer.getAccountNo() + "\';";
        
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            type = result.getString("discount_type");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return type;
    }
    
    
    public boolean foreignKeyCheckZero() {
        boolean completed = false;
        final String sql = "SET FOREIGN_KEY_CHECKS = 0;";
        
        try {
            database.write(sql, conn);
            completed = true;
        } catch (Exception e) {
            System.out.println("Exception error: " + e);
        }
        
        return completed;
    }
    
    public boolean foreignKeyCheckOne() {
        boolean completed = false;
        final String sql = "SET FOREIGN_KEY_CHECKS = 1;";
        
        try {
            database.write(sql, conn);
            completed = true;
        } catch (Exception e) {
            System.out.println("Exception error: " + e);
        }
        
        return completed;
    }
    
    public boolean deleteAccount(CustomerDetails customer){
        boolean completed = false;
        if (foreignKeyCheckZero()) {
            final String sql = "DELETE FROM customer WHERE account_no = "
                    + customer.getAccountNo() + ";";
            try {
                database.write(sql, conn);
            } catch (Exception e) {
                System.out.println("Exception error: " + e);
            }
            if (foreignKeyCheckOne())
                completed = true;
        }
        return completed;
    }
    
    public void updateCustomerStatus(final CustomerDetails customer, int suspendedvalue, int defaultValue) {
        final String sql = "update `bapers_data`.`customer` set `is_suspended`=\'"
                + suspendedvalue
                + "\', `in_default`=\'"
                + defaultValue
                + "\' where `account_no`=\'"
                + customer.getAccountNo()
                + "\';";
        
        try {
            database.write(sql, conn);
        } catch (Exception e) {
            System.out.println("Exception error: " + e);
        }
    }
    
    public void updateCustomerType(final CustomerDetails customer, int value) {
        final String sql = "update `bapers_data`.`customer` set `is_valued`=\'"
                + value
                + "\' where `account_no`=\'"
                + customer.getAccountNo()
                + "\';";
        
        try {
            database.write(sql, conn);
        } catch (Exception e) {
            System.out.println("Exception error: " + e);
        }
    }
    
    
    
    
    
    
    
    // apply discount 
    public boolean checkCustomerHasDiscountPlan(final CustomerDetails customer) {
        final String sql = "select count(*) as 'Has discount' from discountplan where discountplan.customer_account_no =" 
                + customer.getAccountNo() + ";";
        
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            return result.getBoolean("Has Discount");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
    
    public void applyDiscountPlan(final CustomerDetails customer, final UserDetails user, final String discountPlan) {
        final boolean hasDiscountPlan = checkCustomerHasDiscountPlan(customer);
        String sql;
        
        // update or insert discount plan
        if (hasDiscountPlan == true) 
            sql = "update `bapers_data`.`discountplan` set `discount_type`=\'" 
                    + discountPlan + "\' where `customer_account_no`=\'" 
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
    }
    
    public void applyFixedDiscountRate(final int fixedDiscountPercentage, final CustomerDetails customer) {
        final String sql = "insert into bapers_data.fixeddiscount values( \'" 
                + fixedDiscountPercentage + "\', \'"
                + customer.getAccountNo() + "\'"
                + ");";
        
        try {
            database.write(sql, conn);
        } catch (Exception e) {
            System.out.println("Exception error: " + e);
        }
    }
    
    public ArrayList<Job> getAllJobsFromCustomer(final CustomerDetails customer) {
        ArrayList<Job> jobs = new ArrayList<>();
        final String sql = "select * from bapers_data.job\n" 
                + "inner join status\n" 
                + "where status.status_id = job.Status_status_id\n" 
                + "and job.Customer_account_no =\'"
                + customer.getAccountNo()
                + "\';";
        
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
    
    public ArrayList<Invoice> getAllInvoiceRelatedToJob(final ArrayList<Job> j) {
        ArrayList<Invoice> customerInvoices = new ArrayList<>();
        String[] sqlQ = new String[j.size()];
        for(int i = 0; i < j.size(); ++i)
            sqlQ[i] = "select * from invoice where Job_job_no =\'" + j.get(i).getJobNo() + "\';";
        
        
        String sql;
        
        for(int i = 0; i < j.size(); ++i) {
            sql = sqlQ[i];
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

                    customerInvoices.add(invoice);
                }   
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        
        return customerInvoices;
    }
    
    public ArrayList<JobStandardJob> getAllStandardJobFromJob(final Job jobNo) {
        ArrayList<JobStandardJob> stdJobs = new ArrayList<>();
        final String sql = "select * from job_standardJobs " 
                + "inner join status " 
                + "where job_standardjobs.status_status_id = status.status_id " 
                + "and job_standardJobs.job_job_no =\'" 
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
        final String sql = "select * from job_standardjobs_tasks where job_standardjobs_standardJob_code =\'" 
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
                final String sql = "select * from task where task_id = \'"
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
        final String sql = "select * from task";
        
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) {
                Task task = new Task(
                        result.getInt("task_id"),
                        result.getString("description"),
                        result.getInt("duration_min"),
                        result.getString("Department_department_code") + result.getString("shelf_slot"),
                        result.getDouble("price")
                );
                tasks.add(task);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return tasks;
    }
    

    /*
    
  Joseph
    
    
    
     */
    public ArrayList<Task> getTasksArrayList() {
        //Get information from task table from the database
        String SQL = "SELECT * FROM task;";
        rs = database.read(SQL, conn);

        //Create array list of tasks
        ArrayList<Task> taskInfo = new ArrayList<>();
        Task task;

        //Get task infromatino from database and set it to new task instance(s)
        try {
            while (rs.next()) {
                //Convert department code to department name
                String departmentName = this.getDepartmentName(rs.getString("Department_department_code"));
                //Create new task 
                task = new Task(rs.getInt("task_id"), rs.getString("description"), rs.getInt("duration_min"), rs.getInt("shelf_slot"), rs.getDouble("price"),
                        departmentName);
                taskInfo.add(task);
            }
        } catch (Exception e) {
            System.out.println("update task table Error");
        }
        return taskInfo;
    }

    public String getDepartmentName(String departmentCode) {

        //Convert department code to department name
        String departmentName = "";
        switch (departmentCode) {
            case "CR":
                departmentName = "Copy Room";
                break; // optional
            case "DR":
                departmentName = "Development Area";
                break; // optional

            case "PR":
                departmentName = "Packaging Department";
                break; // optional
            case "FR":
                departmentName = "Finishing Room";
                break; // optional

        }
        return departmentName;
    }

    public void updateTaskStatus(String standardJobCode, int taskID, String status) {
        this.getStandardJobTasks(standardJobCode).get(taskID).setStatus(status);
    }

    /**
     * @param standardJobCode
     * @return the standardJobTasks
     */
    public ArrayList<Task> getStandardJobTasks(String standardJobCode) {
        return standardJobTasksList;
        //return standardJob.getStandardJobTasks();
    }

    /**
     * @param standardJobTasks the standardJobTasks to set
     */
    public void setStandardJobTasks(ArrayList<Task> standardJobTasks) {
        this.standardJobTasksList = standardJobTasks;
    }

    public void updateTaskStatusInDatabase(String statusType, int taskID, String time) {
        int statusID = this.getTaskList().get(taskID).getStatusID();
        this.getTaskList().get(taskID).setStatus(statusType);
        String SQL = "";
        switch (statusType) {
            case ("Completed"):
                SQL = "Update status\n"
                        + "SET  finish = '" + time + "', status_type = '" + statusType + "'\n"
                        + "WHERE status_id = '" + statusID + "';";
                break;
            case ("In progress"):
                SQL = "Update status\n"
                        + "SET  start = '" + time + "', status_type = '" + statusType + "'\n"
                        + "WHERE status_id = '" + statusID + "';";
                break;
            case ("Not started"):
                SQL = "Update status\n"
                        + "SET status_type = '" + statusType + "'\n"
                        + "WHERE status_id = '" + statusID + "';";
                break;
        }
        database.write(SQL, conn);
    }

    public void setTechnicianWhoCompletedTask(int accountNumber, String standardJobCode, int jobNumber, int taskID) {
        String SQL = "Update job_standardjobs_tasks\n"
                + "SET User_account_no = '" + accountNumber + "'\n"
                + "WHERE Job_StandardJobs_StandardJob_code = '" + standardJobCode + "' AND Task_task_id = '" + taskID + "' AND Job_StandardJobs_Job_job_no = '" + jobNumber + "';";
        database.write(SQL, conn);
    }

    /**
     * @return the taskList
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void updateStandardJobStatus(int jobNumber, int standardJobIndex, String status, String time) {
        this.getStandardJobList().get(standardJobIndex).setStatus(status);
        int statusID = (this.getStandardJobList().get(standardJobIndex).getStatusID());
        String SQL = "";
        switch (status) {
            case ("Completed"):
                SQL = "Update status\n"
                        + "SET  finish = '" + time + "', status_type = '" + status + "'\n"
                        + "WHERE status_id = '" + statusID + "';";
                break;
            case ("In progress"):
                SQL = "Update status\n"
                        + "SET  start = '" + time + "', status_type = '" + status + "'\n"
                        + "WHERE status_id = '" + statusID + "';";
                break;
            case ("Not started"):
                SQL = "Update status\n"
                        + "SET status_type = '" + status + "'\n"
                        + "WHERE status_id = '" + statusID + "';";
                break;
        }
        database.write(SQL, conn);
    }

    public void overrideStandardJobStatus(int jobNumber, int standardJobIndex, String status) {
        this.getStandardJobList().get(standardJobIndex).setStatus(status);
        int statusID = (this.getStandardJobList().get(standardJobIndex).getStatusID());
        String SQL = "Update status\n"
                + "SET status_type = '" + status + "'\n"
                + "WHERE status_id = '" + statusID + "';";
        database.write(SQL, conn);
    }

    public void overrideJobStatus(String status, int index) {
        int statusID = this.getJob().get(index).getStatusID();
        this.getJob().get(index).setStatus(status);
        String SQL = "Update status\n"
                + "SET status_type = '" + status + "'\n"
                + "WHERE status_id = '" + statusID + "';";
        database.write(SQL, conn);
    }

    /**
     * @return the standardJobList
     */
    public ArrayList<StandardJob> getStandardJobList() {
        return standardJobList;
    }

    public void clearStandardJobList() {
        standardJobList.clear();
    }

    public void updateJobStatus(String status, int index, String time) {
        int statusID = this.getJob().get(index).getStatusID();
        this.getJob().get(index).setStatus(status);
        String SQL = "";
        switch (status) {
            case ("Completed"):
                SQL = "Update status\n"
                        + "SET  finish = '" + time + "', status_type = '" + status + "'\n"
                        + "WHERE status_id = '" + statusID + "';";
                break;
            case ("In progress"):
                SQL = "Update status\n"
                        + "SET  start = '" + time + "', status_type = '" + status + "'\n"
                        + "WHERE status_id = '" + statusID + "';";
                break;
            case ("Not started"):
                SQL = "Update status\n"
                        + "SET status_type = '" + status + "'\n"
                        + "WHERE status_id = '" + statusID + "';";
                break;
        }
        database.write(SQL, conn);
    }

    public ArrayList<Job> getJob() {
        return jobList;
    }

    public void clearJob() {
        jobList.clear();
    }

    public boolean checkIfAllTasksAreCompleted(String standardJobCode, int jobNumber) {
        boolean allTasksCompleted = true;
        String SQL = "SELECT status_type FROM job_standardjobs_tasks \n"
                + "inner join status on job_standardjobs_tasks.Status_status_id = status.status_id\n"
                + "inner join job on job_standardjobs_tasks.Job_StandardJobs_Job_job_no = job.job_no\n"
                + "WHERE Job_StandardJobs_StandardJob_code = '" + standardJobCode + "' AND job_no = '" + jobNumber + "';";
        rs = database.read(SQL, conn);
        try {
            while (rs.next()) {
                if (rs.getString("status_type").equals("In progress") || rs.getString("status_type").equals("Not started")) {
                    allTasksCompleted = false;
                }
            }
        } catch (Exception e) {
            System.out.println("Check If All Tasks Are Completed Error");
        }
        return allTasksCompleted;
    }

    public boolean checkIfStandardJobIsInProgress(String standardJobCode, int jobNumber) {
        boolean standardJobIsInProgress = false;
        String SQL = "SELECT status_type FROM job_standardjobs_tasks \n"
                + "inner join status on job_standardjobs_tasks.Status_status_id = status.status_id\n"
                + "inner join job on job_standardjobs_tasks.Job_StandardJobs_Job_job_no = job.job_no\n"
                + "WHERE Job_StandardJobs_StandardJob_code = '" + standardJobCode + "' AND job_no = '" + jobNumber + "';";
        rs = database.read(SQL, conn);
        try {
            while (rs.next()) {
                if (rs.getString("status_type").equals("In progress") || rs.getString("status_type").equals("Completed")) {
                    standardJobIsInProgress = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Check If Standard job is in progress Error");
        }

        return standardJobIsInProgress;
    }

    public boolean checkIfJobIsInProgress(int jobNumber) {
        boolean jobIsInProgress = false;
        String SQL = "SELECT status_type from job_standardjobs\n"
                + "inner join status on job_standardjobs.Status_status_id = status.status_id\n"
                + "WHERE job_standardjobs.Job_job_no = '" + jobNumber + "' ;";
        rs = database.read(SQL, conn);

        try {
            while (rs.next()) {
                if (rs.getString("status_type").equals("In progress") || rs.getString("status_type").equals("Completed")) {
                    jobIsInProgress = true;
                }
            }

        } catch (Exception e) {
            System.out.println("Check if Job is in progress error");
        }
        return jobIsInProgress;
    }

    public boolean checkIfAllStandardJobsAreCompleted(int jobNumber) {
        boolean allStandardJobsCompleted = true;
        String SQL = "SELECT * from job_standardjobs\n"
                + "inner join status on job_standardjobs.Status_status_id = status.status_id\n"
                + "WHERE Job_job_no = '" + jobNumber + "';";
        rs = database.read(SQL, conn);
        try {
            while (rs.next()) {
                if (rs.getString("status_type").equals("In progress") || rs.getString("status_type").equals("Not started")) {
                    allStandardJobsCompleted = false;
                }
            }
        } catch (Exception e) {
            System.out.println("Check If all standard jobs are completed error");
        }
        return allStandardJobsCompleted;
    }

    public void addJob(String userInput) {

        //Get job information from database
        String SQL = "SELECT * from job\n"
                + "inner join user on job.User_account_no = user.account_no\n"
                + "inner join status on job.Status_status_id = status.status_id\n"
                + "WHERE Customer_account_no = '" + userInput + "';";
        //Read job information from database
        rs = database.read(SQL, conn);
        //Read standard job information from database

        String issuedBy = "";
        int jobNumber = 0;
        boolean isCollected = false;
        int statusID = 0;
        Date dateReceived = null;
        Time completionTime = null;
        String statusType = "";

        try {
            while (rs.next()) {

                //Combine first name and last name
                issuedBy = rs.getString("firstname") + " " + rs.getString("lastname");

                //Determine if job is collected
                int collectedValue = rs.getInt("is_collected");
                if (collectedValue == 0) {
                    isCollected = false;
                } else {
                    isCollected = true;
                }

                jobNumber = rs.getInt("job_no");
                statusID = rs.getInt("Status_status_id");
                dateReceived = rs.getDate("Deadline_date_received");
                completionTime = rs.getTime("Deadline_CompletionTime_completion_time");
                statusType = rs.getString("status_type");

                Job job = new Job(jobNumber, completionTime,
                        issuedBy, statusType, dateReceived, isCollected,
                        statusID);
                jobList.add(job);
            }

        } catch (Exception e) {
            System.out.println("Add job error");
        }

    }

    public void clearStandardJobTasks() {
        standardJobTasksList.clear();
    }

    public boolean isCustomerValued(int jobNumber) {
        boolean isValued = false;
        String SQL = "select is_valued from job\n"
                + "inner join customer on job.Customer_account_no = customer.account_no\n"
                + "WHERE job_no = '" + jobNumber + "';";
        rs = database.read(SQL, conn);
        try {
            if (rs.next()) {
                if (rs.getInt("is_valued") == 1) {
                    isValued = true;
                }
            }
        } catch (Exception e) {

        }
        return isValued;
    }

    public boolean doesJobExist(String userInput, boolean isJobNumberInput) {
        boolean exists = false;

        String SQL = "";
        if (isJobNumberInput) {
            SQL = "SELECT * FROM job WHERE job_no = '" + userInput + "';";

        } else {
            SQL = "SELECT * FROM job WHERE Customer_account_no = '" + userInput + "';";

        }
        rs = database.read(SQL, conn);
        try {
            //If job exists ...
            if (rs.next()) {
                exists = true;
            }
        } catch (Exception e) {
            System.out.println("Job exist error");
        }
        return exists;
    }

    public boolean doesTaskExist(String userINput) {
        boolean exists = false;
        String SQL = "SELECT * FROM task WHERE task_id = '" + userINput + "';";
        rs = database.read(SQL, conn);
        try {
            //If task exists ...
            if (rs.next()) {
                exists = true;
            }
        } catch (Exception e) {
            System.out.println("Task exist error");
        }
        return exists;
    }

    public String getDepartmentCode(String department1) {
        //Convert department name to departmenet code
        String departmentCode = "";
        switch (department1) {
            case "Copy Room":
                departmentCode = "CR";
                break; // optional
            case "Development Area":
                departmentCode = "DR";
                break; // optional
            case "Packaging Department":
                departmentCode = "PR";
                break; // optional
            case "Finishing Room":
                departmentCode = "FR";
                break; // optional

        }
        return departmentCode;
    }

    public boolean updateTask(int taskID, String description, int shelfSlot, double price, String departmentCode) {
        boolean success = false;
        String depCode = this.getDepartmentCode(departmentCode);

        String SQL = "Update task\n"
                + "SET description = '" + description + "',Department_department_code = '" + depCode + "',shelf_slot = '" + shelfSlot + "',price = '" + price + "'\n"
                + "WHERE task_id =" + taskID + ";";
        database.write(SQL, conn);
        try {
            if (rs.next()) {
                success = true;
            }
        } catch (Exception e) {
            System.out.println("Update Task Error");
        }
        return success;
    }

    public boolean deleteTask(int taskID) {
        boolean success = false;
        String SQL = "DELETE FROM task WHERE task_id = " + taskID + ";";
        database.write(SQL, conn);
        try {
            if (rs.next()) {
                success = true;
            }
        } catch (Exception e) {
            System.out.println("Delete Task Error");
        }
        return success;
    }

    public void getListOfJobNumbers(String statusType, String priority, int isCollected) {
        String SQL = "SELECT job_no from job\n"
                + "inner join status on job.Status_status_id = status.status_id\n"
                + "inner join deadline on job.Deadline_date_received = deadline.date_received\n"
                + "inner join completiontime on deadline.CompletionTime_completion_time = completiontime.completion_time\n"
                + "inner join priority on completiontime.Priority_priority_description = priority.priority_description\n"
                + "where status_type = '" + statusType + "' AND priority_description = '" + priority + "' AND is_collected = '" + isCollected + "';";

        ResultSet resultSet = database.read(SQL, conn);

        try {

            while (resultSet.next()) {

                int jobNumber = resultSet.getInt("job_no");
                this.searchAllJobsUnderCriteria(jobNumber);
            }
        } catch (Exception e) {
            System.out.println("Error getting job details from job enquiry search criteria");
        }
    }

    public boolean doesJobUnderJobCriteriaExist(String statusType, String priority, int isCollected) {
        boolean exists = false;
        String SQL = "SELECT job_no from job\n"
                + "inner join status on job.Status_status_id = status.status_id\n"
                + "inner join deadline on job.Deadline_date_received = deadline.date_received\n"
                + "inner join completiontime on deadline.CompletionTime_completion_time = completiontime.completion_time\n"
                + "inner join priority on completiontime.Priority_priority_description = priority.priority_description\n"
                + "where status_type = '" + statusType + "' AND priority_description = '" + priority + "' AND is_collected = '" + isCollected + "';";
        rs = database.read(SQL, conn);
        try {
            if (rs.next()) {
                exists = true;
            }
        } catch (Exception e) {
            System.out.println("Error searching job under criteria");
        }
        return exists;
    }

    public void searchAllJobsUnderCriteria(int jobNumber) {

        //Get job information from database
        String SQL = "SELECT * from job\n"
                + "inner join status on job.Status_status_id = status.status_id\n"
                + "inner join user on job.User_account_no = user.account_no\n"
                + "WHERE job_no = '" + jobNumber + "';";
        rs = database.read(SQL, conn);

        String status = "";
        String issuedBy = "";
        boolean isCollected = false;
        Time deadline = null;
        Date dateReceived = null;
        int statusID = 0;
        try {

            while (rs.next()) {

                //Combine first name and last name
                issuedBy = rs.getString("firstname") + " " + rs.getString("lastname");
                deadline = rs.getTime("Deadline_CompletionTime_completion_time");
                int collectedValue = rs.getInt("is_collected");
                dateReceived = rs.getDate("Deadline_date_received");
                statusID = rs.getInt("Status_status_id");
                status = rs.getString("status_type");
                if (collectedValue == 0) {
                    isCollected = false;
                } else {
                    isCollected = true;
                }
            }

            //Create a new job
            Job job = new Job(jobNumber, deadline, issuedBy, status, dateReceived, isCollected, statusID);
            //Add job to job list  
            jobList.add(job);
            //job.setStandardJobList(standardJobList);

        } catch (Exception e) {
            System.out.println("Search job under job criteria Error");
        }
    }

    public void getStandardJobsFromJobNumber(int jobNumber) {
        //Get standard jobs from job number
        String SQL = "SELECT * from job_standardjobs\n"
                + "inner join standardjob on job_standardjobs.StandardJob_code = standardjob.code\n"
                + "inner join status on job_standardjobs.Status_status_id = status.status_id\n"
                + "WHERE Job_job_no = '" + jobNumber + "';";
        rs = database.read(SQL, conn);

        String code = "";
        String description = "";
        double price = 0;
        String status = "";
        int statusID = 0;

        try {
            while (rs.next()) {
                code = rs.getString("code");
                description = rs.getString("job_description");
                price = rs.getInt("price");
                status = rs.getString("status_type");
                statusID = rs.getInt("status_id");

                //Create a new standard job and add it to array list
                StandardJob standardJob = new StandardJob(code, description, price, status, statusID);
                getStandardJobList().add(standardJob);
            }

        } catch (Exception e) {
            System.out.println("Get Standard Jobs from job number error");
        }
    }

    public void getTasksFromStandardJobCode(String standardJobCode, int jobNumber) {
        //Get tasks from standard job
        String SQL = " SELECT * FROM job_standardjobs_tasks\n"
                + "inner join task on job_standardjobs_tasks.Task_task_id = task.task_id\n"
                + "inner join standardjob on job_standardjobs_tasks.Job_StandardJobs_StandardJob_code = standardjob.code\n"
                + "inner join status on job_standardjobs_tasks.Status_status_id = status.status_id\n"
                + "                where Job_StandardJobs_StandardJob_code = '" + standardJobCode + "' AND Job_StandardJobs_Job_job_no = '" + jobNumber + "';";
        rs = database.read(SQL, conn);

        try {
            while (rs.next()) {
                //Create task array list

                //Convert department code to department name
                String departmentName = this.getDepartmentName(rs.getString("Department_department_code"));
                //Create new task   
                Task task = new Task(rs.getInt("task_id"), rs.getString("description"), rs.getInt("duration_min"), rs.getInt("shelf_slot"), rs.getDouble("price"),
                        departmentName, rs.getString("status_type"), rs.getInt("status_id"));
                //Add task to task array list
                getTaskList().add(task);
            }
        } catch (Exception e) {
            System.out.println("Error getting tasks from standard job");
        }
    }

    public void clearTaskList() {
        taskList.clear();
    }

    public Integer getInvoiceNumber(int jobNumber) {
        //Get invoice number from database

        String SQL = "SELECT invoice_no FROM invoice,job WHERE job.job_no = invoice.Job_job_no AND job_no = '" + jobNumber + "';";
        rs = database.read(SQL, conn);
        int invoiceNumber = 0;
        try {
            if (rs.next()) {
                invoiceNumber = rs.getInt("invoice_no");
            }
        } catch (Exception e) {
            System.out.println("Get Invoice Number Error");
        }
        return invoiceNumber;
    }

    public boolean doesTechnicianHaveAccess(int accountNumber, String standardJobCode, int jobNumber, int taskID) {
        boolean access = false;
        String SQL = "SELECT * FROM department\n"
                + "inner join technician_room on department.department_code = technician_room.Department_code\n"
                + "inner join task on department.department_code = task.Department_department_code\n"
                + "inner join job_standardjobs_tasks on task.task_id = job_standardjobs_tasks.Task_task_id\n"
                + "inner join job_standardjobs on job_standardjobs_tasks.Job_StandardJobs_StandardJob_code = job_standardjobs.StandardJob_code\n"
                + "WHERE Account_no = '" + accountNumber + "' AND Job_StandardJobs_StandardJob_code = '" + standardJobCode + "' "
                + "AND Job_job_no = '" + jobNumber + "' AND Job_StandardJobs_Job_job_no = '" + jobNumber + "' AND task_id = '" + taskID + "';";
        rs = database.read(SQL, conn);
        try {
            if (rs.next()) {
                access = true;
            }
        } catch (Exception e) {
            System.out.println("Does technician have access error");
        }
        return access;
    }

    public void createTechnician(int accountNumber, String departmentCode) {
        String SQL = "INSERT INTO technician_room (Department_code,Account_no) VALUES ('" + departmentCode + "','" + accountNumber + "');";
        database.write(SQL, conn);
    }

    public ArrayList<LatePaymentInvoice> getLatePaymentInvoices(String monthIssued) {

        ArrayList<LatePaymentInvoice> invoiceList = new ArrayList<>();
        DateFormat monthFormat = new SimpleDateFormat("MM");

        String SQL = "SELECT * FROM invoice\n"
                + "inner join job on invoice.Job_job_no = job.job_no\n"
                + "inner join customer on job.Customer_account_no = customer.account_no\n"
                + "WHERE invoice_status = 'Awaiting payment';";
        rs = database.read(SQL, conn);

        try {
            while (rs.next()) {

                //Convert invoice date issued format to month and compare with monthIssued
                String invoiceMonth = monthFormat.format(rs.getDate("date_issued"));
                //Conver month format to numbers
                int invoiceMonthNumber = Integer.parseInt(invoiceMonth);
                int currentMonthNumber = Integer.parseInt(monthIssued);

                //If current month is ahead of the month of the invoice generated then create late payment invoice
                //If the invoice awaiting payment does not equal the current month then generate reminder letter
                if (currentMonthNumber > invoiceMonthNumber) {
                    String customerName = rs.getString("firstname") + " " + rs.getString("lastname");

                    LatePaymentInvoice invoice = new LatePaymentInvoice(rs.getInt("Invoice_no"), customerName, rs.getInt("total_payable"), rs.getDate("date_issued"),
                            rs.getString("street_name"), rs.getString("city"), rs.getString("postcode"), rs.getString("lastname"), rs.getString("prefix"));

                    invoiceList.add(invoice);
                }
            }
        } catch (Exception e) {
            System.out.println("Get late payment invoices error");
        }

        return invoiceList;
    }

    public boolean hasFirstReminderLetterBeenGenerated(int invoiceNumber) {

        String SQL = "SELECT * from reminderletter WHERE Invoice_invoice_no = '" + invoiceNumber + "';";
        rs = database.read(SQL, conn);
        try {
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error checking if first reminder letter has been generated");
        }
        return false;
    }

    public void insertFirstReminderLetterInformationInDatabase(int invoiceNumber, String todayDate) {

        String SQL = "INSERT INTO reminderletter (Invoice_invoice_no,date_issued,letter_location)\n"
                + "VALUES ('" + invoiceNumber + "','" + todayDate + "','Test');";
        database.write(SQL, conn);
    }

    public boolean reminderLettersAlreadyGenerated(String month) {
        boolean generated = false;
        String SQL = "SELECT Date from reminderletterdate WHERE Date = '" + month + "';";
        rs = database.read(SQL, conn);
        try {
            if (rs.next()) {
                generated = true;
            }
        } catch (Exception e) {
            System.out.println("Check if reminder letters have been generated for this month error");
        }
        return generated;
    }

    public void setReminderLetterDate(String date) {
        String SQL = "INSERT INTO reminderletterdate (Date) VALUES('" + date + "');";
        database.write(SQL, conn);

    }

    public void setCustomerToInDefault(int invoiceNumber) {
        String SQL = "Update customer\n"
                + "Inner join job on customer.account_no = job.Customer_account_no\n"
                + "inner join invoice on job.job_no = invoice.Job_job_no\n"
                + "SET in_default = '1'\n"
                + "WHERE Invoice_no = '" + invoiceNumber + "';";
        database.write(SQL, conn);
    }

    public boolean isCustomerSuspended(int invoiceNumber) {
        boolean suspended = false;
        String SQL = "SELECT * FROM customer\n"
                + "Inner join job on customer.account_no = job.Customer_account_no\n"
                + "inner join invoice on job.job_no = invoice.Job_job_no\n"
                + "WHERE Invoice_no = '" + invoiceNumber + "' AND is_suspended = '1';";
        rs = database.read(SQL, conn);
        try {
            if (rs.next()) {
                suspended = true;

            }
        } catch (Exception e) {
            System.out.println("Is customer in default error");
        }
        return suspended;
    }

    public void setCustomerToSuspended(int invoiceNumber) {
        String SQL = "Update customer\n"
                + "Inner join job on customer.account_no = job.Customer_account_no\n"
                + "inner join invoice on job.job_no = invoice.Job_job_no\n"
                + "SET is_suspended = '1'\n"
                + "WHERE Invoice_no = '" + invoiceNumber + "';";
        database.write(SQL, conn);
    }

    public ArrayList<String> viewAlert(int roleID) {
        ArrayList<String> alerts = new ArrayList<>();
        String message = "";
        String SQL = "SELECT * FROM alert WHERE role = '" + roleID + "';";
        rs = database.read(SQL, conn);
        try {
            while (rs.next()) {

                message = rs.getString("message");
                alerts.add(message);
            }
        } catch (Exception e) {
            System.out.println("View Alert error");
        }
        return alerts;
    }

    public ArrayList<StandardJob> getAllStandardJobs() {
        ArrayList<StandardJob> allStandardJobList = new ArrayList<>();
        String SQL = "SELECT * FROM standardjob;";
        rs = database.read(SQL, conn);
        try {
            while (rs.next()) {
                StandardJob standardJob = new StandardJob(rs.getString("code"), rs.getString("job_description"), rs.getDouble("price"));
                allStandardJobList.add(standardJob);
            }
        } catch (Exception e) {
            System.out.println("Get All standard jobs error (Manage Standard Job)");
        }
        return allStandardJobList;
    }

    public ArrayList<Task> getTasksFromStandardJob(String standardJobCode) {
        ArrayList<Task> taskListFromStandardJob = new ArrayList<>();
        String SQL = "SELECT * FROM standardjob_tasks \n"
                + "inner join task on standardjob_tasks.Task_task_id = task.task_id\n"
                + "inner join department on task.Department_department_code = department.department_code\n"
                + "WHERE StandardJob_code = '" + standardJobCode + "';";
        rs = database.read(SQL, conn);

        try {
            while (rs.next()) {

                Task task = new Task(rs.getInt("task_id"), rs.getString("description"),
                        rs.getInt("duration_min"), rs.getInt("shelf_slot"), rs.getDouble("price"), rs.getString("Department_department_code"));

                taskListFromStandardJob.add(task);

            }
        } catch (Exception e) {
            System.out.println("Get task list from standard job error");
        }
        return taskListFromStandardJob;
    }

    public Integer getTotalFromInvoice(int invoiceNumber) {
        int total = 0;
        String SQL = "SELECT total_payable FROM invoice\n"
                + "WHERE Invoice_no = '" + invoiceNumber + "';";
        rs = database.read(SQL, conn);
        try {
            if (rs.next()) {
                total = rs.getInt("total_payable");
            }
        } catch (Exception e) {
            System.out.println("Get total from invoice error");
        }
        return total;
    }

    public boolean doesTaskExistInStandardJob(String standardJobCode, String taskID) {
        boolean exists = false;
        String SQL = "SELECT * FROM standardjob_tasks\n"
                + "WHERE StandardJob_code = '" + standardJobCode + "' AND Task_task_id = '" + taskID + "';";
        rs = database.read(SQL, conn);
        try {
            if (rs.next()) {
                exists = true;
            }
        } catch (Exception e) {
            System.out.println("Does task exist in standard job error");
        }
        return exists;
    }

    public void addTaskToStandardJob(String taskID, String standardJobCode) {
        String SQL = "INSERT INTO standardjob_tasks  (StandardJob_code,Task_task_id) VALUES ('" + standardJobCode + "','" + taskID + "');";
        database.write(SQL, conn);
    }

    public float getPriceOfStandardJob(String standardJobCode) {
        String SQL = "SELECT * FROM standardjob_tasks \n"
                + "inner join task on standardjob_tasks.Task_task_id = task.task_id\n"
                + "WHERE StandardJob_code = '" + standardJobCode + "';";
        rs = database.read(SQL, conn);
        System.out.println("read");
        float price = 0;
        try {
            while (rs.next()) {

                price = price + rs.getFloat("price");

            }
        } catch (Exception e) {
            System.out.println("Get Price of standard job error");
        }
        return price;
    }

    public void updatePrice(String standardJobCode, float price) {
        String SQL = "Update standardjob\n"
                + "SET price = '" + price + "'\n"
                + "WHERE code = '" + standardJobCode + "';";
        database.write(SQL, conn);
    }

    public void removeTaskFromStandardJob(String standardJobCode, int taskID) {
        String SQL = "DELETE FROM standardjob_tasks WHERE StandardJob_code = '" + standardJobCode + "' AND Task_task_id = '" + taskID + "';";
        database.write(SQL, conn);
    }

    public boolean doesUserExist(String username) {
        String SQL = "SELECT * FROM user WHERE username = '" + username + "';";
        rs = database.read(SQL, conn);
        try {
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Does user exist error");
        }
        return false;
    }

    public String securityAnswer(String username) {
        String SQL = "SELECT security_answer FROM user WHERE username = '" + username + "';";
        String answer = "";
        rs = database.read(SQL, conn);
        try {
            if (rs.next()) {
                answer = rs.getString("security_answer");
            }
        } catch (Exception e) {
            System.out.println("Security answer error");
        }
        return answer;
    }

    public void updatePassword(String username, int password) {
        String SQL = "UPDATE user\n"
                + "SET password_hash = '" + password + "'\n"
                + "WHERE username = '" + username + "';";
        database.write(SQL, conn);
    }
    
    public void updateCompletionTimeWhenJobIsCompleted(){
        
    }
}
