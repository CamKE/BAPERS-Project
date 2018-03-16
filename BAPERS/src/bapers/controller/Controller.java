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
 
       public boolean createNewTask(String description1, int duration1, double price1, int department1, int shelf_slot1){
        boolean success = false;      
        String SQL = "INSERT INTO TASK(description,duration_min,price,Department_department_code,shelf_slot) VALUES ('"+description1+"','"+duration1+"','"+price1+"','"+department1+"','" +shelf_slot1+"');";   
        try {
            database.write(SQL, conn);
            success = true;
        } catch (Exception e) {
            System.out.println("create new task Error");
        }
        return success;
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

