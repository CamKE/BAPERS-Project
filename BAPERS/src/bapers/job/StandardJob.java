/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.job;

import java.util.ArrayList;

/**
 *
 * @author kelvin
 */
public class StandardJob {

    String code;
    String jobDescription;
    double price;
    int durationMin;

    public StandardJob(String code, String jobDescription, double price, int durationMin) {
        this.code = code;
        this.jobDescription = jobDescription;
        this.price = price;
        this.durationMin = durationMin;
    }

     public StandardJob(String code, String jobDescription, double price) {
        this.code = code;
        this.jobDescription = jobDescription;
        this.price = price;
    
    }
    
    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public String getCode() {
        return code;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /*Joseph*/
    private String description;

    private ArrayList<StandardJob> standardJobList;
    private String status;
    private int amountOfTasks;
    private int statusID;

    public StandardJob(String code, String description, double price, String status, int statusID) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.status = status;
        this.statusID = statusID;
    }
    
       /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the amountOfTasks
     */
    public int getAmountOfTasks() {
        return amountOfTasks;
    }

    /**
     * @param amountOfTasks the amountOfTasks to set
     */
    public void setAmountOfTasks(int amountOfTasks) {
        this.amountOfTasks = amountOfTasks;
    }

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

}


