package com.example.project_1;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {
    private static final String TAG = "ViewGroupDatabase";
    private String name;
    private String interest;
    private String bios;


    public Group() {
    }

    public String getName() {
        return name;
    }

    public String getInterest() {
        return interest;
    }

    public String getBios() {
        return bios;
    }

}
