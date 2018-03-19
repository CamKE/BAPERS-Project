/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.acct;

import java.sql.Date;

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
            
    private int paymentNo;
    private double total;
    private String paymentType;
    private int paymentDate;
    private int invoiceNumber;

    public Payment(int paymentNo, double total, int paymentDate, int invoiceNumber) {
        this.paymentNo = paymentNo;
        this.total = total;
        this.paymentDate = paymentDate;
        this.invoiceNumber = invoiceNumber;
    }
    
    
}
