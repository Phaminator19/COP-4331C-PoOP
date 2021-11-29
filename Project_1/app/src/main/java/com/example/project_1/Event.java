package com.example.project_1;




public class Event {
    String eventName;
    String eventDate;
    String eventTime;
    String eventDescription;
    String eventCreator;
    String eventGName;

    public Event() {
    }

    public String getEventName(){
        return eventName;
    }

    public void setEventName(String eventName){
        this.eventName = eventName;
    }

    public String getEventDate(){
        return eventDate;
    }

    public void setEventDate(String eventDate){
        this.eventDate = eventDate;
    }

    public String getEventTime(){
        return eventTime;
    }

    public void setEventTime(String eventTime){
        this.eventTime = eventTime;
    }

    public String getEventDescription(){
        return eventDescription;
    }

    public void setEventDescription(String eventDescription){
        this.eventDescription = eventDescription;
    }

    public String getEventCreator() { return eventCreator; }

    public void setEventCreator(String eventCreator){
        this.eventCreator = eventCreator;
    }

    public String getEventGName() { return eventGName; }

    public void setEventGName(String eventGName){
        this.eventGName = eventGName;
    }
}

