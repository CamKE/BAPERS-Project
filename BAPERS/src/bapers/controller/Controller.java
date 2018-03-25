/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.controller;

import bapers.database.DBImpl;
import bapers.job.StandardJob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

        String sql = "DELETE FROM standardjob WHERE code = " + code;
        return database.write(sql, conn) != 0;

    }

       
    }


        
        
                
    


