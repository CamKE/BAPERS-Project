/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.acct;

import java.util.Date;

/**
 *
 * @author kelvin
 */
public class Customer {
    private String accountNo;
    private String accountHolderName;
    private String prefix;
    private String firstName;
    private String surName;
    private String streetName;
    private String postCode;
    private String city;
    private String phoneNumber;
    private boolean isSuspended;
    private boolean inDefault;
    private boolean isValued;
    private Date registrationDate;
    private String buildingNo;
    private String email;

    public Customer(String accountNo, String accountHolderName, String prefix, String firstName, String surName, String streetName, String postCode, String city, String phoneNumber, boolean isSuspended, boolean inDefault, boolean isValued, Date registrationDate, String buildingNo, String email) {
        this.accountNo = accountNo;
        this.accountHolderName = accountHolderName;
        this.prefix = prefix;
        this.firstName = firstName;
        this.surName = surName;
        this.streetName = streetName;
        this.postCode = postCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.isSuspended = isSuspended;
        this.inDefault = inDefault;
        this.isValued = isValued;
        this.registrationDate = registrationDate;
        this.buildingNo = buildingNo;
        this.email = email;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isIsSuspended() {
        return isSuspended;
    }

    public void setIsSuspended(boolean isSuspended) {
        this.isSuspended = isSuspended;
    }

    public boolean isInDefault() {
        return inDefault;
    }

    public void setInDefault(boolean inDefault) {
        this.inDefault = inDefault;
    }

    public boolean isIsValued() {
        return isValued;
    }

    public void setIsValued(boolean isValued) {
        this.isValued = isValued;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
