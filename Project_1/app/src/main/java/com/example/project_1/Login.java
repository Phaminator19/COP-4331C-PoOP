package com.example.project_1;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Login extends AppCompatActivity {
    HashMap<String, String> userMap;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void handlingLogin(View v){
        try {

            HashMap<String, String> tempMap;
            tempMap = loadFile("user.db");
            login = (Button) findViewById(R.id.loginButton);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText user_name = (EditText)findViewById(R.id.loginusername);
                    EditText pass_word = (EditText)findViewById(R.id.loginpassword);

                    String name = user_name.getText().toString().trim();
                    String pass = pass_word.getText().toString().trim();

                    if(pass.isEmpty()) {
                        pass_word.setError("Password required");
                        pass_word.requestFocus();
                        return;
                    }

                    if(name.isEmpty()) {
                        user_name.setError("Username required");
                        user_name.requestFocus();
                        return;
                    }

                    if(tempMap.containsKey(name) && tempMap.containsValue(pass)) {
                        //startActivity(new Intent(Login.this, front_class.class));
                        finish();
                    }
                    else {
                        Toast.makeText(Login.this, "Failed to login. Incorrect username or password", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (FileNotFoundException error) {
            Toast.makeText(Login.this, "An error has occurred. User Database does not found.", Toast.LENGTH_SHORT).show();
        }
    }

    public HashMap<String, String> loadFile(String filename) throws FileNotFoundException {
        try {
            File myObj = new File(filename);
            Scanner reader = new Scanner(myObj);

            while(reader.hasNextLine()) {
                String user_name = reader.nextLine();
                String password = reader.nextLine();
                //String Email = reader.nextLine();

                userMap.put(user_name, password);
            }
        } catch (FileNotFoundException error) {
            throw new FileNotFoundException("User database file does not found. An error has occurred.");
        }
        return userMap;
    }

}
