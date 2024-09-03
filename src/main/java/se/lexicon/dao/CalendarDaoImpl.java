package se.lexicon.dao;

import se.lexicon.model.Calendar;
import se.lexicon.model.User;
import se.lexicon.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalendarDaoImpl implements CalendarDao {

    @Override
    public void addCalendar(Calendar calendar) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO calendars (userId) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, calendar.getUser().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Calendar findCalendarByUser(User user) {
        Calendar calendar = new Calendar(user);
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM calendars WHERE userId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                calendar.setId(resultSet.getInt("id"));
                // Further logic if needed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    @Override
    public void updateCalendar(Calendar calendar) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE calendars SET userId = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, calendar.getUser().getId());
            statement.setInt(2, calendar.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCalendar(Calendar calendar) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM calendars WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, calendar.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
