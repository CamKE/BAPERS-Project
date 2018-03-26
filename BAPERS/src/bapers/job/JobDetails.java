/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.job;

import java.sql.Time;

/**
 *
 * @author Joseph
 */
public class JobDetails {



    private int jobNumber;
    private String status;
    private boolean isCollected;
    private String specialInstructions;
    private Time deadline;
    private String issuedBy;

    public JobDetails(int jobNumber, Time deadline, String issuedBy, String status) {
        this.jobNumber = jobNumber;
        this.deadline = deadline;
        this.issuedBy = issuedBy;
        this.status = status;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the isCollected
     */
    public boolean isIsCollected() {
        return isCollected;
    }

    /**
     * @param isCollected the isCollected to set
     */
    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }

    /**
     * @return the specialInstructions
     */
    public String getSpecialInstructions() {
        return specialInstructions;
    }

    /**
     * @param specialInstructions the specialInstructions to set
     */
    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
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

}
