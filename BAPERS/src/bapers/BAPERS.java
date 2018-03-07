/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers;

import bapers.database.DBImpl;
import bapers.gui.Login;

/**
 *
 * @author CameronE
 */
public class BAPERS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBImpl connect = new DBImpl();
        // TODO code application logic here
        new Login().setVisible(true);
    }
    
}
