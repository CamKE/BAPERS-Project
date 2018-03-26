/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.controller;

import bapers.task.TaskInformation;
import bapers.database.DBImpl;
import bapers.job.JobDetails;
import java.sql.Connection;
import java.util.*;
import java.sql.ResultSet;

/**
 *
 * @author CameronE
 */
public class Controller {

    private DBImpl database;
    private Connection conn;
    private ResultSet rs;
    ArrayList<JobDetails> jobInfo = new ArrayList<>();
    JobDetails job;

    public Controller() {
        database = new DBImpl();
        conn = database.connect();
    }

    //   public boolean createUser(String firstname, String lastname, int role, char[] password) {
    //       int role_id = role+1;
    //       //fix this
    //      String sql = "INSERT INTO user(firstname,lastname,password_hash,Role_role_id) VALUES (firstname,lastname,null,"+role_id+")";
    //       if (database.write(sql, conn) != 0) {
    //           return true;
    //       } else {
    //           return false;
    //       }
    //   }
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
        String SQL = "INSERT INTO TASK(description,duration_min,shelf_slot,price,Department_department_code) VALUES ('" + description1 + "','" + duration1 + "','" + shelf_slot1 + "','" + price1 + "','" + departmentCode + "');";
        try {
            database.write(SQL, conn);
            success = true;
        } catch (Exception e) {
            System.out.println("Create new task Error");
        }
        return success;
    }

    public ArrayList<TaskInformation> getTasks() {
        //Get information from task table from the database
        String SQL = "SELECT * FROM task;";
        rs = database.read(SQL, conn);
        
        //Create array list of tasks
        ArrayList<TaskInformation> taskInfo = new ArrayList<>();
        TaskInformation task;
        
        //Get task infromatino from database and set it to new task instance(s)
        try {
            while (rs.next()) {
                //Convert department code to department name
                String departmentName = this.getDepartmentName(rs.getString("Department_department_code"));
                //Create new task 
                task = new TaskInformation(rs.getInt("task_id"), rs.getString("description"), rs.getInt("duration_min"), rs.getInt("shelf_slot"), rs.getDouble("price"),
                        departmentName);
                taskInfo.add(task);
            }
        } catch (Exception e) {
            System.out.println("update task table Error");
        }
        return taskInfo;
    }

    public String getDepartmentName(String departmentCode){
        
        //Convert department code to department name
               String departmentName = "";
        switch (departmentCode) {
            case "CR":
                departmentName = "Copy Room";
                break; // optional
            case "DR":
                departmentName = "Dark Room";
                break; // optional
            case "DA":
                departmentName = "Development Area";
                break; // optional
            case "PR":
                departmentName = "Printing Room";
                break; // optional
            case "FR":
                departmentName = "Finishing Room";
                break; // optional
            case "PD":
                departmentName = "Packaging Department";
                break; // optional
        }
        return departmentName;
    }
    public void addJob(String userInput) {
        
        //Get job information from database
        String SQL = "SELECT * from job"
                + " inner join user on job.User_account_no = user.account_no "
                + "inner join status on job.Status_status_id = status.status_id"
                + " WHERE job_no = '" + userInput + "';";

        //Read job information from database
        rs = database.read(SQL, conn);
        try {
            while (rs.next()) {
                //Combine first name and last name
                String issuedBy = rs.getString("firstname") + " " + rs.getString("lastname");
                
                //Determine if job is collected
                boolean isCollected;
                int collectedValue = rs.getInt("is_collected");
                
                if (collectedValue == 0){
                    isCollected = false;
                } else{
                    isCollected = true;
                }
               
                
                //Create job instance
                job = new JobDetails(rs.getInt("job_no"), rs.getTime("Deadline_date_received"), issuedBy, rs.getString("status_type"),isCollected);
                jobInfo.add(job);
            }
        } catch (Exception e) {
            System.out.println("Get job error");
        }

    }

    public ArrayList<JobDetails> getJob() {
        return jobInfo;
    }

    public void clearJob() {
        jobInfo.clear();
    }
    
    public Integer getInvoiceNumber( ){
        //Get invoice number from database
        int jobNumber =  job.getJobNumber();
        String SQL = "SELECT invoice_no  FROM invoice,job WHERE job.job_no = invoice.Job_job_no AND job_no = '"+jobNumber+"';";
        rs = database.read(SQL,conn);
        int invoiceNumber = 0;
        try {
            if (rs.next()){
               invoiceNumber = rs.getInt("invoice_no");
            }
        } catch (Exception e){
            System.out.println("Get Invoice Number Error");
        }
     return invoiceNumber;
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
    
    public boolean doesTaskExist(String userINput){
        boolean exists = false;
        String SQL = "SELECT * FROM task WHERE task_id = '" +userINput + "';";
        rs = database.read(SQL,conn);
        try {
            //If task exists ...
            if (rs.next()){
                exists = true;
            }
        } catch (Exception e){
            System.out.println("Task exist error");
        }
        return exists;
    }

    public String[] getShelfSlots() {
        String[] shelfSlots = new String[100];

        for (int i = 0; i < 100; i++) {
            shelfSlots[i] = String.valueOf(i + 1);
        }

        return shelfSlots;
    }

    public String getDepartmentCode(String department1) {
        //Convert department name to departmenet code
        String departmentCode = "";
        switch (department1) {
            case "Copy Room":
                departmentCode = "CR";
                break; // optional
            case "Dark Room":
                departmentCode = "DR";
                break; // optional
            case "Development Area":
                departmentCode = "DA";
                break; // optional
            case "Printing Room":
                departmentCode = "PR";
                break; // optional
            case "Finishing Room":
                departmentCode = "FR";
                break; // optional
            case "Packaging Department":
                departmentCode = "PD";
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
    
    public void setJobNumber(int jobNumber){
      job.setJobNumber(jobNumber);
    }
    
   
}
//    public String[] getRoles() {
//        // ArrayList roles = new ArrayList();
//        //should there be this much code in a controller????
//        String[] roles = new String[4];
//        int i = 0;
//        String sql = "select role_description from role";
//
//        //close resultset after use
//        try (ResultSet result = database.read(sql, conn)) {
//            while (result.next()) {
//                roles[i] = result.getString("role_description");
//                i++;
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        return roles;
//    }

