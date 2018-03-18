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

    public Controller() {
        database = new DBImpl();
        conn = database.connect();
    }

    public boolean findUser(String userNumber, String firstName, String lastName, String role) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * from user WHERE 1=1 ");

        if (!userNumber.isEmpty()) {
            sb.append("AND account_no = ").append(Integer.parseInt(userNumber));
        } else {
            boolean a = !firstName.isEmpty();
            boolean b = !lastName.isEmpty();
            boolean c = !role.equals("Any");

            if (!firstName.isEmpty()) {
                sb.append(" AND firstname = '").append(firstName).append("'");
            }
            if (!lastName.isEmpty()) {
                sb.append(" AND lastname = '").append(lastName).append("'");
            }
            if (!role.equals("Any")) {
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
//                    default: // Optional
//                    // Statements
                }
                sb.append(" AND Role_role_id = ").append(role_id);
            }
        }
        System.out.println(sb.toString());
        ResultSet rs = database.read(sb.toString(), conn);
        System.out.println("yo");
        return false;
    }

}
