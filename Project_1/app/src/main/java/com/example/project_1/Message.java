package com.example.project_1;

public class Message {

    private String message; // message body
    private String userId; // data of the user that sent this message
    private String userName;
    private String timestamp;
    //@Exclude
    //private boolean belongsToCurrentUser;

    public Message() {
        this.message = "kalispera";
    }

    public Message(String message) {
        this.message = message;
    }


    public Message(String message, String userId, String userName, String timestamp) {
        this.message = message;
        this.userId = userId;
        this.userName = userName;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public String getUserName() {
        return this.userName;
    }
}