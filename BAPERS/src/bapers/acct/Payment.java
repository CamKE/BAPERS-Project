/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.acct;

import java.util.Date;

/**
 *
 * @author Kelvin
 */
public class Payment {
    
    private final int paymentNo;
    private final double total;
    private final String paymentType;
    private final Date paymentDate;
    private final int invoiceNumber;

    public Payment(int paymentNo, double total, String paymentType, Date paymentDate, int invoiceNumber) {
        this.paymentNo = paymentNo;
        this.total = total;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.invoiceNumber = invoiceNumber;
    }

    public int getPaymentNo() {
        return paymentNo;
    }

    public double getTotal() {
        return total;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }
        
}
