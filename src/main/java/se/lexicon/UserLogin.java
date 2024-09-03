package se.lexicon;

import se.lexicon.dao.UserDao;
import se.lexicon.dao.UserDaoImpl;
import se.lexicon.model.User;

import java.util.Scanner;

public class UserLogin {
    private static UserDao userDao = new UserDaoImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static boolean login() {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();

        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        User user = userDao.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }
    }
}
