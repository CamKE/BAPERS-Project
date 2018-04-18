/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers;

import java.awt.Component;
import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

/**
 *
 * @author kelvin
 */
public class AutomaticReminder extends Automatic{

    public AutomaticReminder(Calendar todaysDate, Calendar targetDate, AutoBackupConfigData configData, Component component, Timer timer) {
        super(todaysDate, targetDate, configData, component, timer);
    }

    @Override
    public void run() {
        switch(configData.getBackupFrequency()) {
            case "weekly" :
                //System.out.println("this is working (week)");
                setInitTargetNextWeek();
                timer.schedule(new TimerChecker(todaysDate, targetDate, component, configData), 0, TimeUnit.SECONDS.toMillis(5));
                break;
            
            case "monthly" :
                //System.out.println("this is working (month)");
                timer.schedule(new TimerChecker(todaysDate, targetDate, component, configData), 0, TimeUnit.SECONDS.toMillis(5));
                break;
            
            default :
                JOptionPane.showMessageDialog(component, "Error cannot perform backup. No frequency set.");
                break;
        }
    }

    @Override
    public void setInitTargetNextWeek() {
        targetDate.add(Calendar.DATE, 7);
    }
    
}
