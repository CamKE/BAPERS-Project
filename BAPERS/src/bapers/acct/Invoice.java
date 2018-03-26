/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.acct;

import java.util.Date;

/**
 *
 * @author kelvin
 */
public class Invoice {
    
    public enum Status {
        AWAITINGPAYMENT, PAIDONTIME, PAIDLATE;
    }
    
    private final int invoiceNo;
    private final int jobJobNo;
    private final double totalPayable;
    private final Date dateIssued;
    private Status invoiceStatus = Status.AWAITINGPAYMENT;
    private final String invoiceLocation;

    public Invoice(int invoiceNo, int jobJobNo, double totalPayable, Date dateIssued, String invoiceStatus, String invoiceLocation) {
        this.invoiceNo = invoiceNo;
        this.jobJobNo = jobJobNo;
        this.totalPayable = totalPayable;
        this.dateIssued = dateIssued;
        this.invoiceLocation = invoiceLocation;
        
        switch (invoiceStatus) {
            case "Awaiting Payment":
                this.invoiceStatus = Status.AWAITINGPAYMENT;
                break;
            case "Paid late":
                this.invoiceStatus = Status.PAIDLATE;
                break;
            case "Paid on time":
                this.invoiceStatus = Status.PAIDONTIME;
                break;
            default:
                break;
        }
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public int getJobJobNo() {
        return jobJobNo;
    }

    public double getTotalPayable() {
        return totalPayable;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public Status getInvoiceStatus() {
        return invoiceStatus;
    }

    public String getInvoiceLocation() {
        return invoiceLocation;
    }

    public void setInvoiceStatus(Status invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
    
}
