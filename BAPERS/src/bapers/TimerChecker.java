/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers;

import bapers.controller.Controller;
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
public class TimerChecker extends TimerTask {
    private Calendar todaysDate;
    private final Calendar targetDate;
    private final Component component;
    final AutoBackupConfigData configData;
    private Controller controller;

    public TimerChecker(Calendar todaysDate, Calendar targetDate, Component component, AutoBackupConfigData configData, Controller controller) {
        this.todaysDate = todaysDate;
        this.targetDate = targetDate;
        this.component = component;
        this.configData = configData;
        this.controller = controller;
    }

    @Override
    public void run() {
        // checking if timer is working
        //System.out.println("Timer checker bit is runnning");
        //System.out.println("Todays date: " + todaysDate.get(Calendar.DATE) + " Todays month: " + todaysDate.get(Calendar.MONTH));
        //System.out.println("Target date: " + targetDate.get(Calendar.DATE) + " Target month: " + targetDate.get(Calendar.MONTH));
        //System.out.println("");
        
        // update current/today date
        todaysDate = Calendar.getInstance();
        
        
        switch (configData.getBackupMode()) {
            case "auto" : 
                switch (configData.getBackupFrequency()) {
                    case "weekly" : 
                        if (todaysDate.get(Calendar.DATE) == (targetDate.get(Calendar.DATE))) {
                            backup();
                            proceed(configData.getBackupFrequency());
                        }
                        break;
                
                    case "monthly" :
                        if ((todaysDate.get(Calendar.DATE)) == (targetDate.get(Calendar.DATE)) 
                            && (todaysDate.get(Calendar.MONTH)) == (targetDate.get(Calendar.MONTH))) {
                            backup();
                            proceed(configData.getBackupFrequency());
                        }
                        break;
                }
                break;
                
            case "reminder" : 
                switch (configData.getBackupFrequency()) {
                    case "weekly" : 
                        if (todaysDate.get(Calendar.DATE) == (targetDate.get(Calendar.DATE))) {
                            remind();
                            proceed(configData.getBackupFrequency());
                        }
                        break;
                
                    case "monthly" :
                        if ((todaysDate.get(Calendar.DATE)) == (targetDate.get(Calendar.DATE))
                                && (todaysDate.get(Calendar.MONTH)) == (targetDate.get(Calendar.MONTH))) {
                            remind();
                            proceed(configData.getBackupFrequency());
                        }
                        break;
                }
                break;
        }
    }
    
    public void proceed(String frq) {
        switch (frq) {
            case "weekly" : targetDate.add(Calendar.DATE, 7); break;
            case "monthly" : targetDate.add(Calendar.MONTH, 1); break;
        }
    }
    
    public void remind() {
        JOptionPane.showMessageDialog(component, "Please setup backup for automatic backup or perform a backup");
    }
    
    public void backup() {
        try {
            Runtime runtime = Runtime.getRuntime();

            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
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
	                
            if (processComplete == 0) {
                controller.recordBackup(currentDate,filename, configData.getBackupLocation());
                JOptionPane.showMessageDialog(component, "Complete auto backup");
            } else {
                System.out.println("Failed auto backup");
            }
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(AutomaticBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
