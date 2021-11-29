package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupCreation extends AppCompatActivity {
    Button createGroupBut;
    Button cancelButton;
    private DatabaseReference reference;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://groupmatchproject-default-rtdb.firebaseio.com");

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

            createTheGroup(name, interest, bios);

            Intent i = new Intent(GroupCreation.this, Chat.class);
            i.putExtra("GroupName", name);
            startActivity(i);

            finish();
        }


    //save the group into the database
    public void createTheGroup(String name, String interest, String bios) {
        reference = firebaseDatabase.getReference().child("Group");
        DatabaseReference getGroup = firebaseDatabase.getReference("Group");
        String groupID = getGroup.push().getKey();
        Map<String, String> groupMap = new HashMap<>();
        groupMap.put("GroupName", name);
        groupMap.put("GroupInterest", interest);
        groupMap.put("GroupBios", bios);
        groupMap.put("GroupID", groupID);
        reference.child(name).setValue(groupMap);

        Group newGroup = new Group();
        newGroup.setBios(bios);
        newGroup.setName(name);
        newGroup.setInterest(interest);

    }
}
