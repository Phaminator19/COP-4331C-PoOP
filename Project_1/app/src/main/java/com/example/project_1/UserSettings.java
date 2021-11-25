package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        Intent i = getIntent();
        String userName = i.getStringExtra("User Name");
        String pronouns = i.getStringExtra("Pronouns");
        String userBirthday = i.getStringExtra("User Birthday");
        String editBio = i.getStringExtra("Edit Bio");
        String editInterest = i.getStringExtra("Edit Interest");

        ((TextView)findViewById(R.id.editUsername)).setEnabled(false);
        ((TextView)findViewById(R.id.editPronouns)).setEnabled(false);
        ((TextView)findViewById(R.id.editBirthday)).setEnabled(false);
        ((TextView)findViewById(R.id.editGroupBio)).setEnabled(false);
        ((TextView)findViewById(R.id.editInterests)).setEnabled(false);

        Button edit = findViewById(R.id.editUserIcon);
        final boolean[] edit_clicked = {false};
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_clicked[0] = true;
                if (edit_clicked[0]) {
                    ((TextView)findViewById(R.id.editUsername)).setEnabled(true);
                    ((TextView)findViewById(R.id.editPronouns)).setEnabled(true);
                    ((TextView)findViewById(R.id.editBirthday)).setEnabled(true);
                    ((TextView)findViewById(R.id.editGroupBio)).setEnabled(true);
                    ((TextView)findViewById(R.id.editInterests)).setEnabled(true);
                }
            }
        });

        Button save = findViewById(R.id.editSaveUser);
        final boolean[] clicked = {
                false
        };
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked[0] = true;
                if (clicked[0]) {
                    ((TextView)findViewById(R.id.editUsername)).setEnabled(false);
                    ((TextView)findViewById(R.id.editPronouns)).setEnabled(false);
                    ((TextView)findViewById(R.id.editBirthday)).setEnabled(false);
                    ((TextView)findViewById(R.id.editGroupBio)).setEnabled(false);
                    ((TextView)findViewById(R.id.editInterests)).setEnabled(false);
                }
            }
        });

    }

}