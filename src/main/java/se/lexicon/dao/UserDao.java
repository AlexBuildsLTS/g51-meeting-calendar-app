package se.lexicon.dao;

import se.lexicon.model.User;

public interface UserDao {
    User findByUsername(String username);
    void save(User user);
    void update(User user);
    void delete(User user);
}
