package com.timi.menu;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.timi.service.ElearningService;
import com.timi.service.impl.ElearningServiceImpl;
import com.timi.exception.DAOException;
import com.timi.model.*;

public class StudentMenu extends Menu {

    private static Scanner scanner = new Scanner(System.in);
    private static ElearningService elearningService = new ElearningServiceImpl();

    @Override
    public void show() throws DAOException {
        while (true) {
            System.out.println("Student Menu");
            System.out.println("1. Search Course by Name");
            System.out.println("2. Apply for Course");
            System.out.println("3. Take Quiz");
            System.out.println("4. View your quizzes attempts");
            System.out.println("5. View all courses available");
            System.out.println("6. View all quizzes available");
            System.out.println("7. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    searchCourseByName();
                    break;
                case 2:
                    applyForCourse();
                    break;
                case 3:
                    takeQuiz();
                    break;
                case 4:
                    viewUserQuizAttempts();
                    break;
                case 5:
                    viewCourses();
                    break;
                case 6:
                    viewQuizzes();
                    break;
                case 7:
                    Menu.setLoggedInUser(null);
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void searchCourseByName() throws DAOException {
        System.out.println("Searching for a course by name");
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        scanner.nextLine();

        try{
            Course course = elearningService.getCourseByName(courseName);

        if (course != null) {
            System.out.println(course);
        } else {
            System.out.println("Course not found.");
        }
        } catch (DAOException e) {
            System.out.println("Error searching for course: " + e.getMessage());
        }
    }

    private static void applyForCourse() {
        Application application = new Application();
        System.out.println("Applying for a course");
        System.out.println("These are the available courses: ");
        try {
            List<Course> courses = elearningService.getAllCourses();
            for (Course course : courses) {
                System.out.println(course);
            }
        } catch (DAOException e) {
            System.out.println("Error getting courses: " + e.getMessage());
        }
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        application.setCourseId(courseId);
        application.setUserId(Menu.getLoggedInUser().getId());
        application.setStatus("PENDING");
        System.out.println("Enter application letter message: ");
        String message = scanner.nextLine();
        application.setApplicationLetter(message);
        Date date = new Date(System.currentTimeMillis());
        application.setApplicationDate(date);

        try {
            elearningService.applyForCourse(application);
            System.out.println("Application submitted successfully.");
        } catch (DAOException e) {
            System.out.println("Error applying for course: " + e.getMessage());
        }
    }

    private static void takeQuiz() throws DAOException {
        QuizAttempt quizAttempt = new QuizAttempt();
        System.out.println("For what course do you want to take the quiz?");
        try {
            System.out.println("Your Courses:");
            List<Course> courses = elearningService.getUserCourses(Menu.getLoggedInUser().getId());
            for (Course course : courses) {
                System.out.println(course);
            }
        } catch (DAOException e) {
            System.out.println("Error getting user courses: " + e.getMessage());
        }
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        
        try {
            System.out.println("Quizzes available for this course:");
            List<Quiz> quizzes = elearningService.getQuizzesByCourse(courseId);
            for (Quiz quiz : quizzes) {
                System.out.println(quiz);
            }
        } catch (DAOException e) {
            System.out.println("Error getting quizzes: " + e.getMessage());
        }
        System.out.print("Enter quiz ID: ");
        int quizId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Taking the quiz...");
        System.out.println("Good luck " + Menu.getLoggedInUser().getUsername() + "!");
        System.out.println("Timer starts now!");
        LocalDateTime startTime = LocalDateTime.now();
        Quiz quiz = elearningService.getQuizById(quizId);
        quizAttempt.setQuizId(quizId);
        quizAttempt.setUserId(Menu.getLoggedInUser().getId());

        List<Question> questionsAttempted = new ArrayList<>();
        System.out.println("Answer the following questions with the number of the answer you think is correct: ");        
        List<Question> questions = quiz.getQuestions();
        if (questions == null) {
            System.out.println("No questions found for this quiz.");
            return;
        }
        for (Question question : questions) {
            System.out.println(question.getContent());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i) + ". " + options.get(i));
            }
            System.out.print("Enter your answer: ");
            int answer = scanner.nextInt();
            question.setSelectedOptionIndex(answer);
            scanner.nextLine();
            questionsAttempted.add(question);      
        }

        LocalDateTime endTime = LocalDateTime.now();
        float duration = (float) ChronoUnit.SECONDS.between(startTime, endTime);
        System.out.println("Quiz completed! Duration: " + duration + " seconds.");
        quizAttempt.setDurationAttempted(duration);
        quizAttempt.setQuestionsAttempted(questionsAttempted);
        quizAttempt.setScore(quizAttempt.calculateQuizScore());

        System.out.println("Quizz attempt: ");
        System.out.println(quizAttempt);
        try {
            elearningService.takeQuiz(quizAttempt);
            System.out.println("Quiz submitted successfully.");
        } catch (DAOException e) {
            System.out.println("Error taking quiz: " + e.getMessage());
        }
    }

    private static void viewUserQuizAttempts() {
        System.out.println("Your Quiz Attempts:");
        try {
            List<QuizAttempt> quizAttempts = elearningService.getUserQuizAttempts(Menu.getLoggedInUser().getId());
            for (QuizAttempt quizAttempt : quizAttempts) {
                System.out.println(quizAttempt.showPreview());
            }
        } catch (DAOException e) {
            System.out.println("Error getting quiz attempts: " + e.getMessage());
        }
    }

    private static void viewCourses() {
        System.out.println("All Courses:");
        try {
            List<Course> userCourses = elearningService.getAllCourses();
            for (Course course : userCourses) {
                System.out.println(course);
            }
        } catch (DAOException e) {
            System.out.println("Error getting courses: " + e.getMessage());
        }
    }

    private static void viewQuizzes() {
        System.out.println("All Quizzes:");
        try {
            List<Quiz> quizzes = elearningService.getAllQuizzes();
            for (Quiz quiz : quizzes) {
                System.out.println(quiz.showPreview());
            }
        } catch (DAOException e) {
            System.out.println("Error getting quizzes: " + e.getMessage());
        }
    }
}