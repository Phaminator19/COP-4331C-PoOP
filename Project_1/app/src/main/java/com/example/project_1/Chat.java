package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.text.SimpleDateFormat;

import android.widget.EditText;
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

public class Chat extends AppCompatActivity
{

    EditText userMessage;
    TextView groupNameView;
    FirebaseUser user;
    String groupID, userName, groupName, GROUPPATH;
    private MessageAdapter messageAdapter;
    private ListView messageView;
    private String userId;
    private DatabaseReference groupNameRef;

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
                groupName = getIntent().getExtras().getString("Group Name");
            }
        }
        else
        {
            groupName = (String) savedInstanceState.getSerializable("Group Name");
        }


        // put message in database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://groupmatchproject-default-rtdb.firebaseio.com/");

        groupNameRef = database.getReference("Group").child(groupName);
        groupID = groupNameRef.push().getKey();
        GROUPPATH = "Group/" + groupID + "/Messages/";

        DatabaseReference myRef = database.getReference(GROUPPATH);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
        userName = user.getDisplayName();
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

            Date date = new Date(); // This object contains the current date value
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Message chatMessage = new Message(msg, user.getUid(), user.getDisplayName(), formatter.format(date));
            myRef.push().setValue(chatMessage);

        }
    }
}
