package com.timi;

import java.util.Date;

import com.timi.dao.impl.UserDAOImpl;
import com.timi.model.*;

public class App {
    public static void main(String[] args) {
        // Instantiate UserDAOImpl
        UserDAOImpl userDAO = new UserDAOImpl();

        try {
            // Example: Insert a new student into the database
            Student student = new Student("test@gmail.com", "Timi", "Test", Level.BEGINNER, 0);
            userDAO.addUser(student);

            Instructor instructor = new Instructor("Dragan@unibuc.ro", "Mihaita", "1234", new Date(), "CSIE");
            userDAO.addUser(instructor);

            // Example: Insert a new admin into the database
            Admin admin = new Admin("admin@gmail.com","Admin", "1234", "1234567890");
            userDAO.addUser(admin);

            // Update the email of the student 
            student.setEmail("newEmail@gmail.com");
            userDAO.updateUser(student);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
