package se.lexicon;

import java.util.Scanner;

public class CalendarApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Calendar App!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

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
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 3.");
            }
        }

        scanner.close();
    }

    public static void showMainMenu() {
        // Main menu code here after successful login
        System.out.println("Main Menu: ");
        // Options for creating calendar, meetings, etc.
    }
}