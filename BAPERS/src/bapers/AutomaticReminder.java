/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author kelvin
 */
public class AutomaticReminder extends Automatic{

    public AutomaticReminder(Calendar todaysDate, AutoBackupConfigData configData, Component component) {
        super(todaysDate, configData, component);
    }

    @Override
    public void run() {
        switch(configData.getBackupFrequency()) {
            case "weekly" :
                if (todaysDate.get(Calendar.DAY_OF_WEEK) == (Calendar.SUNDAY))
                    remind();
                break;
            
            case "monthly" :
                if (checkEachMonth())
                    remind();
                break;
            
            default :
                JOptionPane.showMessageDialog(component, "Error cannot perform backup. No frequency set");
                break;
        }
    }
    
    private void remind() {
        JOptionPane.showMessageDialog(component, "Please setup backup for automatic backup or perform a backup");
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
