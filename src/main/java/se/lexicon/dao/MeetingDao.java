package se.lexicon.dao;

import se.lexicon.model.Meeting;
import se.lexicon.model.User;

import java.util.List;

public interface MeetingDao {
    void addMeeting(Meeting meeting, User user);
    List<Meeting> findMeetingsByUser(User user);
    void updateMeeting(Meeting meeting);
    void deleteMeeting(Meeting meeting);
}
