/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.user;

import java.sql.Blob;
import java.sql.Timestamp;

/**
 *
 * @author CameronE
 */
// Class to hold a user's information
public class UserDetails {
    
    private int account_no;
    private String firstname;
    private String lastname;
    private Blob password;
    private Timestamp datetime;
    private String role;

    public UserDetails(int account_no, String firstname, String lastname, Blob password, Timestamp datetime, String role) {
        this.account_no = account_no;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.datetime = datetime;
        this.role = role;
    }

    public int getAccount_no() {
        return account_no;
    }

    public void setAccount_no(int account_no) {
        this.account_no = account_no;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Blob getPassword() {
        return password;
    }

    public void setPassword(Blob password) {
        this.password = password;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role_id) {
        this.role = role_id;
    }
    
    
}
