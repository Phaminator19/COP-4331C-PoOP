package com.example.project_1;

public abstract class ListType {
    public abstract void addGroup(Group group);
    public abstract void removeGroup(int index);
    public abstract void view();
    public abstract Integer size();
    public abstract Group get(int index);
}
