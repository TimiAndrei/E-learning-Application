package com.timi.menu;

import java.util.Scanner;
import com.timi.service.ElearningService;
import com.timi.service.impl.ElearningServiceImpl;
import com.timi.exception.DAOException;
import com.timi.exception.InvalidEmailException;
import com.timi.model.*;

public class AdminMenu extends Menu {

    private static Scanner scanner = new Scanner(System.in);
    private static ElearningService elearningService = new ElearningServiceImpl();
    private static User loggedInUser = null;

    @Override
    protected Menu createInstance() {
        return new AdminMenu();
    }

    @Override
    public void show() throws DAOException, InvalidEmailException {
        while (true) {
            System.out.println("Admin Menu");
            System.out.println("1. Create User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    updateUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    loggedInUser = null;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createUser() {
        System.out.println("Creating a new user");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            elearningService.registerUser(email, username, password);
            System.out.println("User created successfully.");
        } catch (DAOException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    private static void updateUser() throws DAOException, InvalidEmailException {
        System.out.println("Updating an existing user");
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        User user = elearningService.getUserById(userId);
        scanner.nextLine();  
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        user.setEmail(email);
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        user.setUsername(username);
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        user.setPassword(password);
        System.out.print("Enter new role (STUDENT/INSTRUCTOR/ADMIN): ");
        String role = scanner.nextLine();
        user.setRole(role);

        if (!role.equals("STUDENT") && !role.equals("INSTRUCTOR") && !role.equals("ADMIN")) {
            System.out.println("Invalid role. Please try again.");
            return;
        }

        if (userId == loggedInUser.getId() && !role.equals(loggedInUser.getRole())) {
            System.out.println("You cannot change your own role. Please try again.");
            return;
        }

        if (user.getRole().equals("INSTRUCTOR")) {
            Instructor instructor = (Instructor) user;
            System.out.println("Enter new department:");
            String department = scanner.nextLine();
            instructor.setDepartment(department);
            try {
                elearningService.updateUser(instructor);
            } catch (DAOException e) {
                System.out.println("Error updating user: " + e.getMessage());
            }
        } else if (user.getRole().equals("ADMIN")) {
            Admin admin = (Admin) user;
            System.out.println("Enter new telephone:");
            String telephone = scanner.nextLine();
            admin.setTelephone(telephone);
            try {
                elearningService.updateUser(admin);
            } catch (DAOException e) {
                System.out.println("Error updating user: " + e.getMessage());
            }
        } else if (user.getRole().equals("STUDENT")) {
            try {
                elearningService.updateUser(user);
            } catch (DAOException e) {
                System.out.println("Error updating user: " + e.getMessage());
            }
        }
        
        System.out.println("User updated successfully.");
    }

    private static void deleteUser() {
        System.out.println("Deleting a user");
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();  

        try {
            elearningService.deleteUser(userId);
            System.out.println("User deleted successfully.");
        } catch (DAOException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }
}