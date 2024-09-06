package se.lexicon.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Random;

public class User {
    private int id;
    private final String username;
    private String password;
    private boolean expired;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public User(String username, String password) {
        this.username = username;
        this.password = hashPassword(password);
        this.expired = false;
    }


    public User(int id, String username, String password, boolean expired) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.expired = expired;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    // Hash password using BCrypt
    private String hashPassword(String password) {
        return encoder.encode(password);
    }


    public boolean checkPassword(String rawPassword) {
        return encoder.matches(rawPassword, this.password);
    }

    public String userInfo() {
        return "Username: " + username + ", Expired: " + expired;
    }

    private String generateRandomPassword() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new SecureRandom();
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int passwordLength = 10;

        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());
            char randomChar = allowedCharacters.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
}
