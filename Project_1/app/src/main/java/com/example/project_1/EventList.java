package com.example.project_1;

import java.util.List;

public class EventList extends EventListType {
    List<Event> eventList;
    @Override
    public void addEvent(Event event) {
        eventList.add(event);
    }

    @Override
    public void removeEvent(int index) {
        eventList.remove(index);
    }

    @Override
    public void view() {
        int i = 0;
        for(Event e : eventList) {
            System.out.printf("%d) " + e.getEventName(), i);
            System.out.println("\n");
            i++;
        }
    }

    @Override
    public Integer size() {
        return eventList.size();
    }

    @Override
    public Event get(int index) {
        return eventList.get(index);
    }
}
