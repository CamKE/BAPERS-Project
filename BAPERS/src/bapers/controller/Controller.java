/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.controller;

import bapers.database.DBImpl;
import java.sql.Connection;

/**
 *
 * @author CameronE
 */
public class Controller {

    private DBImpl database;
    private Connection conn;

    public Controller() {
        database = new DBImpl();
        conn = database.connect();
    }
    
    public boolean createStandardJob(String code, String job_description, double price) {
        boolean success = false;
        String SQL = "INSERT INTO STANDARDJOB(code, job_description, price) VALUES ("+code+"," +job_description+"," +price+");";        
        
        try {
            database.write(SQL, conn);
            success = true;
        } catch (Exception e) {
            System.out.println("create standard job Error");
        }
        return success;
    }
        //fix this
       
    }
        
        
                
    


