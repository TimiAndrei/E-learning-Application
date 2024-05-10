package com.timi;

import java.util.List;
import java.util.Queue;

import com.timi.service.ElearningService;
import com.timi.service.DataService;
import com.timi.service.impl.ElearningServiceImpl;
import com.timi.service.impl.DataServiceImpl;
import com.timi.exception.DAOException;
import com.timi.model.*;

public class App {
    public static void main(String[] args) throws Exception {

        ElearningService elearningService = new ElearningServiceImpl();
        DataService dataService = new DataServiceImpl();

        try {
            dataService.loadDatabase();
        } catch (DAOException e) {
            System.out.println("Error loading data from the database: " + e.getMessage());
        }

        // Printing all the data from the database 
        try {
            dataService.printAllData();
        } catch (DAOException e) {
            System.out.println("Error printing data: " + e.getMessage());
        }

        // Specific actions using the Elearning Service like getting all the pending applications
        try{
            Queue<Application> pendingApplications = elearningService.getPendingApplications();
            System.out.println("Pending applications:");
            for (Application application : pendingApplications) {
                System.out.println(application);
            }
            System.out.println();
        } catch (DAOException e) {
            System.out.println("Error getting pending applications: " + e.getMessage());
        }

        // Specific actions using the Elearning Service like Course by specific User
        try{
            List<Course> userCourses = elearningService.getUserCourses(3);
            System.out.println("Courses for user with id 3:");
            for (Course course : userCourses) {
                System.out.println(course);
            }
            System.out.println();
        } catch (DAOException e) {
            System.out.println("Error getting user courses: " + e.getMessage());
        }
    }
}





