package com.example.project_1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EventCreation extends AppCompatActivity {
    Button createEventButton;
    Button cancelEventEditButton;
    private DatabaseReference reference;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://groupmatchproject-default-rtdb.firebaseio.com");
    private String AdditionalGroupName;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        if(savedInstanceState == null) {
            if (getIntent().getExtras()== null)
            {
                AdditionalGroupName = "";
            }
            else
            {
                AdditionalGroupName = getIntent().getExtras().getString("GroupName");
            }
        }
        else
        {
            AdditionalGroupName = (String)savedInstanceState.getSerializable("GroupName");
        }

        createEventButton = findViewById(R.id.createEvent);
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText eventName = findViewById(R.id.createEventName);
                EditText eventDate = findViewById(R.id.createEventDate);
                EditText eventTime = findViewById(R.id.createEventTime);
                EditText eventDescription = findViewById(R.id.createEventDescription);
                EditText eventCreator = findViewById(R.id.eventCreator);


                String eName = eventName.getText().toString();
                String eDate = eventDate.getText().toString();
                String eTime = eventTime.getText().toString();
                String eDescription = eventDescription.getText().toString();
                String eCreator = eventCreator.getText().toString();
                String eGName = AdditionalGroupName;

                if(eName.isEmpty()) {
                    eventName.setError("Event Name is required");
                    eventName.requestFocus();
                    return;
                }

                if (eDate.isEmpty()) {
                    eventDate.setError("Event Date is required");
                    eventDate.requestFocus();
                    return;
                }

                if (eTime.isEmpty()) {
                    eventTime.setError("Event Time is required");
                    eventDate.requestFocus();
                    return;
                }

                if (eDescription.isEmpty()) {
                    eventDescription.setError("Event Description is required");
                    eventDescription.requestFocus();
                }

                if (eCreator.isEmpty()) {
                    eventCreator.setError("Event Creator is required");
                    eventCreator.requestFocus();
                }

//                if (eGName.isEmpty()) {
//                    eventGName.setError("Event Group Name is required");
//                    eventGName.requestFocus();
//                }

                createTheEvent(eName, eDate, eTime, eDescription, eCreator, eGName);
                Toast.makeText(EventCreation.this, "New event is successfully made", Toast.LENGTH_SHORT).show();
                Intent i4 = new Intent(EventCreation.this, Chat.class);
                i4.putExtra("GroupName", AdditionalGroupName);
                startActivity(i4);
            }
        });

        cancelEventEditButton = findViewById(R.id.cancelEventCreation);
        cancelEventEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EventCreation.this, GroupMenu.class);
                i.putExtra("GroupName", AdditionalGroupName);
                startActivity(i);
            }
        });
    }

    public void createTheEvent(String eName, String eDate, String eTime, String eDescription, String eCreator, String eGName) {
        reference = firebaseDatabase.getReference("Group").child(eGName);
        Map<String, String> eventMap = new HashMap<String, String>();

        eventMap.put("EventName", eName);
        eventMap.put("EventDate", eDate);
        eventMap.put("EventTime", eTime);
        eventMap.put("EventDescription", eDescription);
        eventMap.put("EventCreator", eCreator);

        reference.child("Event: " + eName).setValue(eventMap);

        Event newEvent = new Event();
        newEvent.setEventName(eName);
        newEvent.setEventDate(eDate);
        newEvent.setEventTime(eTime);
        newEvent.setEventDescription(eDescription);
        newEvent.setEventCreator(eCreator);
        newEvent.setEventGName(eGName);
    }
}

