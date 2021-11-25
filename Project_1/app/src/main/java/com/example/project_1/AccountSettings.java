package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AccountSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        Intent i = getIntent();
        String UserAge = i.getStringExtra("User Age");
        String UserBirthday = i.getStringExtra("User Birthday");
        String UserCity = i.getStringExtra("User City");
        String UserJob = i.getStringExtra("User Job");
//
//        ((TextView)findViewById(R.id.Age)).setText(UserAge);
//        Toast.makeText(this, "user age saved: " + UserAge, Toast.LENGTH_LONG).show();
//        ((TextView)findViewById(R.id.Birthday)).setText(UserBirthday);
//        Toast.makeText(this, "user birthday saved: " + UserBirthday, Toast.LENGTH_LONG).show();
//        ((TextView)findViewById(R.id.City)).setText(UserCity);
//        Toast.makeText(this, "user city saved: " + UserCity, Toast.LENGTH_LONG).show();
//        ((TextView)findViewById(R.id.Job)).setText(UserJob);
//        Toast.makeText(this, "user job saved: " + UserJob, Toast.LENGTH_LONG).show();
    }
}