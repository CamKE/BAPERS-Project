/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bapers.payment;

import java.util.Date;

/**
 *
 * @author kelvin
 */
public class PaymentCash extends PaymentDetails {

    public PaymentCash(int[] paymentNo, int[] invoiceNumber, double total, String paymentType, Date paymentDate) {
        super(paymentNo, invoiceNumber, total, paymentType, paymentDate);
    }
    
}
