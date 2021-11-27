package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.text.SimpleDateFormat;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Chat extends AppCompatActivity
{

    EditText userMessage;
    TextView groupNameView;
    FirebaseUser user;
    String groupID, groupName, GROUPPATH;
    private MessageAdapter messageAdapter;
    private ListView messageView;
    private String userId;
    private DatabaseReference groupNameRef;

    ImageButton iButton;
    ImageButton returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupchat);

        initiateActivity();

        if (savedInstanceState == null)
        {
            if (getIntent().getExtras()== null)
            {
                groupName = "";
            }
            else
            {
                groupName = getIntent().getExtras().getString("GroupName");
            }
        }
        else
        {
            groupName = (String) savedInstanceState.getSerializable("GroupName");
        }


        // put message in database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://groupmatchproject-default-rtdb.firebaseio.com/");

        groupNameRef = database.getReference("Group").child(groupName);
        groupID = groupNameRef.push().getKey();
        //- Estefania
        //GROUPPATH = "Group/" + groupID + "/Messages/";

        //-Quang
        GROUPPATH = "Group/" + groupName + "/Messages/";

        DatabaseReference myRef = database.getReference(GROUPPATH);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
//        userName = user.getDisplayName(); - Quang
        groupNameView.setText(groupName);

        final List<Message> msgs = new ArrayList<Message>();

        myRef.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {
                Message message = dataSnapshot.getValue(Message.class);
                boolean self = true;
                msgs.add(message);
                messageAdapter.add(message);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot)
            {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
            }

        });

        iButton = findViewById(R.id.infoButton);
        iButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Chat.this, GroupMenu.class);
                i.putExtra("GroupName",groupName);
                startActivity(i);
            }
        });

        returnButton = findViewById(R.id.backButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Chat.this, UserProfile.class));
                finish();
            }
        });
    }
    // initiate components from groupchat activity
    private void initiateActivity()
    {
        userMessage = findViewById(R.id.userMessage);
        groupNameView = findViewById(R.id.groupName);
        messageAdapter = new MessageAdapter(this);
        messageView = findViewById(R.id.messages_view);
        messageView.setAdapter(messageAdapter);
    }

    // save the message to the database
    public void sendMessage(View view)
    {
        String msg = userMessage.getText().toString();

        if (msg.length() > 0)
        {
            userMessage.getText().clear();

            FirebaseDatabase database = FirebaseDatabase.getInstance("https://groupmatchproject-default-rtdb.firebaseio.com/");
            DatabaseReference myRef = database.getReference(GROUPPATH);
            //get the username - Quang
            DatabaseReference databaseUser = database.getReference("User");

            Date date = new Date(); // This object contains the current date value
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            //get the username - Quang
            DatabaseReference username = databaseUser.child(userId).child("Username");
            username.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String username = snapshot.getValue().toString();
                    Message chatMessage = new Message(msg, userId, username, formatter.format(date));
                    myRef.push().setValue(chatMessage);
                    Log.d("MessageID", "New message is sent");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("UserNameID", error.getMessage());
                }
            });
        }
    }
}
