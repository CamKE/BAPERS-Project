/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.job;

import java.sql.Time;
import java.util.ArrayList;
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
    
    /*Joseph*/
    
        /**
     * @return the statusID
     */
    public int getStatusID() {
        return statusID;
    }

    /**
     * @param statusID the statusID to set
     */
    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    /**
     * @return the dateReceived
     */
    public Date getDateReceived() {
        return dateReceived;
    }

    /**
     * @param dateReceived the dateReceived to set
     */
    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    /**
     * @return the standardJobList
     */
    public ArrayList<StandardJob> getStandardJobList() {
        return standardJobList;
    }

    /**
     * @param standardJobList the standardJobList to set
     */
    public void setStandardJobList(ArrayList<StandardJob> standardJobList) {
        this.standardJobList = standardJobList;
    }

    /**
     * @return the amountOfStandardJobs
     */
    public int getAmountOfStandardJobs() {
        return amountOfStandardJobs;
    }

    /**
     * @param amountOfStandardJobs the amountOfStandardJobs to set
     */
    public void setAmountOfStandardJobs(int amountOfStandardJobs) {
        this.amountOfStandardJobs = amountOfStandardJobs;
    }

    /**
     * @return the jobNumber
     */
    public int getJobNumber() {
        return jobNumber;
    }

    /**
     * @param jobNumber the jobNumber to set
     */
    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }


    /**
     * @return the issuedBy
     */
    public String getIssuedBy() {
        return issuedBy;
    }

    /**
     * @param issuedBy the issuedBy to set
     */
    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }


    /**
     * @return the deadline
     */
    public Time getDeadline() {
        return deadline;
    }

    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(Time deadline) {
        this.deadline = deadline;
    }
    private int jobNumber;
    private String issuedBy;
    private Time deadline;
    private int amountOfStandardJobs;
    private ArrayList<StandardJob> standardJobList;
    private Date dateReceived;
    private int statusID;

    public Job(int jobNumber, Time deadline, String issuedBy, String status, Date dateReceived, boolean isCollected,int statusID) {
        this.jobNumber = jobNumber;
        this.deadline = deadline;
        this.issuedBy = issuedBy;
        this.status = status;
        this.isCollected = isCollected;
        this.dateReceived = dateReceived;
        this.statusID = statusID;
    }

    public Job(ArrayList<StandardJob> standardJobList) {
        this.standardJobList = new ArrayList<>();
    }
}


