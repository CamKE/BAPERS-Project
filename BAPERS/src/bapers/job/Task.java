/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.job;

/**
 *
 * @author CameronE
 */
public class Task {

    int taskId;
    String description;
    int durationMin;
    String shelfslot;
    double price;

    public Task(int taskId, String description, int durationMin, String shelfslot, double price) {
        this.taskId = taskId;
        this.description = description;
        this.durationMin = durationMin;
        this.shelfslot = shelfslot;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public String getShelfslot() {
        return shelfslot;
    }

    public void setShelfslot(String shelfslot) {
        this.shelfslot = shelfslot;
    }

    /*Joseph*/
    private int taskID;
    private int duration;
    private int shelfSlot;
    private String departmentCode;
    private int indexInList;
    private String status;
    private int statusID;

    public Task(int taskID, String description, int duration, int shelfSlot, double price, String departmentCode, String status, int statusID) {
        this.departmentCode = departmentCode;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.shelfSlot = shelfSlot;
        this.taskID = taskID;
        this.status = status;
        this.statusID = statusID;
    }

    public Task(int taskID, String description, int duration, int shelfSlot, double price, String departmentCode) {
        this.departmentCode = departmentCode;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.shelfSlot = shelfSlot;
        this.taskID = taskID;

    }

    /**
     * @return the taskID
     */
    public int getTaskID() {
        return taskID;
    }

    /**
     * @param taskID the taskID to set
     */
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @return the shelfSlot
     */
    public int getShelfSlot() {
        return shelfSlot;
    }

    /**
     * @param shelfSlot the shelfSlot to set
     */
    public void setShelfSlot(int shelfSlot) {
        this.shelfSlot = shelfSlot;
    }

    /**
     * @return the departmentCode
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * @param departmentCode the departmentCode to set
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    /**
     * @return the indexInList
     */
    public int getIndexInList() {
        return indexInList;
    }

    /**
     * @param indexInList the indexInList to set
     */
    public void setIndexInList(int indexInList) {
        this.indexInList = indexInList;
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
