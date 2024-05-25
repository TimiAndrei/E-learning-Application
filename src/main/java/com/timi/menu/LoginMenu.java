package com.timi.menu;

import java.util.Scanner;
import com.timi.service.ElearningService;
import com.timi.service.impl.ElearningServiceImpl;
import com.timi.exception.DAOException;
import com.timi.model.*;

public class LoginMenu extends Menu{

    private static Scanner scanner = new Scanner(System.in);
    private static ElearningService elearningService = new ElearningServiceImpl();
    private static User loggedInUser = null;

    @Override
    protected Menu createInstance() {
        return new LoginMenu();
    }

    @Override
    public void show() throws DAOException {
        System.out.println("Welcome to the E-learning Application");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();  

        switch (choice) {
            case 1:
                register();
                break;
            case 2:
                login();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void register() {
        System.out.println("Register a new account");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        try {
            elearningService.registerUser(email, username, password);
            System.out.println("Registration successful. Please login.");
        } catch (DAOException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }

    private static void login() {
        System.out.println("Login to your account");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            loggedInUser = elearningService.authenticateUser(email, password);
            if (loggedInUser != null) {
                System.out.println("Login successful. Welcome, " + loggedInUser.getUsername());
            } else {
                System.out.println("Invalid email or password. Please try again.");
            }
        } catch (DAOException e) {
            System.out.println("Error logging in: " + e.getMessage());
        }
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

}