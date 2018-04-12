/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.reporttype;

import bapers.database.DBImpl;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author CameronE
 */
public class IndividualReport extends Report {

    public IndividualReport(String[] reportPeriod) {
        super(reportPeriod);
    }

    @Override
    public ArrayList<Object[][]> generate(DBImpl db, Connection conn) {
        ArrayList<Object[][]> shiftData = new ArrayList<>();

        return shiftData;
    }
}
