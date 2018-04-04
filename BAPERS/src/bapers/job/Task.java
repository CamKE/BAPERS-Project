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
    
}
