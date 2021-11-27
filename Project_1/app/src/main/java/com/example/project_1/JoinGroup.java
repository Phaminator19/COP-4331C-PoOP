package com.example.project_1;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
//import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JoinGroup extends AppCompatActivity {
    private final static String TAG = "ViewGroupDATABASE";
    DatabaseReference databaseUsers;
    private ArrayList<String> GroupList;
    private RecyclerView recyclerView;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://groupmatchproject-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);

        recyclerView = findViewById(R.id.JoinGroupList);
        databaseUsers = database.getReference("Group");
        DatabaseReference groupReference = databaseUsers;


        groupReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                GroupList = new ArrayList<>();
//
//                for (DataSnapshot dsp: snapshot.getChildren()) {
//                    GroupList.add(String.valueOf(dsp.getValue()).toString());
//                }
//                for (int i = 0; i < GroupList.size(); i++) {
//                    System.out.println(GroupList.get(i));
//                }
            for(DataSnapshot ds: snapshot.getChildren()) {
                GroupInformation gInformation = new GroupInformation();
                gInformation.setGroupName(ds.getValue(GroupInformation.class).getGroupName());

                //display the group name
                Log.d(TAG, "Show groupName " + gInformation.getGroupName());

                GroupList = new ArrayList<>();
                GroupList.add(gInformation.getGroupName());
            }
                Adapter adapter = new Adapter(JoinGroup.this, GroupList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



//        setAdapter();
    }

//    private void setAdapter() {
//        for (int i = 0; i < GroupList.size(); i++) {
//                    System.out.println(GroupList.get(i));
//                }
//        Adapter adapter = new Adapter(JoinGroup.this, GroupList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);
//    }

}