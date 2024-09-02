package se.lexicon.dao;

import se.lexicon.model.Meeting;
import se.lexicon.model.User;
import se.lexicon.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MeetingDaoImpl implements MeetingDao {

    @Override
    public void addMeeting(Meeting meeting, User user) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO meetings (title, startTime, endTime, userId) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, meeting.getTitle());
            statement.setTimestamp(2, java.sql.Timestamp.valueOf(meeting.getStartTime()));
            statement.setTimestamp(3, java.sql.Timestamp.valueOf(meeting.getEndTime()));
            statement.setInt(4, user.getId()); // Assuming User has an ID field
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Meeting> findMeetingsByUser(User user) {
        // Implement query to retrieve meetings by user
        return null; // Placeholder
    }

    @Override
    public void updateMeeting(Meeting meeting) {
        // Implement query to update a meeting
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM meetings WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, meeting.getId()); // Assuming Meeting has an ID field
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
