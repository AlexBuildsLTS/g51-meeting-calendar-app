package se.lexicon;

import se.lexicon.model.Calendar;
import se.lexicon.model.User;

public class App {
    public static void main(String[] args) {
        // Creating users
        User user1 = new User("Alex Youssef");
        User user2 = new User("Sandra");

        // Print user info
        System.out.println(user1.userInfo());
        System.out.println(user2.userInfo());

        // Creating a calendar for a user
