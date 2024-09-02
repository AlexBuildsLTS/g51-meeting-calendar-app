package se.lexicon.model;

import java.security.SecureRandom;
import java.util.Random;

public class User {
    private final String username;
    private String password;
    private boolean expired;

    // Constructor for registration (password is generated)
    public User(String username) {
        this.username = username;
        this.password = generateRandomPassword();
        this.expired = false;
    }

    // Constructor for authentication
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.expired = false;
    }

    // Constructor for fetching data from DB
    public User(String username, String password, boolean expired) {
        this.username = username;
        this.password = password;
        this.expired = expired;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
