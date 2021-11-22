package com.example.project_1;


import androidx.appcompat.app.AppCompatActivity;

public class Event extends AppCompatActivity {
    String eventtitle;
    String eventcategory;
    //Integer eventpicture;
    String eventdate;
    String eventtime;

    public Event(String eventitle, String eventcategory, String eventtime, String eventdate) {
        this.eventtitle = eventtitle;
        this.eventcategory = eventcategory;
        //this.eventpicture = eventpicture;
        this.eventdate = eventdate;
        this.eventtime = eventtime;
    }

    public String getEventtitle(){
        return eventtitle;
    }

    public void setEventtitle(String eventtitle){
        this.eventtitle = eventtitle;
    }

    public String getEventcategory(){
        return eventcategory;
    }

    public void setEventcategory(String eventcategory){
        this.eventcategory = eventcategory;
    }


//    public Integer getEventpicture(){
//        return eventpicture;
//    }
//
//    public void setEventpicture(Integer eventpicture){
//        this.eventpicture = eventpicture;
//    }
//
    public String getEventdate(){
        return eventdate;
    }

    public void setEventdate(String eventdate){
        this.eventdate = eventdate;
    }

    public String getEventtime(){
        return eventtime;
    }

    public void setEventtime(String eventtime){
        this.eventtime = eventtime;
    }
}

