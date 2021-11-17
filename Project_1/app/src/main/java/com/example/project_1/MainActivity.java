package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.File;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public FileOutputStream User_Database(View v, String str) throws IOException {
        FileOutputStream textFile = new FileOutputStream ("users.properties.txt");

        /*
        Saving the user name, email address, and password. User mode into the textFile. We will use
        this text file to compare the user strings again so that person can access their profile again
         */
        byte[] strToBytes = str.getBytes();
        textFile.write(strToBytes);

        textFile.close();

        return textFile;
    }

    /*
    We need methods that do database that will store User Photos. Group creation. Event creation.
    Photo Shared by User (Media History).
     */

}