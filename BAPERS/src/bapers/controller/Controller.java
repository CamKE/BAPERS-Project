/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.controller;

import bapers.job.Task;
import bapers.database.DBImpl;
import bapers.job.JobDetails;
import bapers.job.StandardJob;
import java.sql.Connection;
import java.util.*;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Date;

/**
 *
 * @author CameronE
 */
public class Controller {

    private DBImpl database;
    private Connection conn;
    private ResultSet rs;

    //Create array list of JobDetails to store into table
    ArrayList<JobDetails> jobList = new ArrayList<>();
    JobDetails job;
    //Create array list of tasks to store into standard job
    private ArrayList<Task> standardJobTasksList = new ArrayList<>();
    Task task;
    //Create array list of standardJobs to store into job
    StandardJob standardJob;

    Map<String, ArrayList<Task>> standardJobTaskMap = new HashMap<>();
    Map<Integer, ArrayList<StandardJob>> jobStandardJobMap = new HashMap<>();

    public Controller() {
        database = new DBImpl();
        conn = database.connect();
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
        String SQL = "INSERT INTO TASK(description,duration_min,shelf_slot,price,Department_department_code) VALUES ('" + description1 + "','" + duration1 + "','" + shelf_slot1 + "','" + price1 + "','" + departmentCode + "');";
        try {
            database.write(SQL, conn);
            success = true;
        } catch (Exception e) {
            System.out.println("Create new task Error");
        }
        return success;
    }

    public ArrayList<Task> getTasks() {
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

    public void setTasksIntoStandardJob(String standardJobCode) {
        String SQL = "SELECT * FROM job_standardjobs_tasks \n"
                + "inner join task on job_standardjobs_tasks.Task_task_id = task.task_id\n"
                + "inner join standardjob on job_standardjobs_tasks.Job_StandardJobs_StandardJob_code = standardjob.code\n"
                + "inner join status on job_standardjobs_tasks.Status_status_id = status.status_id\n"
                + "where Job_StandardJobs_StandardJob_code = '" + standardJobCode + "';";

        String standardJobSQL = "SELECT * From job_standardjobs\n"
                + "inner join status on job_standardjobs.Status_status_id = status.status_id\n"
                + "inner join standardjob on job_standardjobs.StandardJob_code = standardjob.code\n"
                + "where job_standardjobs.StandardJob_code= '" + standardJobCode + "';";

        ResultSet standardJobResultSet = database.read(standardJobSQL, conn);
        rs = database.read(SQL, conn);

        ArrayList<Task> taskList = new ArrayList<>();
        String standardJobDescription = "";
        double price = 0;
        String status = "";
        int statusID = 0;
        try {

            //Create Task class from database
            while (rs.next()) {
                //Create task array list

                //Convert department code to department name
                String departmentName = this.getDepartmentName(rs.getString("Department_department_code"));
                //Create new task   
                task = new Task(rs.getInt("task_id"), rs.getString("description"), rs.getInt("duration_min"), rs.getInt("shelf_slot"), rs.getDouble("price"),
                        departmentName, rs.getString("status_type"), rs.getInt("status_id"));
                //Add task to task array list
                taskList.add(task);
                //Get price from task and add it to standard job price total
                price = price + rs.getDouble("price");

            }

            //Create standard job class from database
            if (standardJobResultSet.next()) {
                //Get status of standard job
                status = standardJobResultSet.getString("status_type");
                //Get standardJobDescription
                standardJobDescription = standardJobResultSet.getString("job_description");
                statusID = standardJobResultSet.getInt("status_id");
            }

            //Add array list task to map
            standardJobTaskMap.put(standardJobCode, taskList);
            //Create new standard job
            standardJob = new StandardJob(standardJobCode, standardJobDescription, price, status, statusID);
            standardJob.setStandardJobTasks(taskList);
            standardJob.setAmountOfTasks(standardJobTaskMap.get(standardJobCode).size());
        } catch (Exception e) {
            System.out.println("Set tasks into standard job error");
        }

        /*        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("Standard Job Code: " + standardJobCode + " -> Task ID: " + standardJobTaskMap.get(standardJobCode).get(i).getTaskID());
        }*/
    }

    public void setStandardJobIntoJob(int jobNumber) {
        String SQL = "SELECT * from job\n"
                + "inner join job_standardjobs on job.job_no = job_standardjobs.Job_job_no\n"
                + "inner join status on job.Status_status_id = status.status_id\n"
                + "inner join user on job.User_account_no = user.account_no\n"
                + "WHERE job_no = '" + jobNumber + "';";
        ResultSet rSet = database.read(SQL, conn);

        ArrayList<StandardJob> standardJobList = new ArrayList<>();
        String status = "";
        String issuedBy = "";
        boolean isCollected = false;
        Time deadline = null;
        Date dateReceived = null;
        int statusID = 0;
        try {

            while (rSet.next()) {
                //Get standard job code from database and call method to set tasks into standard job
                this.setTasksIntoStandardJob(rSet.getString("StandardJob_code"));
                //Add standard job to array list
                standardJobList.add(standardJob);
                status = rSet.getString("status_type");

                //Combine first name and last name
                issuedBy = rSet.getString("firstname") + " " + rSet.getString("lastname");
                deadline = rSet.getTime("Deadline_CompletionTime_completion_time");
                int collectedValue = rSet.getInt("is_collected");
                dateReceived = rSet.getDate("Deadline_date_received");
                statusID = rSet.getInt("Status_status_id");
                if (collectedValue == 0) {
                    isCollected = false;
                } else {
                    isCollected = true;
                }
            }

            //Add array list standard job to map
            jobStandardJobMap.put(jobNumber, standardJobList);

            //Create a new job
            job = new JobDetails(jobNumber, deadline, issuedBy, status, dateReceived, isCollected, statusID);
            //Add job to job list
            job.setAmountOfStandardJobs(jobStandardJobMap.get(jobNumber).size());
            // System.out.println(job.getAmountOfStandardJobs());          
            jobList.add(job);
            job.setStandardJobList(standardJobList);

        } catch (Exception e) {
            System.out.println("Set Standard Job into Job Error");
        }

    }

    public void test() {
        for (int i = 0; i < jobStandardJobMap.size(); i++) {
            int jobNumber = jobList.get(i).getJobNumber();
            System.out.println("Job Number: " + jobNumber);
            //  System.out.println("Amount of standard jobs: " + jobStandardJobMap.get(jobList.get(i).getJobNumber()).size());

            for (int j = 0; j < jobStandardJobMap.get(jobNumber).size(); j++) {
                String standardJobCode = jobStandardJobMap.get(jobNumber).get(j).getCode();
                System.out.println("Standard Job Code : " + standardJobCode);

                for (int k = 0; k < standardJobTaskMap.get(jobStandardJobMap.get(jobList.get(i).getJobNumber()).get(j).getCode()).size(); k++) {

                    System.out.println("Task ID : " + standardJobTaskMap.get(jobStandardJobMap.get(jobList.get(i).getJobNumber()).get(j).getCode()).get(k).getTaskID());
                }
            }
            System.out.println("-------------");
        }

    }

    public void updateTaskStatus(String standardJobCode, int taskID, String status) {
        this.getStandardJobTasks(standardJobCode).get(taskID).setStatus(status);
    }

    public void updateTaskStatusInDatabase(String standardJobCode, int taskID) {
        int statusID = this.getStandardJobTasks(standardJobCode).get(taskID).getStatusID();
        String statusType = this.getStandardJobTasks(standardJobCode).get(taskID).getStatus();
        String SQL = "Update status\n"
                + "SET status_type = '" + statusType + "'\n"
                + "WHERE status_id = '" + statusID + "';";
        database.write(SQL, conn);
    }

    public void updateStandardJobStatus(int jobNumber, int standardJobIndex, String status) {
        //this.getStandardJobList(jobNumber).get(standardJobIndex).getCode();
        this.getStandardJobList(jobNumber).get(standardJobIndex).setStatus(status);
        int statusID = (this.getStandardJobList(jobNumber).get(standardJobIndex).getStatusID());
        //this.getStandardJobList(jobNumber).get(standardJobIndex).setStatus(status);
        String SQL = "Update status\n"
                + "SET status_type = '" + status + "'\n"
                + "WHERE status_id = '" + statusID + "';";
        database.write(SQL, conn);
    }

    public void updateJobStatus(String status) {
        int statusID = this.getJob().get(0).getStatusID();
        this.getJob().get(0).setStatus(status);
        String SQL = "Update status\n"
                + "SET status_type = '" + status + "'\n"
                + "WHERE status_id = '" + statusID + "';";
        database.write(SQL, conn);
    }

    public boolean checkIfAllTasksAreCompleted(String standardJobCode) {
        boolean allTasksCompleted = true;
        String SQL = "SELECT * from job_standardjobs_tasks\n"
                + "inner join status on job_standardjobs_tasks.Status_status_id = status.status_id\n"
                + "WHERE Job_StandardJobs_StandardJob_code = '" + standardJobCode + "';";
        rs = database.read(SQL, conn);
        try {
            while (rs.next()) {
                if (rs.getString("status_type").equals("In progress")) {
                    allTasksCompleted = false;
                } 
            }
        } catch (Exception e) {
            System.out.println("Check If All Tasks Are Completed Error");
        }
        return allTasksCompleted;
    }

    public boolean checkIfAllStandardJobsAreCompleted(int jobNumber) {
        boolean allStandardJobsCompleted = true;
        String SQL = "SELECT * from job_standardjobs\n"
                + "inner join status on job_standardjobs.Status_status_id = status.status_id\n"
                + "WHERE Job_job_no = '" + jobNumber + "';";
        rs = database.read(SQL, conn);
        try {
            while (rs.next()) {
                if (rs.getString("status_type").equals("In progress")) {
                    allStandardJobsCompleted = false;
                } 
            }
        } catch (Exception e) {
            System.out.println("Check If all standard jobs are completed error");
        }
        return allStandardJobsCompleted;
    }

    public ArrayList<StandardJob> getStandardJobList(int jobNumber) {
        return job.getStandardJobList();
    }

    /**
     * @param standardJobCode
     * @return the standardJobTasks
     */
    public ArrayList<Task> getStandardJobTasks(String standardJobCode) {
        return standardJobTaskMap.get(standardJobCode);
        //return standardJob.getStandardJobTasks();
    }

    /**
     * @param standardJobTasks the standardJobTasks to set
     */
    public void setStandardJobTasks(ArrayList<Task> standardJobTasks) {
        this.standardJobTasksList = standardJobTasks;
    }

    public String getDepartmentName(String departmentCode) {

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
        String SQL = "SELECT * from job\n"
                + "inner join user on job.User_account_no = user.account_no\n"
                + "inner join status on job.Status_status_id = status.status_id\n"
                + "inner join job_standardjobs on job.job_no = job_standardjobs.Job_job_no\n"
                + "WHERE Customer_account_no = '" + userInput + "';";
        //Read job information from database
        rs = database.read(SQL, conn);
        //Read standard job information from database
        
        int amountOfStandardJobs = 0;
        String issuedBy = "";
        int jobNumber = 0;
        boolean isCollected = false;
        int statusID = 0;
        Date dateReceived = null;
        Time completionTime = null;
        String statusType = "";
        try {
            while (rs.next()) {
                amountOfStandardJobs++;
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
            }
            //Create job instance
            System.out.println(amountOfStandardJobs);
            job = new JobDetails(jobNumber, completionTime,
                    issuedBy, statusType, dateReceived, isCollected,
                    statusID);
            jobList.add(job);
            job.setAmountOfStandardJobs(amountOfStandardJobs);
        } catch (Exception e) {
            System.out.println("Add job error");
        }

    }

    public ArrayList<JobDetails> getJob() {
        return jobList;
    }

    public void clearJob() {
        jobList.clear();
    }

    public void clearStandardJobTasks() {
        standardJobTasksList.clear();
    }

    public Integer getInvoiceNumber() {
        //Get invoice number from database
        int jobNumber = job.getJobNumber();
        String SQL = "SELECT invoice_no  FROM invoice,job WHERE job.job_no = invoice.Job_job_no AND job_no = '" + jobNumber + "';";
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

    public void setJobNumber(int jobNumber) {
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

