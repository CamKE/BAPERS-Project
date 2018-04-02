/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.acct;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kelvin
 */
public class AutoBackup extends TimerTask {

    AutoBackupConfig test;
    public AutoBackup(AutoBackupConfig test) {
        this.test = test;
    }
    
    @Override
    public void run() {
        //System.out.println("Done");
        try {
            Runtime runtime = Runtime.getRuntime();
            
            String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
            date = date.replace(' ', '_');
            date = date.replace(':', '-');
            String filename = "BAPERS_" + date + ".sql";
            //System.out.println(filename);
            
            int processComplete;
            String dump = "/usr/local/mysql-5.7.21-macos10.13-x86_64/bin/mysqldump -uroot -pPassword --databases bapers_data > " + test.getBackupLocation() +"/" + filename;
            String[] cmdarray = {"/bin/sh","-c", dump};
            Process p = Runtime.getRuntime().exec(cmdarray);
            processComplete = p.waitFor();
            
            if (processComplete == 0)
                System.out.println("Complete auto backup");
            else
                System.out.println("Failed auto backup");
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(AutoBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
