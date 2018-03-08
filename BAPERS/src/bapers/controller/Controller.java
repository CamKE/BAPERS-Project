/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.controller;

import bapers.database.DBImpl;
import bapers.user.UserDetails;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author CameronE
 */
public class Controller {

    //new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }
    private DBImpl database;
    private Connection conn;

    public Controller() {
        database = new DBImpl();
        conn = database.connect();
    }

    public boolean createUser(String firstname, String lastname, int role, char[] password) {
        int role_id = role+1;
        //fix this
       String sql = "INSERT INTO user(firstname,lastname,password_hash,Role_role_id) VALUES (firstname,lastname,null,"+role_id+")";
        if (database.write(sql, conn) != 0) {
            return true;
        } else {
            return false;
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
}
