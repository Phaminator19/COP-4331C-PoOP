package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UserSettings_2 extends AppCompatActivity {
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://groupmatchproject-default-rtdb.firebaseio.com/");
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();
        DatabaseReference userRef = db.getReference("User").child(id);

        Button save = findViewById(R.id.editSaveUser);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    handlingTheUserEdit(userRef, id);
            }
        });

    }

    public void handlingTheUserEdit(DatabaseReference reference, String ID) {
        TextView username = findViewById(R.id.editUsername);
        TextView pronouns = findViewById(R.id.editPronouns);
        TextView Birthday = findViewById(R.id.editBirthday);
        TextView Interests = findViewById(R.id.editGroupInterest);

        String name = username.getText().toString();
        String PRONOUNS = pronouns.getText().toString();
        String bd = Birthday.getText().toString();
        String inte = Interests.getText().toString();

        if(name.isEmpty()) {
            username.setError("What is your new name?");
            username.requestFocus();
            return;
        }

        if(PRONOUNS.isEmpty()) {
            pronouns.setError("What is your pronouns?");
            pronouns.requestFocus();
            return;
        }

        if(bd.isEmpty()) {
            Birthday.setError("Please enter your birthday");
            Birthday.requestFocus();
            return;
        }

        if(inte.isEmpty()) {
            Interests.setError("Please enter your interests");
            Interests.requestFocus();
            return;
        }

        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("Username", name);
        userMap.put("Pronouns", PRONOUNS);
        userMap.put("Birthday", bd);
        userMap.put("Interest", inte);
        reference.push().setValue(userMap);

        startActivity(new Intent(this, UserProfile.class));
        finish();
    }


}