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
    private double amount;
    private boolean card;
    private Date dateOfPayment;

    public Payment(double amount, boolean card, Date dateOfPayment) {
        this.amount = amount;
        this.card = card;
        this.dateOfPayment = dateOfPayment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isCard() {
        return card;
    }

    public void setCard(boolean card) {
        this.card = card;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }
    
}
