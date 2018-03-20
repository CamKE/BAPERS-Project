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
public class PaymentDetails {
    
//    paymentNo
//    total
//    paymentType
//    paymentDate
//    invoiceInvoiceNo
//    cardDetailsLast4digits
//    cardDetailsExpiryDate
            
    private final int[] paymentNo;
    private final int[] invoiceNumber;
    private final double total;
    private final String paymentType;
    private final Date paymentDate;

    public PaymentDetails(int[] paymentNo, int[] invoiceNumber, double total, String paymentType, Date paymentDate) {
        this.paymentNo = paymentNo;
        this.invoiceNumber = invoiceNumber;
        this.total = total;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
    }

    public int[] getPaymentNo() {
        return paymentNo;
    }

    public int[] getInvoiceNumber() {
        return invoiceNumber;
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

    
    
}
