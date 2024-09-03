package se.lexicon.model;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private int id; // Add this field to match the 'id' in the database
    private User user; // Add this field if a Calendar is associated with a User
    private List<Meeting> meetings;

    public Calendar(User user) { // Adjusted constructor to include User
        this.user = user;
        this.meetings = new ArrayList<>();
    }

    public int getId() { // Add getter for 'id'
        return id;
    }

    public void setId(int id) { // Add setter for 'id'
        this.id = id;
    }

    public User getUser() { // Add getter for 'user'
        return user;
    }

    public void setUser(User user) { // Add setter for 'user'
        this.user = user;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    public void removeMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    public void listMeetings() {
        if (meetings.isEmpty()) {
            System.out.println("No meetings scheduled.");
        } else {
            for (Meeting meeting : meetings) {
                System.out.println(meeting);
            }
        }
    }
}
