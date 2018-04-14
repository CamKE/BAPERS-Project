/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.job;

import java.util.ArrayList;

/**
 *
 * @author Josep
 */
public class StandardJob {

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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
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
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    private String code;
    private String description;
    private double price;
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



}
