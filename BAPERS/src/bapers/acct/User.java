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
public class User {
    String accountNo;
    String firstName;
    String lastName;
    String password;
    Date registrationDate;
    String roleID;

    public User(String accountNo, String firstName, String lastName, String password, Date registrationDate, String roleID) {
        this.accountNo = accountNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.registrationDate = registrationDate;
        this.roleID = roleID;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
}
