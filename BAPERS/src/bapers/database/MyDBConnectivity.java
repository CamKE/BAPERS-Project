/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.database;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author CameronE
 */
public interface MyDBConnectivity {
    
    public ResultSet read(String sql);
    
    public int write(String sql);
    
    public Connection connection();
    
    
}
