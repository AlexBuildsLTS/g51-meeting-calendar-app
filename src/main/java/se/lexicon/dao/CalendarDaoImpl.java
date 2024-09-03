package se.lexicon.dao;

import se.lexicon.model.Calendar;
import se.lexicon.model.User;
import se.lexicon.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalendarDaoImpl implements CalendarDao {

    @Override
    public void addCalendar(Calendar calendar) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO calendars (title, userId) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, calendar.getTitle());
            statement.setInt(2, calendar.getUser().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Calendar> findCalendarsByUser(User user) {
        List<Calendar> calendars = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM calendars WHERE userId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Calendar calendar = new Calendar(title, user);
                calendar.setId(id);
                calendars.add(calendar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return calendars;
    }

    @Override
    public Calendar findCalendarById(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM calendars WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                int userId = resultSet.getInt("userId");
                User user = new User(userId, null, null, false);
                Calendar calendar = new Calendar(title, user);
                calendar.setId(id);
                return calendar;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateCalendar(Calendar calendar) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE calendars SET title = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, calendar.getTitle());
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
