/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers;

import java.awt.Component;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kelvin
 */
public class AutomaticBackup extends Automatic {

    public AutomaticBackup(Calendar todaysDate, AutoBackupConfigData configData, Component component) {
        super(todaysDate, configData, component);
    }
    
    @Override
    public void run() {
        switch(configData.getBackupFrequency()) {
            case "weekly" :
                if (todaysDate.get(Calendar.DAY_OF_WEEK) == (Calendar.SUNDAY))
                    backup();
                break;
            
            case "monthly" :
                if (checkEachMonth())
                    backup();
                break;
            
            default :
                JOptionPane.showMessageDialog(component, "Error cannot perform backup. No frequency set");
                break;
        }
    }
    
    private void backup() {
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
                    dump = "C:/Program Files/MySQL/MySQL Server 5.7/bin/mysqldump.exe -uroot -ppassword -B bapers_data -r \"" + configData.getBackupLocation() + "\\" + filename + "\"";
                    p = runtime.exec(dump);
                    processComplete = p.waitFor();
                break;
            }
	                
            if (processComplete == 0)
                JOptionPane.showMessageDialog(component, "Complete auto backup");
            else
                System.out.println("Failed auto backup");
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(AutomaticBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean checkEachMonth() {
        final String datePerfromed = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(configData.getDatePerformed());
        final String[] token = datePerfromed.split("-");
        final int day = Integer.parseInt(token[2]);
        
        if (todaysDate.get(Calendar.MONTH) == Calendar.JANUARY && todaysDate.get(Calendar.DAY_OF_MONTH) == day)
            return true;
        if (todaysDate.get(Calendar.MONTH) == Calendar.FEBRUARY && todaysDate.get(Calendar.DAY_OF_MONTH) == day)
            return true;
        if (todaysDate.get(Calendar.MONTH) == Calendar.MARCH && todaysDate.get(Calendar.DAY_OF_MONTH) == day)
            return true;
        if (todaysDate.get(Calendar.MONTH) == Calendar.APRIL && todaysDate.get(Calendar.DAY_OF_MONTH) == day)
            return true;
        if (todaysDate.get(Calendar.MONTH) == Calendar.MAY && todaysDate.get(Calendar.DAY_OF_MONTH) == day)
            return true;
        if (todaysDate.get(Calendar.MONTH) == Calendar.JUNE && todaysDate.get(Calendar.DAY_OF_MONTH) == day)
            return true;
        if (todaysDate.get(Calendar.MONTH) == Calendar.JULY && todaysDate.get(Calendar.DAY_OF_MONTH) == day)
            return true;
        if (todaysDate.get(Calendar.MONTH) == Calendar.AUGUST && todaysDate.get(Calendar.DAY_OF_MONTH) == day)
            return true;
        if (todaysDate.get(Calendar.MONTH) == Calendar.SEPTEMBER && todaysDate.get(Calendar.DAY_OF_MONTH) == day)
            return true;
        if (todaysDate.get(Calendar.MONTH) == Calendar.OCTOBER && todaysDate.get(Calendar.DAY_OF_MONTH) == day)
            return true;
        if (todaysDate.get(Calendar.MONTH) == Calendar.NOVEMBER && todaysDate.get(Calendar.DAY_OF_MONTH) == day)
            return true;
        if (todaysDate.get(Calendar.MONTH) == Calendar.DECEMBER && todaysDate.get(Calendar.DAY_OF_MONTH) == day)
            return true;
        return false;
    }
    
}
