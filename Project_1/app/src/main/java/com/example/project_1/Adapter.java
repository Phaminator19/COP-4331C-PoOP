package com.example.project_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context cl;
    private GroupList gr;
    private FirebaseUser mAuth;
    private DatabaseReference groupNameref, reference;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://groupmatchproject-default-rtdb.firebaseio.com/");
    private GroupList GroupList;
    private String GroupPath;

    public Adapter(Context c, GroupList GroupList) {
        this.cl = c;
        gr = GroupList;
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.gName.setText(gr.get(position).getName());
        holder.gInterest.setText(gr.get(position).getInterest());
        holder.gBios.setText(gr.get(position).getBios());
        String groupName = holder.gName.getText().toString();

        holder.join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = mAuth.getUid();
                System.out.println("User ID: " + userID);
                reference = database.getReference("Group");
                GroupList = new GroupList();
                GroupPath = "Group/" + groupName + "/Users/";
                System.out.println(groupName);
                groupNameref = database.getReference(GroupPath);
                addUserToTheCurrentGroup(userID, groupNameref);

                Intent intent = new Intent(cl, Chat.class);
                intent.putExtra("GroupName", groupName);
                cl.startActivity(intent);
            }

            private void addUserToTheCurrentGroup(String uid, DatabaseReference LastRef) {
                DatabaseReference usernameDatabase = database.getReference("User").child(uid).child("Username");
                usernameDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = snapshot.getValue(String.class);
                        System.out.println(name);
                        LastRef.child(uid).setValue(name);
                        Log.d("GroupID", "User is successfully added");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("GroupID", error.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return gr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView gName;
        public TextView gInterest;
        public TextView gBios;
        public Button join_button;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gName = itemView.findViewById(R.id.gName);
            gBios = itemView.findViewById(R.id.gBios2);
            gInterest = itemView.findViewById(R.id.gInterest2);
            join_button = itemView.findViewById(R.id.joinGroupBut);
        }
    }
}
