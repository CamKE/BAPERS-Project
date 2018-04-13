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
public class Task {
    String taskID;
    String description;
    int durationMin;
    String shelfSlot;
    String price;
    String departmentCode;

    public Task(String taskID, String description, int durationMin, String shelfSlot, String price, String departmentCode) {
        this.taskID = taskID;
        this.description = description;
        this.durationMin = durationMin;
        this.shelfSlot = shelfSlot;
        this.price = price;
        this.departmentCode = departmentCode;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
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

    public String getShelfSlot() {
        return shelfSlot;
    }

    public void setShelfSlot(String shelfSlot) {
        this.shelfSlot = shelfSlot;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
    
    
}
