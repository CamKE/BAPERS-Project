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
import javax.swing.JOptionPane;

/**
 *
 * @author kelvin
 */
public class AutoBackup extends TimerTask {

    AutoBackupConfig configData;
    Calendar date;
    
    public AutoBackup(AutoBackupConfig configData, Calendar date) {
        this.configData = configData;
        this.date = date;
    }
    
    @Override
    public void run() {
        System.out.println(date.get(Calendar.DAY_OF_WEEK));
        try {
            Runtime runtime = Runtime.getRuntime();

            String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
            date = date.replace(' ', '_');
            date = date.replace(':', '-');
            final String filename = "BAPERS_" + date + ".sql";
                
            int processComplete = -1;
            Process p;
            String dump;

            final String os = System.getProperty("os.name");
                
            switch(os) {
                case "Mac OS X" :
                    dump = "/usr/local/mysql-5.7.21-macos10.13-x86_64/bin/mysqldump -uroot -pPassword --databases bapers_data > " + configData.getBackupLocation() +"/" + filename;
                    String[] cmdarray = {"/bin/sh","-c", dump};
                    p = Runtime.getRuntime().exec(cmdarray);
                    processComplete = p.waitFor();
                    break;
                
                case "Windows 10" : 
                    dump = "/usr/local/mysql-5.7.21-macos10.13-x86_64/bin/mysqldump -uroot -ppassword -B bapers_data -r \"" + configData.getBackupLocation() + "\\" + filename + "\"";
                    p = runtime.exec(dump);
                    processComplete = p.waitFor();
                    break;
            }
                
            if (processComplete == 0)
                JOptionPane.showMessageDialog(null, "Complete auto backup");
            else
                System.out.println("Failed auto backup");
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(AutoBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
