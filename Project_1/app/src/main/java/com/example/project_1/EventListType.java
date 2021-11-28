package com.example.project_1;

public abstract class EventListType {
    public abstract void addEvent(Event ev);
    public abstract void removeEvent(int index);
    public abstract void view();
    public abstract Integer size();
    public abstract Event get(int index);
}
