/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.customer;

import java.sql.Timestamp;

/**
 *
 * @author kelvin
 */
public class CustomerDetails {

    int accountNo;
    String accountHolderName;
    String prefix;
    String firstName;
    String lastName;
    String streetName;
    String postCode;
    String city;
    String phoneNumber;
    Boolean isSuspended;
    Boolean inDefault;
    Boolean isValued;
    Timestamp regDate;
    int buildingNo;
    String email;

    public CustomerDetails(int accountNo, String accountHolderName, String prefix, String firstName, String lastName, String streetName, String postCode, String city, String phoneNumber, Boolean isSuspended, Boolean inDefault, Boolean isValued, Timestamp regDate, int buildingNo, String email) {
        this.accountNo = accountNo;
        this.accountHolderName = accountHolderName;
        this.prefix = prefix;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.postCode = postCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.isSuspended = isSuspended;
        this.inDefault = inDefault;
        this.isValued = isValued;
        this.regDate = regDate;
        this.buildingNo = buildingNo;
        this.email = email;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Boolean getIsSuspended() {
        return isSuspended;
    }

    public String getCustomerStatus() {
        if (isSuspended) {
            return "Suspended";
        } else {
            return "Active";
        }
    }

    public String getCustomerType() {
        if (isValued) {
            return "Valued";
        } else {
            return "Normal";
        }
    }

    public void setIsSuspended(Boolean isSuspended) {
        this.isSuspended = isSuspended;
    }

    public Boolean getInDefault() {
        return inDefault;
    }

    public void setInDefault(Boolean inDefault) {
        this.inDefault = inDefault;
    }

    public Boolean getIsValued() {
        return isValued;
    }

    public void setIsValued(Boolean isValued) {
        this.isValued = isValued;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public int getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(int buildingNo) {
        this.buildingNo = buildingNo;
    }

}
