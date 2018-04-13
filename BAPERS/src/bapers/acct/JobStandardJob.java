/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.acct;

/**
 *
 * @author kelvin
 */
public class JobStandardJob {
    private String jobJobNo;
    private String standardJobCode;
    private String statusStatusType;

    public JobStandardJob(String jobJobNo, String standardJobCode, String statusStatusType) {
        this.jobJobNo = jobJobNo;
        this.standardJobCode = standardJobCode;
        this.statusStatusType = statusStatusType;
    }

    public String getJobJobNo() {
        return jobJobNo;
    }

    public void setJobJobNo(String jobJobNo) {
        this.jobJobNo = jobJobNo;
    }

    public String getStandardJobCode() {
        return standardJobCode;
    }

    public void setStandardJobCode(String standardJobCode) {
        this.standardJobCode = standardJobCode;
    }

    public String getStatusStatusType() {
        return statusStatusType;
    }

    public void setStatusStatusType(String statusStatusType) {
        this.statusStatusType = statusStatusType;
    }
    
    
    
}
