/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers;

import java.util.Date;

/**
 *
 * @author kelvin
 */
public class AutoBackupConfigData {
    private final String backupMode;
    private final String backupFrequency;
    private final String backupLocation;
    private final Date datePerformed;
    private final Date targetDate;

    public AutoBackupConfigData(String backupMode, String backupFrequency, String backupLocation, Date datePerformed, Date targetDate) {
        this.backupMode = backupMode;
        this.backupFrequency = backupFrequency;
        this.backupLocation = backupLocation;
        this.datePerformed = datePerformed;
        this.targetDate = targetDate;
    }

    public String getBackupMode() {
        return backupMode;
    }

    public String getBackupFrequency() {
        return backupFrequency;
    }

    public String getBackupLocation() {
        return backupLocation;
    }

    public Date getDatePerformed() {
        return datePerformed;
    }

    public Date getTargetDate() {
        return targetDate;
    }
    
    
    
}
