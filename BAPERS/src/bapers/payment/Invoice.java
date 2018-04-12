/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.payment;

/**
 *
 * @author Josep
 */
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;

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
     * @return the amountDue
     */
    public int getAmountDue() {
        return amountDue;
    }

    /**
     * @param amountDue the amountDue to set
     */
    public void setAmountDue(int amountDue) {
        this.amountDue = amountDue;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    private int invoiceNumber;
    private String customerName;
    private int amountDue;
    private Date date;
    
    public Invoice (int invoiceNumber, String customerName, int amountDue, Date date){
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.amountDue = amountDue;
        this.date = date;
    }
}
