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
    public boolean createStandardJob(String code1, String job_description1, double price1) {
        boolean success = false;
        String SQL = "INSERT INTO STANDARDJOB(code, job_description, price) VALUES ('"+code1+"','" +job_description1+"','" +price1+"');";        
        
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
    
    
    public List<Task> fillComboBoxTask(){
        /*try{
            String SQL = "SELECT * FROM task";
            rs = database.read(SQL, conn);
            
            while(rs.next()){
                String description = rs.getString("description");
                selectATaskBox.addItem(description);
                
            }
            
        }catch(Exception e){
            System.out.println("Error");
            
        }*/
    
    List<Task> taskDescription = new ArrayList<>();
        String sql = "SELECT * from TASK";

        //close resultset after use
        try (ResultSet result = database.read(sql, conn)) {
            while (result.next()) {
                taskDescription.add(new Task(result.getInt("task_id"), result.getString("description"), result.getInt("duration_min"), result.getInt("shelf_slot"), result.getDouble("price"), result.getString("Department_department")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        return taskDescription;
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

    }

       
    


        
        
                
    


