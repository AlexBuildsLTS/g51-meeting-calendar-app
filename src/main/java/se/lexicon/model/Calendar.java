package se.lexicon.model;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private List<Meeting> meetings;

    public Calendar() {
        this.meetings = new ArrayList<>();
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
