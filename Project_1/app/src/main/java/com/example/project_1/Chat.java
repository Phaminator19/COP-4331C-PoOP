package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
import com.google.firebase.database.core.Tag;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Chat extends AppCompatActivity{

    private Toolbar top;
    private ImageButton sendMessage;
    private ScrollView scroll;
    private TextView displayMessages;
    private FirebaseAuth chatAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://groupmatchproject-default-rtdb.firebaseio.com/");
    @Nullable private EditText userMessage;
    @Nullable private DatabaseReference userRef, groupNameRef, groupChatKeyRef;
    @Nullable private String currGroupName, currUserId, currUserName, currDate, currTime, groupID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupchat);
//
//        currGroupName = getIntent().getExtras().get("Group Name").toString();
//        chatAuth = FirebaseAuth.getInstance();
//        currUserId = chatAuth.getCurrentUser().getUid();
//        userRef = db.getReference().child("User").child(currUserId);
//        groupNameRef = db.getReference("Group");
//        groupID = groupNameRef.push().getKey();

        currGroupName = getIntent().getExtras().get("Group Name").toString();
        chatAuth = FirebaseAuth.getInstance();
        currUserId = chatAuth.getCurrentUser().getUid();
        userRef = db.getReference().child("User").child(currUserId);
        groupNameRef = db.getReference("Group").child(currGroupName);


        //Toast.makeText(Chat.this,"",Toast.LENGTH_SHORT).show();

        initializeActivity();
        getUser();

        System.out.println("No problem here");
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save the message to the database, revert editText, set scrollview to bottom - Estefania
                saveMessage();
                userMessage.setText("");
                scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        groupNameRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                if(dataSnapshot.exists()){
//                    showChat(dataSnapshot);
//                    System.out.println("No problem here");
//                }
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                return;
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//                return;
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                return;
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                return;
//            }
//        });
//    }

    // initializes all the components on the groupchat activity page - Estefania
    private void initializeActivity(){
        top = findViewById(R.id.group_chat_bar_layout);
        setSupportActionBar(top);
        getSupportActionBar().setTitle(currGroupName);
        sendMessage = findViewById(R.id.sendmessagebutton);
        userMessage = findViewById(R.id.inputgroupmessage);
        displayMessages = findViewById(R.id.messagesDisplay);
        scroll = findViewById(R.id.myscrollview);
    }

    // gets the current user's info from the database - Estefania
    private void getUser(){
        userRef.child(currUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    currUserName = dataSnapshot.child("Username").getValue().toString();
                    System.out.println("No problem with getting the user here");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("User error", databaseError.getMessage());
            }
        });
    }

    // saves message to the database - Estefania
    private void saveMessage(){
        // get message and message key - Estefania
        String message = userMessage.getText().toString().trim();

        // if there is no message, do nothing - Estefania
        if(TextUtils.isEmpty(message)){
            Toast.makeText(this,"", Toast.LENGTH_SHORT).show();
        }
        // if there is a message, save it - Estefania
        else{
            System.out.println("Did it get here?");
            // get the current date and time - Estefania
            Calendar dateCal = Calendar.getInstance();
            Calendar timeCal = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
            currDate = dateFormat.format(dateCal.getTime());
            currTime = timeFormat.format(timeCal.getTime());

            // hashmap for group message keys - Estefania
//            HashMap<String, Object> groupMessKey = new HashMap<>();
//            groupNameRef.updateChildren(groupMessKey);
//            groupChatKeyRef = groupNameRef.child(messKey);

            // get the values inside the current group reference - Quang
            groupNameRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        System.out.println("and does it get here?");
                        groupID = snapshot.child("Group ID").getValue(String.class);

                        //comment the line below if it doesn't work.
                        //groupChatKeyRef = snapshot.child(groupID).getRef();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("currentGroupID", error.getMessage());
                }
            });


            //Alternatively to get the reference I will use this? - Quang
            String messKey = groupID;
            HashMap<String, Object> groupMessKey = new HashMap<>();
            groupNameRef.updateChildren(groupMessKey);
            groupChatKeyRef = groupNameRef.child(messKey);

            //DatabaseReference groupChatRef = groupChatKeyRef;

            // hashmap for message information - Estefania
            HashMap<String, Object> messageInfo = new HashMap<>();
            messageInfo.put("name", currUserName);
            messageInfo.put("message", message);
            messageInfo.put("date", currDate);
            messageInfo.put("time", currTime);

            // put it all together - Estefania
            groupChatKeyRef.updateChildren(messageInfo);

            //put it all together - Quang
            //groupChatRef.child("Message_Database").setValue(messageInfo);
        }
    }

    // displays all the messages in the chat as they are sent/received - Estefania
//    private void showChat(DataSnapshot dataSnapshot){
//        Iterator iterator = dataSnapshot.getChildren().iterator();
//        while(iterator.hasNext()){
//            String chatDate = (String)((DataSnapshot)iterator.next()).getValue();
//            String chatMessage = (String)((DataSnapshot)iterator.next()).getValue();
//            String chatName = (String)((DataSnapshot)iterator.next()).getValue();
//
//            displayMessages.append(chatName + ":\n" + chatMessage + "\n" + chatDate + "\n\n");
//            scroll.fullScroll(ScrollView.FOCUS_DOWN);
//        }
//        System.out.println("Did the showChat() work?");
//    }
}
