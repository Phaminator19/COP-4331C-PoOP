package com.example.project_1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EventMainPage extends AppCompatActivity {
    private String AdditionalGroupName;
    Button JoinEvent;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_event_mainpage);

            getWindow().setStatusBarColor(Color.GRAY);

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
            JoinEvent = findViewById(R.id.join_event_button);
            JoinEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(EventMainPage.this, Chat.class);
                    newIntent.putExtra("GroupName", AdditionalGroupName);
                    startActivity(newIntent);
                }
            });



        }
}
