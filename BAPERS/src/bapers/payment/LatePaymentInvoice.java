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

public class LatePaymentInvoice {

    /**
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * @return the streetName
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * @param streetName the streetName to set
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the postCode
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * @param postCode the postCode to set
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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
    private String streetName;
    private String city;
    private String postCode;
    private String lastName;
    private String prefix;
    
    public LatePaymentInvoice (int invoiceNumber, String customerName, int amountDue, Date date, String streetName,String city,String postCode, String lastName, String prefix){
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.amountDue = amountDue;
        this.date = date;
        this.streetName = streetName;
        this.city = city;
        this.postCode = postCode;
        this.lastName = lastName;
        this.prefix = prefix;
    }
}
