/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.acct;

import java.awt.Component;
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
    Component component;
    
    public AutoBackup(AutoBackupConfig configData, Component component) {
        this.configData = configData;
        this.component = component;
    }
    
    @Override
    public void run() {
        
        if (configData.getBackupMode().equals("auto")) {
        try {
            Runtime runtime = Runtime.getRuntime();

            String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
            currentDate = currentDate.replace(' ', '_');
            currentDate = currentDate.replace(':', '-');
            final String filename = "BAPERS_" + currentDate + ".sql";
                
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
                JOptionPane.showMessageDialog(component, "Complete auto backup");
            else
                System.out.println("Failed auto backup");
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(AutoBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
        } else if (configData.getBackupMode().equals("reminder")) {
            JOptionPane.showMessageDialog(component, "Back is needed");
        }
        
    }
}
