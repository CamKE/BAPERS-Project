/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.acct;

import java.util.Date;

/**
 *
 * @author kelvin
 */
public class Job {
    String jobNo;
    String UserAccountNo;
    String customerAccountNo;
    String status;
    boolean isCollected;
    Date deadlineDateRecived;
    Date deadlineCompletionTime;
    String specialInstruction;

    public Job(String jobNo, String UserAccountNo, String customerAccountNo, String status, boolean isCollected, Date deadlineDateRecived, Date deadlineCompletionTime, String specialInstruction) {
        this.jobNo = jobNo;
        this.UserAccountNo = UserAccountNo;
        this.customerAccountNo = customerAccountNo;
        this.status = status;
        this.isCollected = isCollected;
        this.deadlineDateRecived = deadlineDateRecived;
        this.deadlineCompletionTime = deadlineCompletionTime;
        this.specialInstruction = specialInstruction;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getUserAccountNo() {
        return UserAccountNo;
    }

    public void setUserAccountNo(String UserAccountNo) {
        this.UserAccountNo = UserAccountNo;
    }

    public String getCustomerAccountNo() {
        return customerAccountNo;
    }

    public void setCustomerAccountNo(String customerAccountNo) {
        this.customerAccountNo = customerAccountNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsCollected() {
        return isCollected;
    }

    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }

    public Date getDeadlineDateRecived() {
        return deadlineDateRecived;
    }

    public void setDeadlineDateRecived(Date deadlineDateRecived) {
        this.deadlineDateRecived = deadlineDateRecived;
    }

    public Date getDeadlineCompletionTime() {
        return deadlineCompletionTime;
    }

    public void setDeadlineCompletionTime(Date deadlineCompletionTime) {
        this.deadlineCompletionTime = deadlineCompletionTime;
    }

    public String getSpecialInstruction() {
        return specialInstruction;
    }

    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }
    
    
}
