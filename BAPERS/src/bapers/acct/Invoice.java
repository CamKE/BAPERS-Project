/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.acct;

import java.sql.Date;

/**
 *
 * @author kelvin
 */
public class Invoice {
    
    public enum Status {
        AWAITINGPAYMENT, PAIDONTIME, PAIDLATE;
    }
    
    private final int Invoice_no;
    private final int Job_job_no;
    private final double total_payable;
    private final Date date_issued;
    private Status invoice_status = Status.AWAITINGPAYMENT;
    private final String invoice_location;

    public Invoice(int Invoice_no, int Job_job_no, double total_payable, Date date_issued, String invoice_status, String invoice_location) {
        this.Invoice_no = Invoice_no;
        this.Job_job_no = Job_job_no;
        this.total_payable = total_payable;
        this.date_issued = date_issued;
        this.invoice_location = invoice_location;
        
        if (invoice_status.equals("Awaiting Payment")) {
            this.invoice_status = Status.AWAITINGPAYMENT;
        } else if (invoice_status.equals("Paid Late")) {
            this.invoice_status = Status.PAIDLATE;
        } else if (invoice_status.equals("Paid On Time")) {
            this.invoice_status = Status.PAIDONTIME;
        }
    }

    public int getInvoice_no() {
        return Invoice_no;
    }

    public int getJob_job_no() {
        return Job_job_no;
    }

    public double getTotal_payable() {
        return total_payable;
    }

    public Date getDate_issued() {
        return date_issued;
    }

    public Status getInvoice_status() {
        return invoice_status;
    }

    public String getInvoice_location() {
        return invoice_location;
    }

    public void setInvoice_status(Status invoice_status) {
        this.invoice_status = invoice_status;
    }
    
}
