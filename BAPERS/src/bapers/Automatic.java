/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers;

import java.awt.Component;
import java.util.Calendar;

/**
 *
 * @author kelvin
 */
public abstract class Automatic {
    final Calendar todaysDate;
    final AutoBackupConfigData configData;
    final Component component;
    
    public Automatic(Calendar todaysDate, AutoBackupConfigData configData, Component component) {
        this.todaysDate = todaysDate;
        this.configData = configData;
        this.component = component;
    }
    
    public abstract void run();
}
