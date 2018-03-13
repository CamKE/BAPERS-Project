/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author CameronE
 */
// An implementation of the database interface. All db interaction
// is through this class
public class DBImpl implements MyDBConnectivity {

    private ResultSet rs;

    // DB connection must be established anytime this class is 
    // instantiated


    // Executes sql queries that fetch data (SELECT), and returns the results
    @Override
    public ResultSet read(String sql, Connection conn) {

        try {
            Statement s = conn.createStatement();
            rs = s.executeQuery(sql);         
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rs;
    }

    // Executes sql queries that modify table(s) in the bapers db, returning 
    // an int that indicates the number of affected rows
    @Override
    public int write(String sql ,Connection conn) {
        int num_rows = 0;
        
        // Good practice to automatically close the resource to avoid 
        // tying up db resources
        try (Statement s = conn.createStatement()) {
            num_rows = s.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return num_rows;
    }

    // Establishes a connection to the bapers db, and returns that connection
    @Override
    public Connection connect() {
        Connection connect = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bapers_data?autoReconnect=true&useSSL=false", "root", "password");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return connect;
    }
}
