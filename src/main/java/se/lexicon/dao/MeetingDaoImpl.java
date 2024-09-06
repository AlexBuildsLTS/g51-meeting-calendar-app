package se.lexicon.dao;

import se.lexicon.exception.MySQLException;
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
            // Insert meeting details into meetings table
            String query = "INSERT INTO meetings (title, description, startTime, endTime, userId) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, meeting.getTitle());
            statement.setString(2, meeting.getDescription());
            statement.setTimestamp(3, java.sql.Timestamp.valueOf(meeting.getStartTime()));
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(meeting.getEndTime()));
            statement.setInt(5, user.getId());
            statement.executeUpdate();

            // Retrieve the generated meeting ID
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                meeting.setId(rs.getInt(1));  // Setting the generated ID
            }

            // Insert participants into the meeting_participants table
            addParticipantsToMeeting(meeting, connection);

        } catch (SQLException e) {
            throw new MySQLException("Error adding meeting", e);
        }
    }

    private void addParticipantsToMeeting(Meeting meeting, Connection connection) throws SQLException {
        String query = "INSERT INTO meeting_participants (meeting_id, user_id) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        for (User participant : meeting.getParticipants()) {
            statement.setInt(1, meeting.getId());
            statement.setInt(2, participant.getId());
            statement.addBatch();
        }
        statement.executeBatch();
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

                List<User> participants = getMeetingParticipants(id, connection);

                meetings.add(new Meeting(id, title, description, startTime, endTime, participants));
            }
        } catch (SQLException e) {
            throw new MySQLException("Error finding meetings by user", e);
        }
        return meetings;
    }

    private List<User> getMeetingParticipants(int meetingId, Connection connection) throws SQLException {
        String query = "SELECT users.* FROM users " +
                "JOIN meeting_participants ON users.id = meeting_participants.user_id " +
                "WHERE meeting_participants.meeting_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, meetingId);
        ResultSet resultSet = statement.executeQuery();

        List<User> participants = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            boolean expired = resultSet.getBoolean("expired");

            participants.add(new User(id, username, password, expired));
        }
        return participants;
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

            // Update participants
            deleteMeetingParticipants(meeting, connection);
            addParticipantsToMeeting(meeting, connection);

        } catch (SQLException e) {
            throw new MySQLException("Error updating meeting", e);
        }
    }

    private void deleteMeetingParticipants(Meeting meeting, Connection connection) throws SQLException {
        String query = "DELETE FROM meeting_participants WHERE meeting_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, meeting.getId());
        statement.executeUpdate();
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Delete the meeting from the meetings table
            String query = "DELETE FROM meetings WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, meeting.getId());
            statement.executeUpdate();


            deleteMeetingParticipants(meeting, connection);

        } catch (SQLException e) {
            throw new MySQLException("Error deleting meeting", e);
        }
    }
}
