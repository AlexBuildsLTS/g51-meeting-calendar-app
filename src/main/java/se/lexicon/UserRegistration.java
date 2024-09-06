package se.lexicon;

import se.lexicon.dao.UserDao;
import se.lexicon.dao.UserDaoImpl;
import se.lexicon.model.User;
import se.lexicon.util.PasswordGenerator;
import se.lexicon.util.PasswordHasher;

import java.util.Scanner;

public class UserRegistration {
    private static final UserDao userDao = new UserDaoImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void register() {
        System.out.println("Enter your desired username: ");
        String username = scanner.nextLine();


        String password = PasswordGenerator.generateRandomPassword();
        String hashedPassword = PasswordHasher.hashPassword(password);


        User user = new User(username, hashedPassword);


        userDao.save(user);



        System.out.println("Registration successful! Your password is: " + password);

        User existingUser = userDao.findByUsername(username);
        if (existingUser != null) {
            System.out.println("Username already exists. Please choose a different one.");

        }


    }
}
