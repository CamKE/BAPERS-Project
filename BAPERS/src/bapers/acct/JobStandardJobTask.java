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
public class JobStandardJobTask {
    String standardJobNo;
    String standardJobCode;
    String taskID;
    String status;
    String userAccountNo;

    public JobStandardJobTask(String standardJobNo, String standardJobCode, String taskID, String status, String userAccountNo) {
        this.standardJobNo = standardJobNo;
        this.standardJobCode = standardJobCode;
        this.taskID = taskID;
        this.status = status;
        this.userAccountNo = userAccountNo;
    }

    public String getStandardJobNo() {
        return standardJobNo;
    }

    public void setStandardJobNo(String standardJobNo) {
        this.standardJobNo = standardJobNo;
    }

    public String getStandardJobCode() {
        return standardJobCode;
    }

    public void setStandardJobCode(String standardJobCode) {
        this.standardJobCode = standardJobCode;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserAccountNo() {
        return userAccountNo;
    }

    public void setUserAccountNo(String userAccountNo) {
        this.userAccountNo = userAccountNo;
    }
    
}
