package com.example.project_1;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }
    public void AccountSettings (View view) {
        String userName = "Courtney Than";
        String userAge = ((Button)view).getText().toString();
        String userBirthday = ((Button)view).getText().toString();
        String userCity = ((Button)view).getText().toString();
        String userJob = ((Button)view).getText().toString();

        //((TextView)findViewById(R.id.UserName)).setText(userName);
        TextView t = findViewById(R.id.UserName);
        t.setText(userName);

        Intent i = new Intent(this, AccountSettings.class);

        i.putExtra("User Age", userAge);
        i.putExtra("User Birthday", userBirthday);
        i.putExtra("City", userCity);
        i.putExtra("Job", userJob);

        startActivity(i);
    }

    public void handlingSetStatus(View v) {
        Intent i1 = new Intent(this, SetStatus.class);
        startActivity(i1);
    }
}