package com.timi;

import com.timi.dao.*;
import com.timi.dao.impl.*;

public class App {
    public static void main(String[] args) {
        try {
            System.out.println("Hello World!");

            // Fetch all data from the database and print it

            // Fetch all users
            UserDAO userDAO = new UserDAOImpl();
            System.out.println("Users:");
            userDAO.getAllUsers().forEach(System.out::println);

            // Fetch all courses
            CourseDAO courseDAO = new CourseDAOImpl();
            System.out.println("Courses:");
            courseDAO.getAllCourses().forEach(System.out::println);

            // Fetch all quizzes
            QuizDAO quizDAO = new QuizDAOImpl();
            System.out.println("Quizzes:");
            quizDAO.getAllQuizzes().forEach(System.out::println);

            // Fetch all questions
            QuestionDAO questionDAO = new QuestionDAOImpl();
            System.out.println("Questions:");
            questionDAO.getAllQuestions().forEach(System.out::println);

            // Fetch all quiz attempts
            QuizAttemptDAO quizAttemptDAO = new QuizAttemptDAOImpl();
            System.out.println("Quiz Attempts:");
            quizAttemptDAO.getAllQuizAttempts().forEach(System.out::println);
  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


