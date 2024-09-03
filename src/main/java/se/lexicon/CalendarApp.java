package se.lexicon;

import se.lexicon.dao.CalendarDao;
import se.lexicon.dao.CalendarDaoImpl;
import se.lexicon.dao.UserDao;
import se.lexicon.dao.UserDaoImpl;
import se.lexicon.model.Calendar;
import se.lexicon.model.User;

import java.util.List;
import java.util.Scanner;

public class CalendarApp {

    private static final CalendarDao calendarDao = new CalendarDaoImpl();
    private static final UserDao userDao = new UserDaoImpl();
    private static User currentUser; // Assume this is set after a successful login

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
            System.out.println("7. Create Calendar");
            System.out.println("8. View My Calendars");
            System.out.println("9. Update Calendar");
            System.out.println("10. Delete Calendar");

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
                case 7:
                    createCalendar();
                    break;
                case 8:
                    viewMyCalendars();
                    break;
                case 9:
                    updateCalendar();
                    break;
                case 10:
                    deleteCalendar();
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 10.");
            }
        }

        scanner.close();
    }

    private static void createCalendar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter calendar title:");
        String title = scanner.nextLine();
        Calendar calendar = new Calendar(title, currentUser);
        calendarDao.addCalendar(calendar);
        System.out.println("Calendar created successfully.");
    }

    private static void viewMyCalendars() {
        List<Calendar> calendars = calendarDao.findCalendarsByUser(currentUser);
        for (Calendar calendar : calendars) {
            System.out.println("Calendar ID: " + calendar.getId() + ", Title: " + calendar.getTitle());
        }
    }

    private static void updateCalendar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter calendar ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Calendar calendar = calendarDao.findCalendarById(id);
        if (calendar == null) {
            System.out.println("Calendar not found.");
            return;
        }

        System.out.println("Enter new title:");
        String title = scanner.nextLine();
        calendar.setTitle(title);
        calendarDao.updateCalendar(calendar);
        System.out.println("Calendar updated successfully.");
    }

    private static void deleteCalendar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter calendar ID to delete:");
        int id = scanner.nextInt();

        Calendar calendar = calendarDao.findCalendarById(id);
        if (calendar == null) {
            System.out.println("Calendar not found.");
            return;
        }

        calendarDao.deleteCalendar(calendar);
        System.out.println("Calendar deleted successfully.");
    }

    private static void viewAllUsers() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user.getUsername());
        }
    }

    private static void updateUser() {
        Scanner scanner = new Scanner(System.in);
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

    public static void showMainMenu() {

        System.out.println("Main Menu: ");

    }
}
