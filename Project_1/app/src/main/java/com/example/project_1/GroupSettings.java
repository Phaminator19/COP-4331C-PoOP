package com.example.project_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GroupSettings extends AppCompatActivity {
    Button GroupSave;
    Button GroupEditBut;
    Group g = new Group();
    private DatabaseReference reference;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://groupmatchproject-default-rtdb.firebaseio.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupchat);
        GroupSave = (Button) findViewById(R.id.editSaveGroup);
        GroupSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlingGroupSettings();
                ((EditText)findViewById(R.id.editGroupName)).setEnabled(false);
                ((EditText)findViewById(R.id.editInterests)).setEnabled(false);
                ((EditText)findViewById(R.id.editBio)).setEnabled(false);
            }
        });

        GroupEditBut = (Button)findViewById(R.id.editIcon);
        GroupEditBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                re_editGroup();
            }
        });

    }

    public void handlingGroupSettings() {
        EditText group_name = findViewById(R.id.editGroupName);
        EditText group_interest = findViewById(R.id.editInterests);
        EditText group_bios = findViewById(R.id.editBio);

        String name = group_name.getText().toString().trim();
        String interest = group_interest.getText().toString().trim();
        String bios = group_bios.getText().toString().trim();

        reference = firebaseDatabase.getReference();
        g.editTheGroupName(reference, );

    }

    public void re_editGroup() {
        ((EditText)findViewById(R.id.editGroupName)).setEnabled(true);
        ((EditText)findViewById(R.id.editInterests)).setEnabled(true);
        ((EditText)findViewById(R.id.editBio)).setEnabled(true);
    }

}
