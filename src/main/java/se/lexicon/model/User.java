package se.lexicon.model;

import java.security.SecureRandom;
import java.util.Random;

public class User {
    private int id;  // Add this field if you need an id for database purposes
    private final String username;
    private String password;
    private boolean expired;

    // Constructor for registration (password is generated, id is auto-generated)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.expired = false;
    }

    // Constructor for authentication
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.expired = false;
    }

    // Constructor for fetching data from DB
    public User(int id, String username, String password, boolean expired) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.expired = expired;
    }

    // Getter for 'id'
    public int getId() {
        return id;
    }

    // Setter for 'id'
    public void setId(int id) {
        this.id = id;
    }

    // Getter for 'username'
    public String getUsername() {
        return username;
    }

    // Getter for 'password'
    public String getPassword() {
        return password;
    }

    // Getter for 'expired'
    public boolean isExpired() {
        return expired;
    }

    // Setter for 'expired'
    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    // Method to return user information
    public String userInfo() {
        return "Username: " + username + ", Expired: " + expired;
    }

    // Method to generate a random password
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
