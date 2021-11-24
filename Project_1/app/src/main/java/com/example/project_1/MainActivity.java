package com.example.project_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
<<<<<<< HEAD


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
=======
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
>>>>>>> BACKUP_BRANCH
    Button lbtn;
    Button sbtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD

=======
>>>>>>> BACKUP_BRANCH
    }

    public void launchLoginPage(View v) {
        //launch the login page activity
        lbtn = (Button) findViewById(R.id.loginStart);
        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
    }

    public void launchRegisterPage(View v) {
        sbtn = (Button) findViewById(R.id.signupStart);
        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Signup.class));
            }
        });
    }

<<<<<<< HEAD
    @Override
    public void onClick(View v) {

    }
=======
>>>>>>> BACKUP_BRANCH
}


    /*
    We need methods that do database that will store User Photos. Group creation. Event creation.
    Photo Shared by User (Media History).
     */
//
//    public void User_Photo(String[] photoLink) throws SQLException, IOException {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        FileInputStream imageInputStream = null;
//
//        try {
//            connection = DriverManager.getConnection(
//                                        "jdbc:mysql://localhost:3306/codippa", "root", "root");
//            statement = connection.prepareStatement("insert into users(Type,Description,Status,ImageData) values('Bug','Test','Active',?)");
//            imageInputStream = new FileInputStream(new File("d:\\download.png"));
//            statement.setBinaryStream(1, imageInputStream);
//            statement.execute();
//        } catch (SQLException sqe) {
//            throw sqe;
//        } finally {
//            if (connection != null)
//                connection.close();
//            if (imageInputStream != null)
//                imageInputStream.close();
//        }
//    }
//    // ??? //
