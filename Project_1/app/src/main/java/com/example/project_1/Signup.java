package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import java.io.BufferedWriter;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class Signup extends AppCompatActivity{
    Button registerButton;
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
                    password.setError("Password doesn't meet the require length of at least 10 characters");
                    password.requestFocus();
                    return;
                }

                User new_user = new User(name, email, pass);

                try (FileWriter textfile = new FileWriter("users.db", true); BufferedWriter user_db = new BufferedWriter(textfile);
                     PrintWriter out = new PrintWriter(user_db)) {
                    new_User_database.loadDataBase(out, new_user);

                } catch (IOException e) {
                    Toast.makeText(Signup.this, "failed to register a new user", Toast.LENGTH_LONG).show();
                }

                startActivity(new Intent(Signup.this, MainActivity.class));
            }
        });

    }

    }