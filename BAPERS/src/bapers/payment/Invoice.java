/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.payment;

import java.sql.Date;

/**
 *
 * @author Josep
 */
public class Invoice {

    /**
     * @return the invoiceNumber
     */
    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * @param invoiceNumber the invoiceNumber to set
     */
    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the totalPayable
     */
    public int getTotalPayable() {
        return totalPayable;
    }

    /**
     * @param totalPayable the totalPayable to set
     */
    public void setTotalPayable(int totalPayable) {
        this.totalPayable = totalPayable;
    }

    /**
     * @return the dateIssued
     */
    public Date getDateIssued() {
        return dateIssued;
    }

    /**
     * @param dateIssued the dateIssued to set
     */
    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }
    private int invoiceNumber;
    private String customerName;
    private int totalPayable;
    private Date dateIssued;
    
    public Invoice(int invoiceNumber, String customerName, int totalPayable, Date dateIssued){
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.totalPayable = totalPayable;
        this.dateIssued = dateIssued;
    }
}
