package com.example.project_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class GroupSettings extends AppCompatActivity {
    private final static String TAG = "ViewGroupDatabase";
    Button GroupSave;
    Button GroupEditCancel;
    Group g = new Group();
    private String GroupName;
    private DatabaseReference reference, getGroupRef;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://groupmatchproject-default-rtdb.firebaseio.com");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_settings);

        if(savedInstanceState == null) {
            if (getIntent().getExtras()== null)
            {
                GroupName = "";
            }
            else
            {
                GroupName = getIntent().getExtras().getString("GroupName");
            }
        }
        else
        {
            GroupName = (String)savedInstanceState.getSerializable("GroupName");
        }


        reference = firebaseDatabase.getReference().child("Group").child(GroupName);

        GroupSave = findViewById(R.id.saveChanges);
        GroupSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlingGroupSettings();
//                ((EditText)findViewById(R.id.editGroupName)).setEnabled(false);
//                ((EditText)findViewById(R.id.editInterests)).setEnabled(false);
//                ((EditText)findViewById(R.id.editBio)).setEnabled(false);
            }
        });

        GroupEditCancel = findViewById(R.id.cancelButton2);
        GroupEditCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroupSettings.this, Chat.class);
                i.putExtra("GroupName", GroupName);
                startActivity(i);
            }
        });


    }

    public void handlingGroupSettings() {
        EditText group_name = findViewById(R.id.editGroupName);
        EditText group_interest = findViewById(R.id.editGroupInterest);
        EditText group_bios = findViewById(R.id.editBio);

        String name = group_name.getText().toString().trim();
        String interest = group_interest.getText().toString().trim();
        String bios = group_bios.getText().toString().trim();

//        String groupID = getGroupID.push().getKey();

        if(name.isEmpty()) {
            group_name.setError("Group name is required");
            group_name.requestFocus();
            return;
        }
        if(interest.isEmpty()) {
            group_interest.setError("Group interest is required");
            group_interest.requestFocus();
            return;
        }
        if(bios.isEmpty()) {
            group_bios.setError("Bios can't be empty");
            group_bios.requestFocus();
            return;
        }


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                editTheGroupName(reference, name, interest, bios);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Intent i = new Intent(GroupSettings.this, Chat.class);
        i.putExtra("GroupName", name);
        startActivity(i);
        finish();
    }

    //This will be called by the GroupSetting class - Quang
    public void editTheGroupName(DatabaseReference ref, String new_name, String new_interest, String new_bios) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    HashMap<String, Object> groupMap = new HashMap<>();
                    groupMap.put("GroupName", new_name);
                    groupMap.put("GroupInterest", new_interest);
                    groupMap.put("GroupBios", new_bios);

                    ds.getRef().updateChildren(groupMap);
                    Log.d(TAG, "Group edit successful!");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, error.getMessage()); //Don't ignore this
            }
        });

    }
}

