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
// Interface that establishes the function
// that can be done when interacting with the DB
// regardless of how they are implemented
public interface MyDBConnectivity {
    
    // Executes sql queries that fetch data (SELECT), and returns the results
    public ResultSet read(String sql);
    
    // Executes sql queries that modify table(s) in the bapers db, returning 
    // an int that indicates the status of the query execution
    public int write(String sql);
    
    // Establishes a connection to the bapers db, and returns that connection
    public Connection connect();
    
}
