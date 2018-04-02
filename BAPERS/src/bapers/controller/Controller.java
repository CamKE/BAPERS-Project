/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.controller;

import bapers.database.DBImpl;
import bapers.job.StandardJob;
import bapers.task.Task;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author CameronE
 */
public class Controller {

    private DBImpl database;
    private Connection conn;
    private ResultSet rs;

    public Controller() {
        database = new DBImpl();
        conn = database.connect();
    }
    //Creates a new standard job using the input from the GUI 
    
    public boolean createStandardJob(String code1, String job_description1, double price1, String taskSelected) {
        boolean success = false;
        //This does not work, Have to add a sql query where it would link with the standardjob_task table
        String SQL = "INSERT INTO STANDARDJOB(code, job_description, price) VALUES ('"+code1+"','" +job_description1+"','" +price1+"') INSERT INTO STANDARDJOB_TASKS(StandardJob_code, Task_task_id) VALUES ('"+code1+"','"+taskSelected+"');";        
        
        try {
            database.write(SQL, conn);
            success = true;
        } catch (Exception e) {
            System.out.println("create standard job Error");
        }
        return success;
    }
        
    
    public ArrayList<StandardJob> getStandardJob() {
        String SQL = "SELECT * FROM standardjob;";
        rs = database.read(SQL, conn);
        ArrayList<StandardJob> standardJobInfo = new ArrayList<>();
        StandardJob standardJob;
        try {
            System.out.println("Getting standard job information...");
            while (rs.next()) {

                standardJob = new StandardJob(rs.getString("code"), rs.getString("job_description"), rs.getDouble("price"));
                standardJobInfo.add(standardJob);
            }
        } catch (Exception e) {
            System.out.println("update standard job table Error");
        }
        return standardJobInfo;
    }
    
    
    public boolean deleteStandardJob(String code) {

        boolean success = false;
        String SQL = "DELETE FROM standardjob WHERE code = " + code + ";";
        database.write(SQL,conn);
        try {
            if (rs.next()){
                success = true;
            }
        } catch (Exception e){
            System.out.println("Delete Job Error");
        }
        return success;
    }
    
    
    public int numTasks() {
        String sql = "SELECT COUNT(*) AS rowcount FROM task";
        try {
            ResultSet rs = database.read(sql, conn);
            rs.next();
            return rs.getInt("rowcount");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    
    
    public String[] getTasks() {
        String[] roles = new String[numTasks()];
        int i = 0;
        String sql = "select * from task";

        //close resultset after use
        try (ResultSet result = database.read(sql, conn)) {
            result.next();
            roles[i] = result.getString("description");
            System.out.println(roles[i]);
            while (result.next()) {
                roles[i] = result.getString("description");
                System.out.println(roles[i]);
                i++;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return roles;
    }
    
    
    public ArrayList<Task> getTask() {
        String SQL = "SELECT * FROM task;";
        rs = database.read(SQL, conn);
        ArrayList<Task> taskInfo = new ArrayList<>();
        Task tasks;
        try {
            System.out.println("Getting task information...");
            while (rs.next()) {

                tasks = new Task(rs.getInt("task_id"), rs.getString("description"), rs.getInt("duration_min"), rs.getInt("shelf_slot"), rs.getDouble("price"), rs.getString("Department_department_code"));
                taskInfo.add(tasks);
            }
        } catch (Exception e) {
            System.out.println("update task table Error");
        }
        return taskInfo;
    }

    
    
    }

       
    


        
        
                
    


