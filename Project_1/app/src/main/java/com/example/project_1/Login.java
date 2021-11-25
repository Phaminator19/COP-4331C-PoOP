package com.example.project_1;
<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;

=======
import static android.content.ContentValues.TAG;

import android.os.Bundle;

import android.util.Log;
>>>>>>> BACKUP_BRANCH
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
=======
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
>>>>>>> BACKUP_BRANCH

public class Login extends AppCompatActivity {
    HashMap<String, String> userMap;
    Button login;
<<<<<<< HEAD
=======
    private FirebaseAuth mAuth ;
    private EditText email_address;
    private EditText pass_word;
>>>>>>> BACKUP_BRANCH

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
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
=======
        setContentView(R.layout.activity_login_2);

        mAuth = FirebaseAuth.getInstance();

        login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlingLogin();
            }
        });

        }


    public void handlingLogin() {
        //fix this
        email_address = (EditText) findViewById(R.id.loginEmail);


        pass_word = (EditText) findViewById(R.id.loginpassword);

        String email = this.email_address.getText().toString().trim();
        String pass = pass_word.getText().toString().trim();

        if (pass.isEmpty()) {
            pass_word.setError("Password required");
            pass_word.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            email_address.setError("Email is required");
            email_address.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
//                    startActivity(new Intent(Login.this, place_holder.class));
                    if(mAuth.getCurrentUser() != null) {
                        Log.d(TAG, "login is successful");
                        Toast.makeText(Login.this, "Successful Login", Toast.LENGTH_LONG).show();
                        //finish();
                    }
                }
                else {
                    Log.e(TAG, "login isn't successful");
                    Toast.makeText(Login.this, "Failed to login. Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

>>>>>>> BACKUP_BRANCH
