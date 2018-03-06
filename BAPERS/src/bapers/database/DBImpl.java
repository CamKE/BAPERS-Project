/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author CameronE
 */
public class DBImpl implements MyDBConnectivity {
    
    private Connection conn;
    private Statement s;
    private ResultSet rs;
    
    public DBImpl()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
        }catch(Exception ex){
            System.out.println("Error: "+ex);
        }
    }
    
    @Override
    public ResultSet read(String sql)
    {
        return rs;
    }
    
    @Override
    public int write(String sql)
    {
        return 0;
    }
    
    @Override
    public Connection connect()
    {
        return conn;
    }
}
