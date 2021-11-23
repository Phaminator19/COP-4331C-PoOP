package com.example.project_1;

import android.content.Intent;
import android.os.Bundle;
import static android.content.ContentValues.TAG;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

import androidx.annotation.NonNull;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {
    HashMap<String, String> userMap;
    Button login;
    private FirebaseAuth mAuth ;
    private EditText email_address;
    private EditText pass_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        email_address = (EditText) findViewById(R.id.loginEmailAddress);


        pass_word = (EditText) findViewById(R.id.loginpassword);

        String email = email_address.getText().toString().trim();
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
                    if (mAuth.getCurrentUser().isEmailVerified()) {
                            Log.d(TAG, "login is successful");
                            startActivity(new Intent(Login.this, UserProfile.class));
                            finish();
                    }else {
                        Log.e(TAG, "login isn't successful");
                        Toast.makeText(Login.this, "Failed to login. The email isn't verified!", Toast.LENGTH_LONG).show();
                }
                } else {
                    Toast.makeText(Login.this, "Failed to login!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
