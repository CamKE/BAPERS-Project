/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.payment;

/**
 *
 * @author kelvin
 */
public class Card {
    private final String last4Digits;
    private final String cardType;
    private final String expiryDate;

    public Card(String last4Digits, String cardType, String expiryDate) {
        this.last4Digits = last4Digits;
        this.cardType = cardType;
        this.expiryDate = expiryDate;
    }

    public String getLast4Digits() {
        return last4Digits;
    }

    public String getCardType() {
        return cardType;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
