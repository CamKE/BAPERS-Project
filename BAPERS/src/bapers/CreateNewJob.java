/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers;

/**
 *
 * @author lukas
 */
public class CreateNewJob {
    private String code;
    private String job_description;
    private double price;
    
    
    public CreateNewJob(String code, String job_description, double price){
        this.code = code;
        this.job_description = job_description;
        this.price = price;
                
        
    }

    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code = code;
    }
    
    
    public String getJob_description(){
        return job_description;
    }
    public void setJob_description(String job_description){
        this.job_description = job_description;
    }
    
    
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

}

