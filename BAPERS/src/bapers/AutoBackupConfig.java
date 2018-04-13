/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers;

/**
 *
 * @author kelvin
 */
public class AutoBackupConfig {
    private final String backupMode;
    private final String backupFrequency;
    private final String backupLocation;

    public AutoBackupConfig(String backupMode, String backupFrequency, String backupLocation) {
        this.backupMode = backupMode;
        this.backupFrequency = backupFrequency;
        this.backupLocation = backupLocation;
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
    
}
