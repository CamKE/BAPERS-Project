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
public class StandardJob {
    String code;
    String jobDescription;
    double price;

    public StandardJob(String code, String jobDescription, double price) {
        this.code = code;
        this.jobDescription = jobDescription;
        this.price = price;
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
    
    
}
