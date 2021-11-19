package com.example.project_1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    Database new_database = new Database();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void read_users_info (View v) throws IOException{

        EditText user_name = findViewById(R.id.user_name);
        EditText email_address = findViewById(R.id.user_emailAddress);
        EditText password = findViewById(R.id.user_password);
        
        String name = user_name.getText().toString();
        String email = email_address.getText().toString();
        String pass = password.getText().toString();
        
        User new_user = new User(name, email, pass);

        FileOutputStream user_db;
        File textfile = new File("user.db");
        if(isTheFileExists(textfile)) {
            user_db = new FileOutputStream(textfile, false);
            new_database.loadDataBase(user_db, new_user);
        }
        else {
            user_db = new FileOutputStream(textfile, true);
            new_database.loadDataBase(user_db, new_user);
        }

    }

    public boolean isTheFileExists(File user_db) {
        return user_db.exists();
    }




    /*
    We need methods that do database that will store User Photos. Group creation. Event creation.
    Photo Shared by User (Media History).
     */

    public void User_Photo(String[] photoLink) throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        FileInputStream imageInputStream = null;

        try {
            connection = DriverManager.getConnection(
                                        "jdbc:mysql://localhost:3306/codippa", "root", "root");
            statement = connection.prepareStatement("insert into users(Type,Description,Status,ImageData) values('Bug','Test','Active',?)");
            imageInputStream = new FileInputStream(new File("d:\\download.png"))
            statement.setBinaryStream(1, imageInputStream);
            statement.execute();
        } catch (SQLException sqe) {
            throw sqe;
        } finally {
            if (connection != null)
                connection.close();
            if (imageInputStream != null)
                imageInputStream.close();
        }
    }

    public
}