package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;


import androidx.appcompat.app.AppCompatActivity;

public class Chat extends AppCompatActivity{

    private Toolbar top;
    private ImageButton sendMessage;
    private ScrollView scroll;
    private TextView displayMessages;
    private FirebaseAuth chatAuth;
    @Nullable private EditText userMessage;
    @Nullable private DatabaseReference userRef, groupNameRef, groupChatKeyRef;
    @Nullable private String currGroupName, currUserId, currUserName, currDate, currTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupchat);

        //currGroupName = getIntent().getExtras().get("Group Name").toString();
        chatAuth = FirebaseAuth.getInstance();
        currUserId = chatAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("User");
        groupNameRef = FirebaseDatabase.getInstance().getReference().child("Group");
        String groupID = groupNameRef.getKey();

        Toast.makeText(Chat.this,"",Toast.LENGTH_SHORT).show();

        initializeActivity();
        getUser();

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save the message to the database, revert editText, set scrollview to bottom - Estefania
                saveMessage();
                userMessage.setText("type message...");
                scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        groupNameRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){
                    showChat(dataSnapshot);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                return;
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                return;
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                return;
            }
        });
    }

    // initializes all the components on the groupchat activity page - Estefania
    private void initializeActivity(){
        top = (Toolbar)findViewById(R.id.group_chat_bar_layout);
        setSupportActionBar(top);
        getSupportActionBar().setTitle(currGroupName);
        sendMessage = (ImageButton)findViewById(R.id.sendmessagebutton);
        userMessage = (EditText)findViewById(R.id.inputgroupmessage);
        displayMessages = (TextView)findViewById(R.id.messagesDisplay);
        scroll = (ScrollView)findViewById(R.id.myscrollview);
    }

    // gets the current user's info from the database - Estefania
    private void getUser(){
        userRef.child(currUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    currUserName = dataSnapshot.child("name").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                return;
            }
        });
    }

    // saves message to the database - Estefania
    private void saveMessage(){
        // get message and message key - Estefania
        String message = userMessage.getText().toString();
        String messKey = groupNameRef.push().getKey();

        // if there is no message, do nothing - Estefania
        if(TextUtils.isEmpty(message)){
            Toast.makeText(this,"", Toast.LENGTH_SHORT).show();
        }
        // if there is a message, save it - Estefania
        else{
            // get the current date and time - Estefania
            Calendar dateCal = Calendar.getInstance();
            Calendar timeCal = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
            currDate = dateFormat.format(dateCal.getTime());
            currTime = timeFormat.format(timeCal.getTime());

            // hashmap for group message keys - Estefania
            HashMap<String, Object> groupMessKey = new HashMap<>();
            groupNameRef.updateChildren(groupMessKey);
            groupChatKeyRef = groupNameRef.child(messKey);

            // hashmap for message information - Estefania
            HashMap<String, Object> messageInfo = new HashMap<>();
            messageInfo.put("name", currUserName);
            messageInfo.put("message", message);
            messageInfo.put("date", currDate);
            messageInfo.put("time", currTime);

            // put it all together - Estefania
            groupChatKeyRef.updateChildren(messageInfo);
        }
    }

    // displays all the messages in the chat as they are sent/received - Estefania
    private void showChat(DataSnapshot dataSnapshot){
        Iterator iterator = dataSnapshot.getChildren().iterator();

        while(iterator.hasNext()){
            String chatDate = (String)((DataSnapshot)iterator.next()).getValue();
            String chatMessage = (String)((DataSnapshot)iterator.next()).getValue();
            String chatName = (String)((DataSnapshot)iterator.next()).getValue();

            displayMessages.append(chatName + ":\n" + chatMessage + "\n" + chatDate + "\n\n");
            scroll.fullScroll(ScrollView.FOCUS_DOWN);
        }
    }
}
