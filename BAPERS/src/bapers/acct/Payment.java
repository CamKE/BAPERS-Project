/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.acct;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Kelvin
 */
public class Payment {
    
//    paymentNo
//    total
//    paymentType
//    paymentDate
//    invoiceInvoiceNo
//    cardDetailsLast4digits
//    cardDetailsExpiryDate
            
    private final int paymentNo;
    private final double total;
    private final String paymentType;
    private final String paymentDate;
    private final int invoiceNumber;

    public Payment(int paymentNo, double total, String paymentType, String paymentDate, int invoiceNumber) {
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

    public String getPaymentDate() {
        return paymentDate;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }
        
}
