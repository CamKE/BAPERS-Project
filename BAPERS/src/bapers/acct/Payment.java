/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.acct;

/**
 *
 * @author Kelvin
 */
public class Payment {
    
    private final String paymentNo;
    private final double total;
    private final String paymentType;
    private final String paymentDate;
    private final int invoiceNumber;

    public Payment(String paymentNo, double total, String paymentType, String paymentDate, int invoiceNumber) {
        this.paymentNo = paymentNo;
        this.total = total;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.invoiceNumber = invoiceNumber;
    }

    public String getPaymentNo() {
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
