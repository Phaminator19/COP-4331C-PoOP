package com.example.project_1;
// this class holds all the message information - Estefania
public class Message
{
    private String message;
    private String userID;
    private String userName;
    private String date;
    private String time;

    public Message(String message, String userID, String userName, String date, String time)
    {
        this.message = message;
        this.userID = userID;
        this.userName = userName;
        this.date = date;
        this.time = time;
    }

    public Message(String message)
    {
        this.message = message;
    }

    public Message()
    {
        this.message = "empty";
    }

    public String getMessage()
    {
        return message;
    }

    public String getUserID()
    {
        return userID;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getDate()
    {
        return date;
    }

    public String getTime()
    {
        return time;
    }
}
