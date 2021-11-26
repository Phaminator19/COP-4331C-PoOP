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

import java.util.HashMap;
import java.util.Map;

public class Group {
    HashMap<String, String> groupMap;
    private DatabaseReference reference;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://groupmatchproject-default-rtdb.firebaseio.com");

    public Group() {
        groupMap = new HashMap<>();
    }
    //This will be called by the Group
    public void editTheGroupName(DatabaseReference ref, String id, String new_name, String new_interest, String new_bios) {
       Query groupQuery = ref.orderByChild("Group ID").equalTo(id);
       ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("Group Name", new_name);
                    map.put("Group Interest", new_interest);
                    map.put("Group Bios", new_bios);
                    ds.getRef().updateChildren(map);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, error.getMessage());
            }
        };
        groupQuery.addListenerForSingleValueEvent(valueEventListener);
    }

    public void createTheGroup(String name, String interest, String bios) {
        reference = firebaseDatabase.getReference().child("Group");
        DatabaseReference getGroup = firebaseDatabase.getReference("Group");
        String groupID = getGroup.push().getKey();
        Map<String, String> groupMap = new HashMap<>();
        groupMap.put("Group Name", name);
        groupMap.put("Group Interest", interest);
        groupMap.put("Group Bios", bios);
        groupMap.put("Group ID", groupID);
        reference.child(name).setValue(groupMap);

    }
}
