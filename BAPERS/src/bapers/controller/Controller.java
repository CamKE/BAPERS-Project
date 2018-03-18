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
    
   public boolean findUser(int userNumber,String firstName,String lastName,String role)
   {
       String sql = "SELECT from User WHERE ";
       System.out.println("yo");
       return false;
   }
 
}
