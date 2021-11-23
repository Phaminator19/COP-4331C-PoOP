package com.example.project_1;

import java.util.List;
import java.util.Formatter;
import java.util.ArrayList;

public class FriendList extends ListType{
    List<User> friendList;

    public FriendList () {
        friendList = new ArrayList<>();
    }

    @Override
    public void addFriend(User user){
        friendList.add(user);
    }

    @Override
    public void removeFriend(int index) {
        friendList.remove(index);
    }

    @Override
    public void view() {
        int i = 0;
        for (User user : friendList) {
            System.out.printf("%d) " + user.getUserName(), i);
            System.out.println("\n");
            i++;
        }
    }
}
