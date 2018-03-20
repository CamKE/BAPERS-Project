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
    private ResultSet rs;

    public Controller() {
        database = new DBImpl();
        conn = database.connect();

    }

    public int login(String userID, String password) {
        int roleId = 0;
        int hashPassword = password.hashCode();
        try {

            //Check user details sql query
            String SQL = "SELECT* FROM USER WHERE account_no ='" + userID + "' and password_hash = cast('" + hashPassword + "' as BINARY(32));";
            rs = database.read(SQL, conn);

            //If user details are valid
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
        int hashPassword = password.hashCode();
        String userSQL = "INSERT INTO USER (firstname,lastname,password_hash,Role_role_id) VALUES ('" + firstname + "','" + lastName + "','" + hashPassword + "','" + roleId + "');";

        return database.write(userSQL, conn) == 0;
    }

    public int getRole(String role) {
        int roleID = 0;
        switch (role) {
            case "Technician":
                roleID = 1;
                break;
            case "Office Manager":
                roleID = 2;
                break;
            case "Shift Manager":
                roleID = 3;
                break;
            case "Receptionist":
                roleID = 4;
                break;
        }
        return roleID;
    }

  
}
