/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.reporttype;

import bapers.database.DBImpl;
import java.sql.Connection;

/**
 *
 * @author CameronE
 */
public class Report {
    
    String[] reportPeriod;

    public Report(String[] reportPeriod) {
        this.reportPeriod = reportPeriod;
    }
    
    void generate()
    {
        
    }
}
