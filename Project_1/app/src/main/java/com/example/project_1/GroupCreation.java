package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GroupCreation extends AppCompatActivity {
    Button createGroupBut;
    Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creation);

        createGroupBut = findViewById(R.id.createGroup);
        createGroupBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlingGroupCreation();
            }
        });

        cancelButton = findViewById(R.id.cancelTask);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GroupCreation.this, UserProfile.class));
                finish();
            }
        });


    }

        public void handlingGroupCreation() {
            Group g = new Group();
            EditText groupName = findViewById(R.id.createGroupName);
            EditText groupInterest = findViewById(R.id.createGroupInterest);
            EditText groupBios = findViewById(R.id.createGroupBios);

            String name = groupName.getText().toString().trim();
            String interest = groupInterest.getText().toString().trim();
            String bios = groupBios.getText().toString().trim();

            if(name.isEmpty()) {
                groupName.setError("Group Name is required");
                groupName.requestFocus();
                return;
            }

            if(interest.isEmpty()){
                groupInterest.setError("Interest is required");
                groupInterest.requestFocus();
                return;
            }

            if(bios.isEmpty()) {
                groupBios.setError("Group bios is empty. It's recommended to add something here");
                groupBios.requestFocus();
                return;
            }

            g.createTheGroup(name, interest, bios);
            Intent i = new Intent(GroupCreation.this, Chat.class);

            i.putExtra("Group Name", name);
            startActivity(i);

            finish();
        }
    }
