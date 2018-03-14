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
public class Customer {
    private String accountNo;
    private String accountHolderName;
    private String email;
    private String address;
    private String phoneNumber;
    private boolean isSuspended;

    public Customer(String accountNo, String accountHolderName, String email, String address, String phoneNumber, boolean isSuspended) {
        this.accountNo = accountNo;
        this.accountHolderName = accountHolderName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isSuspended = isSuspended;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    
}
