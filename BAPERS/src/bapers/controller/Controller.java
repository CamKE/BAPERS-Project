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
    private String SQL;

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
 
       public boolean createNewTask(String description, int duration, double price, int department, int shelf_slot){
        boolean success = false;      
        String sql = "INSERT INTO TASK(description_task,duration_min,price_task,Department_department_code,shelf_slot_task) VALUES ("+description+","+duration+","+price+","+department+","+shelf_slot+")";
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

