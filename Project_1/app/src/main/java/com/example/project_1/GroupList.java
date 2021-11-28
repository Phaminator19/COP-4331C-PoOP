package com.example.project_1;

import java.util.List;
import java.util.Formatter;
import java.util.ArrayList;

public class GroupList extends ListType{
    List<Group> GroupList;

    public GroupList() {
        GroupList = new ArrayList<>();
    }

    @Override
    public void addGroup(Group group){
        GroupList.add(group);
    }

    @Override
    public void removeGroup(int index) {
        GroupList.remove(index);
    }

    @Override
    public void view() {
        int i = 0;
        for (Group group : GroupList) {
            System.out.printf("%d) " + group.getName(), i);
            System.out.println("\n");
            i++;
        }
    }

    @Override
    public Integer size() {
        return GroupList.size();
    }

    @Override
    public Group get(int index) {
        return GroupList.get(index);
    }
}
