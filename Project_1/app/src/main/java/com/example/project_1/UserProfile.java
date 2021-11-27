package com.example.project_1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    Button userSettingsButton;
//    Button groupSettingsButton;
    Button searchSettingsButton;
    Button groupCreationButton;
    DatabaseReference databaseUsers;
    private FirebaseAuth mAuth;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://groupmatchproject-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        databaseUsers = database.getReference("User");
        mAuth = FirebaseAuth.getInstance();

        userSettingsButton = findViewById(R.id.UserSettings);
        userSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSettings();
            }
        });
//        groupSettingsButton = findViewById(R.id.GroupSettings);
//        groupSettingsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GroupSettings();
//            }
//        });

        searchSettingsButton= findViewById(R.id.JoinGroup);
        searchSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Search();
                GroupChat();
            }
        });

        groupCreationButton = findViewById(R.id.GroupCreation);
        groupCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupCreation();
            }
        });

        String id = mAuth.getCurrentUser().getUid();
//        System.out.println("Is this ID work? " + id);
        DatabaseReference username = databaseUsers.child(id).child("Username");
        username.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String username = snapshot.getValue().toString();
                    TextView theUserName = (TextView) findViewById(R.id.UserName);
                    theUserName.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("UserNameID", error.getMessage());
            }
        });
    }

    public void UserSettings () {
        Intent i = new Intent(this, UserSettings_2.class);
        startActivity(i);
    }
//
//    public void GroupSettings() {
//        Intent i1 = new Intent(this, GroupSettings.class);
//        startActivity(i1);
//    }

    public void GroupChat() {
        Intent i2 = new Intent(this, JoinGroup.class);
        startActivity(i2);
    }


    public void GroupCreation() {
        Intent i3 = new Intent(this, GroupCreation.class);
        startActivity(i3);
    }
}