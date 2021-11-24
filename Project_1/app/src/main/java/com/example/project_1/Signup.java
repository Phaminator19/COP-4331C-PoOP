package com.example.project_1;

<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
=======
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
>>>>>>> BACKUP_BRANCH
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

<<<<<<< HEAD
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
=======
import java.io.File;
import java.io.IOException;
>>>>>>> BACKUP_BRANCH
import java.util.HashMap;

import android.widget.Button;
import android.content.Intent;
<<<<<<< HEAD
=======
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
>>>>>>> BACKUP_BRANCH


public class Signup extends AppCompatActivity{
    Button registerButton;
<<<<<<< HEAD
    User_Database new_user_database;
    HashMap<String, String> userMap;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


=======
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://groupmatchproject-default-rtdb.firebaseio.com/");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();

        registerButton = (Button)findViewById(R.id.signup);
        //click the register button - Quang
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                read_users_info();
                Toast.makeText(Signup.this, "A user is created", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Signup.this, MainActivity.class));
            }
        });
>>>>>>> BACKUP_BRANCH

        // this populates the drop down menu with the three user types - Estefania
        Spinner userType = (Spinner) findViewById(R.id.userType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(adapter);

    }

    //this takes in the user name, email, and password - Quang
<<<<<<< HEAD
    public void read_users_info (View v) throws IOException {
        User_Database new_User_database = new User_Database();

        registerButton = (Button)findViewById(R.id.signup);
        //click the register button - Quang
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
=======
    public void read_users_info (){

>>>>>>> BACKUP_BRANCH

                int password_length = 10;

                EditText user_name = findViewById(R.id.signupUsername);
                EditText email_address = findViewById(R.id.signupEmail);
                EditText password = findViewById(R.id.signupPassword);

                String name = user_name.getText().toString().trim();
                String email = email_address.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(pass.isEmpty()) {
                    password.setError("Password required");
                    password.requestFocus();
                    return;
                }

                if(name.isEmpty()) {
                    user_name.setError("Username required");
                    user_name.requestFocus();
                    return;
                }

                if(email.isEmpty()) {
                    email_address.setError("Email address is required");
                    email_address.requestFocus();
                    return;
                }

                if(pass.length() < password_length) {
                    password.setError("Password doesn't meet the required length of at least 10 characters");
                    password.requestFocus();
                    return;
                }

<<<<<<< HEAD
                User new_user = new User(name, email, pass);
                CreateAFile(new_user);
                startActivity(new Intent(Signup.this, MainActivity.class));
            }
        });
    }
    public void CreateAFile(User user) {

        //Toast.makeText(Signup.this, file.getAbsolutePath() + " is created",Toast.LENGTH_SHORT).show();
        try {
            String filename = "user.db";
            new_user_database.loadDataBase(user, filename);
//            File file = new File(filename);
//            if(file.createNewFile()) {
//                System.out.println("File is created at " + file.getAbsolutePath());
//            }
//            else {
//                System.out.println("...");
//            }
//            if(file.createNewFile()) {
//                System.out.println("File is created at " + file.getAbsolutePath());
//            }
//            else {
//                System.out.println("...");
//            }
//            FileWriter fr = new FileWriter(file, true);
//            BufferedWriter br = new BufferedWriter(fr);
//            PrintWriter pr = new PrintWriter(br);
//
//            pr.println(user.getUserName());
//            pr.println(user.getPassword());
//            pr.close();
//            br.close();
//            fr.close();

        } catch (IOException error) {
            Toast.makeText(Signup.this, "Failed to make a user. An error occurred.", Toast.LENGTH_LONG).show();
        }
    }
=======
                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            HashMap<String, String> userMap = new HashMap<>();
                            userMap.put("Username", name);
                            userMap.put("Email", email);
                            userMap.put("Password", pass);
                            reference = firebaseDatabase.getReference();
                            reference.child("User").child(mAuth.getUid()).setValue(userMap);

                        }
                        else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Signup.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }

//    public void CreateAFile(User user) {
//        boolean test = false;
//        //Toast.makeText(Signup.this, file.getAbsolutePath() + " is created",Toast.LENGTH_SHORT).show();
//        try {
//            String filename = "user.txt";
//            new_user_database.writeDataBase(user, filename,Signup.this);
//
////            File file = new File(filename);
////            if(file.createNewFile()) {
////                System.out.println("File is created at " + file.getAbsolutePath());
////            }
////            else {
////                System.out.println("...");
////            }
////            if(file.createNewFile()) {
////                System.out.println("File is created at " + file.getAbsolutePath());
////            }
////            else {
////                System.out.println("...");
////            }
////            FileWriter fr = new FileWriter(file, true);
////            BufferedWriter br = new BufferedWriter(fr);
////            PrintWriter pr = new PrintWriter(br);
////
////            pr.println(user.getUserName());
////            pr.println(user.getPassword());
////            pr.close();
////            br.close();
////            fr.close();
//
//        } catch (IOException error) {
//            Toast.makeText(Signup.this, "Failed to make a user. An error occurred.", Toast.LENGTH_LONG).show();
//        }
//    }
>>>>>>> BACKUP_BRANCH

}