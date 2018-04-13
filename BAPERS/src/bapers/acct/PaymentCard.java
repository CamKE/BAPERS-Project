/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.acct;

/**
 *
 * @author kelvin
 */
public class PaymentCard extends Payment {
    private final String cardType;
    private final String cardDetailsLast4Digits;
    private final String cardDetailsExpiryDate;

    public PaymentCard(String paymentNo, double total, String paymentType, String paymentDate, int invoiceNumber, String cardType, String cardDetailsLast4Digits, String cardDetailsExpiryDate) {
        super(paymentNo, total, paymentType, paymentDate, invoiceNumber);
        this.cardType = cardType;
        this.cardDetailsLast4Digits = cardDetailsLast4Digits;
        this.cardDetailsExpiryDate = cardDetailsExpiryDate;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardDetailsLast4Digits() {
        return cardDetailsLast4Digits;
    }

    public String getCardDetailsExpiryDate() {
        return cardDetailsExpiryDate;
    }
    
}
