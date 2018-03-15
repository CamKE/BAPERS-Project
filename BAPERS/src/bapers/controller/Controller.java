/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.controller;

import bapers.database.DBImpl;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author CameronE
 */
public class Controller {

    private DBImpl database;
    private Connection conn;
    private ResultSet rs, rsPassword;

    public Controller() {
        database = new DBImpl();
        conn = database.connect();

    }

    public int login(String userID, String password) {
        int roleId = 0;
        String userPassword;
        try {
            String userSQL = "SELECT* FROM USER WHERE account_no ='" + userID + "';";
            rs = database.read(userSQL, conn);
            if (rs.next()) {
                roleId = Integer.parseInt(rs.getString("Role_role_id"));
            } else {
                roleId = 0;
            }

        } catch (Exception e) {
            System.out.println("Log in Error");
        }
        return roleId;

    }

    public boolean createUser(String firstname, String lastName, String password, int roleId) {
        boolean success = false;
         String SQL = "INSERT INTO USER (firstname,lastname,password_hash,Role_role_id) Values ('"+firstname+"','"+lastName+"','"+password+"','"+roleId+"');";
        try {
            database.write(SQL, conn);
            success = true;
        } catch (Exception e) {
            System.out.println("Create user Error");
        }
        return success;
    }

}
