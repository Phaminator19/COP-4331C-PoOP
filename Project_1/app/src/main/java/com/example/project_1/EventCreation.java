package com.example.project_1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EventCreation extends AppCompatActivity {
    Button createEventButton;
    Button cancelEventEditButton;
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
                Intent i4 = new Intent(EventCreation.this, EventMainPage.class);
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
}

