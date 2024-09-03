package se.lexicon;

import se.lexicon.dao.UserDao;
import se.lexicon.dao.UserDaoImpl;
import se.lexicon.model.User;

import java.util.List;
import java.util.Scanner;

public class CalendarApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Calendar App!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. View All Users");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    UserRegistration.register();
                    break;
                case 2:
                    if (UserLogin.login()) {
                        showMainMenu();
                    }
                    break;
                case 3:
                    viewAllUsers();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    deleteUser();
                    break;
                case 6:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 6.");
            }
        }

        scanner.close();
    }

    public static void showMainMenu() {
        // Main menu code here after successful login
        System.out.println("Main Menu: ");
        // Options for creating calendar, meetings, etc.
    }

    private static void viewAllUsers() {
        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user.getUsername());
        }
    }

    private static void updateUser() {
        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDaoImpl();

        System.out.println("Enter the username to update:");
        String username = scanner.nextLine();

        User user = userDao.findByUsername(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();

        user.setPassword(newPassword);
        userDao.update(user);
        System.out.println("User updated successfully.");
    }

    private static void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDaoImpl();

        System.out.println("Enter the username to delete:");
        String username = scanner.nextLine();

        User user = userDao.findByUsername(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        userDao.delete(user);
        System.out.println("User deleted successfully.");
    }
}