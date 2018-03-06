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
public class DBImpl implements MyDBConnectivity {

    private Connection conn;
    private Statement s;
    private ResultSet rs;

    public DBImpl() {
        conn = connect();
        rs = read("SELECT variable FROM sys.sys_config");
        System.out.println("Records from db");
        try {
            while (rs.next()) {
                String variable = rs.getString("variable");
                System.out.println("variable: " + variable);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ResultSet read(String sql) {
        try {
            s = conn.createStatement();
            return s.executeQuery(sql);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return rs;
    }

    @Override
    public int write(String sql) {
        return 0;
    }

    @Override
    public Connection connect() {
        Connection connect = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?autoReconnect=true&useSSL=false", "root", "password");

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

        return connect;
    }
}
