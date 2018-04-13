/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.acct;

import java.text.SimpleDateFormat;

/**
 *
 * @author kelvin
 */
public class AutomaticBackup implements Automatic {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
//    private final int dayOfWeek;
    
//    public AutomaticBackup() {
//        
//    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
