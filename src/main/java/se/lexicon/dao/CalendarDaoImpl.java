package se.lexicon.dao;

import se.lexicon.model.Calendar;
import se.lexicon.model.User;
import se.lexicon.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CalendarDaoImpl implements CalendarDao {

    @Override
    public void addCalendar(Calendar calendar) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO calendars (userId) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, calendar.getUser().getId()); // Assuming Calendar has a User field
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Calendar findCalendarByUser(User user) {
        // Implement query to retrieve a calendar by user
        return null; // Placeholder
    }

    @Override
    public void updateCalendar(Calendar calendar) {
        // Implement query to update a calendar
    }

    @Override
    public void deleteCalendar(Calendar calendar) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM calendars WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, calendar.getId()); // Assuming Calendar has an ID field
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
