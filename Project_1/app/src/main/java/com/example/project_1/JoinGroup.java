package com.example.project_1;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
//import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JoinGroup extends AppCompatActivity {
    Button joinButton;
    private final static String TAG = "ViewGroupDATABASE";
    DatabaseReference databaseUsers;
    private GroupList GroupList;
    private RecyclerView recyclerView;
    private String GroupPath;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://groupmatchproject-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);



        databaseUsers = database.getReference("Group");
        DatabaseReference groupReference = databaseUsers;
        recyclerView = findViewById(R.id.JoinGroupList);
        GroupList = new GroupList();

        groupReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot ds: snapshot.getChildren()) {
                Group gInformation = new Group();
                gInformation.setName(ds.child("GroupName").getValue(String.class));
                gInformation.setBios(ds.child("GroupBios").getValue(String.class));
                gInformation.setInterest(ds.child("GroupInterest").getValue(String.class));
                //display the group name
                Log.d(TAG, "Show Group Name " + gInformation.getName());
                Log.d(TAG, "Show Group Interest " + gInformation.getInterest());
                Log.d(TAG, "Show Group Bios " + gInformation.getBios());
                GroupList.addGroup(gInformation);
            }
                setAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void setAdapter() {
        Adapter adapter = new Adapter(JoinGroup.this, GroupList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

}