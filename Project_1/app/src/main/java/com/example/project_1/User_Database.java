package com.example.project_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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


    public void updateDatabase(User newUser) {
//        File data = new File("users.db");
//        PrintWriter fs;
//        try {
//            fs = new PrintWriter(data);
//        } catch (IOException e) {
//            System.out.println("IO Exception");
//            return;
//        }
//        fs.flush();
        userList.add(newUser);
    }

    public Object loadDataBase(PrintWriter file, User new_user) throws IOException {
//        File data = new File(filename);
//        Scanner reader = new Scanner(data);

            String name = new_user.getUserName();
            String email = new_user.getEmailAddress();
            String pass = new_user.getPassword();

            User created_user = new User("Placeholder", "Placeholder@aol.com", "wtf555665532");
            created_user.updateUser(name, email, pass);

            updateDatabase(created_user);

            file.println("Username: " + userList.get(0).getUserName());
            file.println("password: " + userList.get(0).getPassword());

            userList.clear();
            return file;

    }

}
