package com.example.project_1;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfile extends AppCompatActivity {
    Button userSettingsButton;
    Button groupSettingsButton;
    Button searchSettingsButton;
    Button groupCreationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userSettingsButton = findViewById(R.id.UserSettings);
        userSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSettings();
            }
        });
        groupSettingsButton = findViewById(R.id.GroupSettings);
        groupSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupSettings();
            }
        });

        searchSettingsButton= findViewById(R.id.Searching);
        searchSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search();
            }
        });

        groupCreationButton = findViewById(R.id.GroupCreation);
        groupCreationButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               GroupCreation();
           }
       });

    }


    public void UserSettings () {
        Intent i = new Intent(this, UserSettings.class);
        startActivity(i);
    }
//
//        String userName = ((Button)view).getText().toString();
//        String pronouns = ((Button)view).getText().toString();
//        String userBirthday = ((Button)view).getText().toString();
//        String editBio = ((Button)view).getText().toString();
//        String editInterest = ((Button)view).getText().toString();
//
//
//
//        i.putExtra("User Name", userName);
//        i.putExtra("Pronouns", pronouns);
//        i.putExtra("User Birthday", userBirthday);
//        i.putExtra("Edit Bio", editBio);
//        i.putExtra("Edit Interest", editInterest);


    public void GroupSettings() {
        Intent i1 = new Intent(this, GroupSettings.class);
        startActivity(i1);
    }

    public void Search() {
//        Intent i2 = new Intent(this, Search.class);
//        startActivity(i2);
    }

    public void GroupCreation() {
        Intent i3 = new Intent(this, GroupCreation.class);
//        String groupName = ((Button)vie).getText().toString();
//        String interest = ((Button)vie).getText().toString();
//        String groupBio = ((Button)vie).getText().toString();
//
//        i3.putExtra("Group Name", groupName);
//        i3.putExtra("Interest", interest);
//        i3.putExtra("Group Bio", groupBio);
        startActivity(i3);
    }
}