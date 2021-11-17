package com.example.project_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    public List<User> userList;

    public Database() {
        userList = new ArrayList<>();
    }

    public void updateDatabase(User newUser) {
        File data = new File("users.db");
        PrintWriter fs;
        try {
            fs = new PrintWriter(data);
        } catch (IOException e) {
            System.out.println("IO Exception");
            return;
        }
        fs.flush();

        userList.add(newUser);
    }

    public Object loadDataBase(String filename, User new_user) throws FileNotFoundException {
//        File data = new File(filename);
//        Scanner reader = new Scanner(data);

            String name = new_user.getUserName();
            String email = new_user.getEmailAddress();
            String pass = new_user.getPassword();

            User created_user = new User("Placeholder", "Placeholder@aol.com", "wtf555665532");
            created_user.updateUser(name, email, pass);

            updateDatabase(created_user);

    }
}
