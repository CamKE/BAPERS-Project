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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        return database.write(userSQL, conn) != 0;
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

    public ArrayList<UserDetails> findUser(String userNumber, String firstName, String lastName, String role) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * from user WHERE 1=1 ");

        if (!userNumber.isEmpty()) {
            sb.append("AND account_no = ").append(Integer.parseInt(userNumber));
        } else {
            if (!firstName.isEmpty()) {
                sb.append(" AND firstname = '").append(firstName).append("'");
            }
            if (!lastName.isEmpty()) {
                sb.append(" AND lastname = '").append(lastName).append("'");
            }
            if (!role.equals("Any")) {
                int role_id = convertRole(role);
                sb.append(" AND Role_role_id = ").append(role_id);
            }
        }
        System.out.println(sb.toString());
        ResultSet rs = database.read(sb.toString(), conn);

        ArrayList<UserDetails> userList = new ArrayList<>();
        UserDetails user;
        try {
            while (rs.next()) {
                user = new UserDetails(rs.getInt("account_no"), rs.getString("firstname"), rs.getString("lastname"), rs.getBlob("password_hash"), rs.getTimestamp("registration_date"), rs.getInt("Role_role_id"));
                userList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM user WHERE account_no = " + userId;

        return database.write(sql, conn) != 0;
    }

    public int convertRole(String role) {
        int role_id = 0;
        switch (role) {
            case "Office Manager":
                role_id = 1;
                break;
            case "Shift Manager":
                role_id = 2;
                break;
            case "Receptionist":
                role_id = 3;
                break;
            case "Technician":
                role_id = 4;
                break;
        }

        return role_id;
    }

    public boolean updateUserRole(int userId, int newRole) {
        String sql = "UPDATE user SET Role_role_id = " + newRole + " WHERE account_no = " + userId;

        return database.write(sql, conn) != 0;
    }
}
