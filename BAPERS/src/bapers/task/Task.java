/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.task;

/**
 *
 * @author lukas
 */
public class Task {
    int task_id;
    String description;
    int duration_min;
    int shelf_slot;
    double price;
    String Department_department;
    

    public Task(int task_id, String description, int duration_min, int shelf_slot, double price, String Department_department) {
        this.task_id = task_id;
        this.description = description;
        this.duration_min = duration_min;
        this.shelf_slot = shelf_slot;
        this.price = price;
        this.Department_department = Department_department;
    }

    public int getTask_id() {
        return task_id;
    }
    
    public String getTaskDescription() {
        return description;
    }
    
    public int getDuration_min(){
        return duration_min;
    }
    
    public int getShelf_slot(){
        return shelf_slot;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getDepartment_department(){
        return Department_department;
    }

    
    
    
}
