package com.example.project_1;

import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User_Database {
    public List<User> userList;

    public User_Database() {
        userList = new ArrayList<>();
    }
    public void updateDatabase(User user) {
        userList.add(user);
    }

    public void loadDataBase(User user, String filename) throws IOException {

        //Toast.makeText(Signup.this, file.getAbsolutePath() + " is created",Toast.LENGTH_SHORT).show();
        try {
            File file = new File(filename);
            if(file.createNewFile()) {
                System.out.println("File is created at " + file.getAbsolutePath());
            }
            else {
                System.out.println("...");
            }
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);

            pr.println(user.getUserName());
            pr.println(user.getPassword());
            pr.close();
            br.close();
            fr.close();
            updateDatabase(user);

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("An error has occurred. User database has error.");
        } catch (IOException error) {
            throw new IOException("An error has occurred. User database has error.");
        }

    }

}
