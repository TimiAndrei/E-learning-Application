package com.timi;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import com.timi.service.ElearningService;
import com.timi.service.DataService;
import com.timi.service.impl.ElearningServiceImpl;
import com.timi.service.impl.DataServiceImpl;
import com.timi.exception.DAOException;
import com.timi.model.*;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static ElearningService elearningService = new ElearningServiceImpl();
    private static DataService dataService = new DataServiceImpl();
    private static User loggedInUser = null;

    public static void main(String[] args) throws Exception {
        try {
            dataService.loadDatabase();
        } catch (DAOException e) {
            System.out.println("Error loading data from the database: " + e.getMessage());
        }

        while (true) {
            if (loggedInUser == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private static void showLoginMenu() {
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

    private static void showMainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. View Courses");
        System.out.println("2. View Quizzes");
        System.out.println("3. View Applications");
        System.out.println("4. Logout");

        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                viewCourses();
                break;
            case 2:
                viewQuizzes();
                break;
            case 3:
                viewApplications();
                break;
            case 4:
                loggedInUser = null;
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

    private static void viewCourses() {
        System.out.println("Your Courses:");
        try {
            List<Course> userCourses = elearningService.getUserCourses(loggedInUser.getId());
            for (Course course : userCourses) {
                System.out.println(course);
            }
        } catch (DAOException e) {
            System.out.println("Error getting user courses: " + e.getMessage());
        }
    }

    private static void viewQuizzes() {
        System.out.println("Your Quizzes:");
        try {
            List<Quiz> quizzes = elearningService.getAllQuizzes();
            for (Quiz quiz : quizzes) {
                System.out.println(quiz);
            }
        } catch (DAOException e) {
            System.out.println("Error getting quizzes: " + e.getMessage());
        }
    }

    private static void viewApplications() {
        System.out.println("Pending Applications:");
        try {
            Queue<Application> pendingApplications = elearningService.getPendingApplications();
            for (Application application : pendingApplications) {
                System.out.println(application);
            }
        } catch (DAOException e) {
            System.out.println("Error getting pending applications: " + e.getMessage());
        }
    }
}
