package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import android.widget.Button;
import android.content.Intent;


public class Signup extends AppCompatActivity{
    Button registerButton;
    User_Database new_user_database;
    HashMap<String, String> userMap;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        // this populates the drop down menu with the three user types - Estefania
        Spinner userType = (Spinner) findViewById(R.id.userType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(adapter);

    }

    //this takes in the user name, email, and password - Quang
    public void read_users_info (View v) throws IOException {
        User_Database new_User_database = new User_Database();

        registerButton = (Button)findViewById(R.id.signup);
        //click the register button - Quang
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

}