/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.task;

/**
 *
 * @author Joseph
 */
public class TaskInformation {

    private int taskID;
    private String description;
    private int duration;
    private int shelfSlot;
    private double price;
    private String departmentCode;
    
    public TaskInformation(int taskID,String description, int duration,int shelfSlot, double price, String departmentCode){
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

}
