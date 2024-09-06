package se.lexicon.dao;

import se.lexicon.exception.DBConnectionException;
import se.lexicon.exception.UserNotFoundException;
import se.lexicon.model.User;
import se.lexicon.util.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User findByUsername(String username) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String password = resultSet.getString("password");
                boolean expired = resultSet.getBoolean("expired");
                int id = resultSet.getInt("id");
                return new User(id, username, password, expired);
            } else {
                throw new UserNotFoundException("User not found with username: " + username);
            }
        } catch (SQLException e) {
            logger.error("SQL error occurred while finding user by username", e);
            throw new DBConnectionException("Database error occurred while finding user by username", e);
        }
    }

    @Override
    public void save(User user) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO users (username, password, expired) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.isExpired());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error saving user", e);
            throw new DBConnectionException("Database error occurred while saving user", e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE users SET password = ?, expired = ? WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getPassword());
            statement.setBoolean(2, user.isExpired());
            statement.setString(3, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating user", e);
            throw new DBConnectionException("Database error occurred while updating user", e);
        }
    }

    @Override
    public void delete(User user) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting user", e);
            throw new DBConnectionException("Database error occurred while deleting user", e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                boolean expired = resultSet.getBoolean("expired");
                users.add(new User(id, username, password, expired));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving all users", e);
            throw new DBConnectionException("Database error occurred while retrieving all users", e);
        }
        return users;
    }
}
