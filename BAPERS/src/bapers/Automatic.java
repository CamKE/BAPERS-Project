/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers;

import bapers.controller.Controller;
import java.awt.Component;
import java.util.Calendar;
import java.util.Timer;

/**
 *
 * @author kelvin
 */
public abstract class Automatic {
    final Calendar todaysDate;
    Calendar targetDate;
    AutoBackupConfigData configData;
    final Component component;
    final Timer timer;
    final Controller controller;

    public Automatic(Calendar todaysDate, Calendar targetDate, AutoBackupConfigData configData, Component component, Timer timer, Controller controller) {
        this.todaysDate = todaysDate;
        this.targetDate = targetDate;
        this.configData = configData;
        this.component = component;
        this.timer = timer;
        this.controller = controller;
    }
    
    public abstract void run();
    public abstract void setInitTargetNextWeek();
    
}
