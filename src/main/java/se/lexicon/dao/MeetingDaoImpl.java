package se.lexicon.dao;

import se.lexicon.model.Meeting;
import se.lexicon.model.User;
import se.lexicon.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingDaoImpl implements MeetingDao {

    @Override
    public void addMeeting(Meeting meeting, User user) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO meetings (title, description, startTime, endTime, userId) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, meeting.getTitle());
            statement.setString(2, meeting.getDescription());
            statement.setTimestamp(3, java.sql.Timestamp.valueOf(meeting.getStartTime()));
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(meeting.getEndTime()));
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Meeting> findMeetingsByUser(User user) {
        List<Meeting> meetings = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM meetings WHERE userId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
                meetings.add(new Meeting(id, title, description, startTime, endTime, null)); // Participants can be added later if needed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meetings;
    }

    @Override
    public void updateMeeting(Meeting meeting) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE meetings SET title = ?, description = ?, startTime = ?, endTime = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, meeting.getTitle());
            statement.setString(2, meeting.getDescription());
            statement.setTimestamp(3, java.sql.Timestamp.valueOf(meeting.getStartTime()));
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(meeting.getEndTime()));
            statement.setInt(5, meeting.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM meetings WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, meeting.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
