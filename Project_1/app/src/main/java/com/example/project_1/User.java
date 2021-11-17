package com.example.project_1;

public class User {

    private String UserName;
    private String EmailAddress;
    private String password;

    public String getUserName() {
        return UserName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public String getPassword() {
        return password;
    }

    public User(String user_name, String emailAddress, String password) {
        if ((isPasswordAtLeast15Characters(password)) && (isValueAtLeastOne(user_name) || isValueAtLeastOne(emailAddress)))
        {
            this.UserName = user_name;
            this.EmailAddress = emailAddress;
            this.password = password;
        }
        else throw new InvalidValueException("User did not get edited. " + "User item need to has a valid password and email address or user name");
    }


    public void updateUser (String user_name, String emailAddress, String password) {
        if ((isPasswordAtLeast15Characters(password)) && (isValueAtLeastOne(user_name) || isValueAtLeastOne(emailAddress))) {
            setUserName(user_name);
            setEmailAddress(emailAddress);
            setPassword(password);
        } else throw new InvalidValueException("User did not get edited. " + "User item need to has a valid password and email address or user name");

    }

    private boolean isValueAtLeastOne(String value) {
        return value.length() > 0;
    }

    private boolean isPasswordAtLeast15Characters(String password) {
        return password.length() >= 15;
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

//    @Override
//    public String toString(){
//        return UserName + ", " + EmailAddress + ", " + "and password: " + password;
//    }

}
