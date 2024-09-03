package se.lexicon.dao;

import se.lexicon.model.User;

import java.util.List;

public interface UserDao {
    User findByUsername(String username);
    List<User> findAll(); // New method
    void save(User user);
    void update(User user);
    void delete(User user);
}
