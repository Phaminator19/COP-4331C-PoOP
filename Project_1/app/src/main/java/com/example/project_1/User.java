package com.example.project_1;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String UserName;
    private String EmailAddress;
    private String password;
    public String userBio;
    public String FirstName;
    public String LastName;

    public String getUserName() {
        return UserName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public String getPassword() {
        return password;
    }

//    public String getUserBio() {
//        return userBio;
//    }

    private void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getFirstName() {
        return FirstName;
    }

    private void setFirstName(String firstName) {
        FirstName = firstName;
    }

    private void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getLastName() {
        return LastName;
    }


    public User(String user_name, String emailAddress, String password) {
            this.UserName = user_name;
            this.EmailAddress = emailAddress;
            this.password = password;
    }


    public void updateUser (String user_name, String emailAddress, String password) {
            setUserName(user_name);
            setEmailAddress(emailAddress);
            setPassword(password);
    }


    private boolean isValueAtLeastOne(String value) {
        return value.length() > 0;
    }


    private boolean isPasswordAtLeast10Characters(String password) {
        return password.length() >= 10;
    }

    static class InvalidValueException extends IllegalArgumentException {
        public InvalidValueException(String msg) {
            super(msg);
        }
    }

    private void setUserName(String user_name) {
        this.UserName = user_name;
    }

    private void setEmailAddress(String EmailAddress) {
        this.EmailAddress = EmailAddress;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void customizeProfile(String First_Name, String userBio, String Last_Name) {
        setFirstName(First_Name);
        setLastName(Last_Name);
        setUserBio(userBio);
    }

//    @Override
//    public String toString(){
//        return UserName + ", " + EmailAddress + ", " + "and password: " + password;
//    }

}
