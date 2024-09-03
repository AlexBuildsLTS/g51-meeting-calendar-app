package se.lexicon;

import se.lexicon.dao.UserDao;
import se.lexicon.dao.UserDaoImpl;
import se.lexicon.model.User;
import se.lexicon.util.PasswordGenerator;

import java.util.Scanner;

public class UserRegistration {
    private static final UserDao userDao = new UserDaoImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void register() {
        System.out.println("Enter your desired username: ");
        String username = scanner.nextLine();

        // Generate password
        String password = PasswordGenerator.generateRandomPassword();

        // Create User object
        User user = new User(username, password);

        // Save user to database
        userDao.save(user);

        // Display generated password to user
        System.out.println("Registration successful! Your password is: " + password);
    }
}
