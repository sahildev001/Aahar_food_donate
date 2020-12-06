package com.example.aahar.Models;

/*Created by Sahil kumar
 * for practice if you have any problem to understan this please contact cu.17bcs2904@gmail.com
 * thankyou*/

public class Users {
    String profilepic, username, email, password, userId, phoneno,Donor,reciver;


    public Users(String profilepic, String username, String email, String password, String userId, String phoneno, String donor, String reciver) {
        this.profilepic = profilepic;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.phoneno = phoneno;
        Donor = donor;
        this.reciver = reciver;
    }
    public Users(){}
    //signUp constructor
    public Users( String username, String email, String password, String phoneno) {

        this.username = username;
        this.email = email;
        this.password = password;

        this.phoneno = phoneno;

    }


    public String getProfilepic(String s) {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getDonor() {
        return Donor;
    }

    public void setDonor(String donor) {
        Donor = donor;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }
}