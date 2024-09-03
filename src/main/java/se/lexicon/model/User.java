package se.lexicon.model;

import java.security.SecureRandom;
import java.util.Random;

public class User {
    private int id;  // Add this field
    private final String username;
    private String password;
    private boolean expired;

    // Constructor for registration (password is generated)
    public User(String username) {
        this.username = username;
        this.password = generateRandomPassword();
        this.expired = false;
    }

    // Constructor for saving or updating a user in the database (without an ID)
    public User(String username, String password, boolean expired) {
        this.username = username;
        this.password = password;
        this.expired = expired;
    }

    // Constructor for creating a user object with an ID (for fetching from the database)
    public User(int id, String username, String password, boolean expired) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.expired = expired;
    }

    // Getters and setters

    public int getId() {  // Add this method
        return id;
    }

    public void setId(int id) {  // Add setter for id
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
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
