package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GroupCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creation);

        Intent i3 = getIntent();
        String groupName = i3.getStringExtra("Group Name");
        String interest = i3.getStringExtra("Interest");
        String groupBio = i3.getStringExtra("Group Bio");

        ((EditText)findViewById(R.id.editGroupName)).setEnabled(false);
        ((TextView)findViewById(R.id.editGroupInterest)).setEnabled(false);
        ((TextView)findViewById(R.id.editGroupBio)).setEnabled(false);

        Button edit = findViewById(R.id.editGroupIcon);
        final boolean[] edit_clicked = {false};
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_clicked[0] = true;
                if (edit_clicked[0]) {
                    ((TextView)findViewById(R.id.editGroupName)).setEnabled(true);
                    ((TextView)findViewById(R.id.editGroupInterest)).setEnabled(true);
                    ((TextView)findViewById(R.id.editGroupBio)).setEnabled(true);
                }
            }
        });

        Button save = findViewById(R.id.editSaveGroup);
        final boolean[] clicked = {false};
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked[0] = true;
                if (clicked[0]) {
                    ((TextView)findViewById(R.id.editGroupName)).setEnabled(false);
                    ((TextView)findViewById(R.id.editGroupInterest)).setEnabled(false);
                    ((TextView)findViewById(R.id.editGroupBio)).setEnabled(false);
                }
            }
        });
    }
}