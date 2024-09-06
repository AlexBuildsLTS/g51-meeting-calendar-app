package se.lexicon.model;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private int id;
    private String title;
    private User user;
    private final List<Meeting> meetings;

    public Calendar(String title, User user) {
        this.title = title;
        this.user = user;
        this.meetings = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void addMeeting(Meeting meeting) {
        if (!meetings.contains(meeting)) {
            meetings.add(meeting);
        } else {
            System.out.println("Meeting already exists.");
        }
    }

    public void removeMeeting(Meeting meeting) {
        if (meetings.contains(meeting)) {
            meetings.remove(meeting);
        } else {
            System.out.println("Meeting does not exist.");
        }
    }
}
