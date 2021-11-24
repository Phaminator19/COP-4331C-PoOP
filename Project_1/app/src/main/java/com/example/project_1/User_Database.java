package com.example.project_1;

<<<<<<< HEAD
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
=======

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class User_Database {
    private DatabaseReference reference;

//    public List<User> userList;
//
//    public User_Database() {
//        userList = new ArrayList<>();
//    }
//    public void updateDatabase(User user) {
//        userList.add(user);
//    }
//    public File getRootFileDir(File root) {
//        return root;
//    }
//
//
//    public void writeDataBase(User user, String filename, Context context) throws IOException {
//        //Toast.makeText(Signup.this, file.getAbsolutePath() + " is created",Toast.LENGTH_SHORT).show();
//        try {
//
////            boolean test;
////            //Context context = App.instance.getApplicationContext();
////            File root =
//                    //File file = new File(root, filename);
//
//                FileOutputStream output = context.openFileOutput(filename, Context.MODE_PRIVATE);
//                System.out.println(" or get here?");
//                output.write(user.getUserName().getBytes());
//                output.write(user.getPassword().getBytes());
//                System.out.println(" then get here?");
//
////                    FileWriter fr = new FileWriter(file, true);
////                    BufferedWriter br = new BufferedWriter(fr);
////                    PrintWriter pr = new PrintWriter(br);
////
////                    pr.println(user.getUserName());
////                    pr.println(user.getPassword());
////                    pr.close();
////                    br.close();
////                    fr.close();
//            updateDatabase(user);
//            output.close();
//
//
//                //System.out.println("File is created at " + file.getAbsolutePath());
//            } catch (FileNotFoundException e) {
//                throw new FileNotFoundException("An error has occurred. User database has error.");
//        } catch (IOException error) {
//            throw new IOException("An error has occurred. User database has error.");
//        }
//
//    }

}

>>>>>>> BACKUP_BRANCH
