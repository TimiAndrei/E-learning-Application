package com.timi.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.timi.service.ElearningService;
import com.timi.service.impl.ElearningServiceImpl;
import com.timi.exception.DAOException;
import com.timi.model.*;

public class InstructorMenu extends Menu {

    private static Scanner scanner = new Scanner(System.in);
    private static ElearningService elearningService = new ElearningServiceImpl();   

    @Override
    public void show() throws DAOException {
        while (true) {
            System.out.println("Instructor Menu");
            System.out.println("1. Add Course");
            System.out.println("2. Edit Course");
            System.out.println("3. Add Quiz");
            System.out.println("4. Add Question");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    editCourse();
                    break;
                case 3:
                    addQuiz();
                    break;
                case 4:
                    addQuestion();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    
    private static void addCourse() {
        Course course = new Course();
        System.out.println("Adding a new course");
        System.out.print("Enter course title: ");
        String courseTitle = scanner.nextLine();
        course.setTitle(courseTitle);
        System.out.print("Enter course description: ");
        String courseDescription = scanner.nextLine();
        course.setDescription(courseDescription);
        System.out.println("Enter course instructor ID: ");
        int instructorId = scanner.nextInt();
        course.setInstructor(instructorId);
        System.out.println("Enter course price: ");
        double price = scanner.nextDouble();
        course.setPrice(price);
        System.out.println("Enter course duration: ");
        int duration = scanner.nextInt();
        course.setDuration(duration);
        System.out.println("Enter course level (BEGINNER/INTERMEDIATE/ADVANCED): ");
        String level = scanner.nextLine();
        course.setLevel(Level.valueOf(level));

        try {
            elearningService.addCourse(course);
            System.out.println("Course added successfully.");
        } catch (DAOException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }

    }

    private static void editCourse() {
        Course course = new Course();
        System.out.println("Editing an existing course");
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();  
        course.setCourseId(courseId);
        System.out.print("Enter new course title: ");
        String courseName = scanner.nextLine();
        course.setTitle(courseName);
        System.out.print("Enter new course description: ");
        String courseDescription = scanner.nextLine();
        course.setDescription(courseDescription);
        System.out.println("Enter new course instructor ID: ");
        int instructorId = scanner.nextInt();
        course.setInstructor(instructorId);
        System.out.println("Enter new course price: ");
        double price = scanner.nextDouble();
        course.setPrice(price);
        System.out.println("Enter new course duration: ");
        int duration = scanner.nextInt();
        course.setDuration(duration);
        System.out.println("Enter new course level (BEGINNER/INTERMEDIATE/ADVANCED): ");
        String level = scanner.nextLine();
        course.setLevel(Level.valueOf(level));


        try {
            elearningService.updateCourse(course);
            System.out.println("Course updated successfully.");
        } catch (DAOException e) {
            System.out.println("Error editing course: " + e.getMessage());
        }
    }

    private static void addQuiz() throws DAOException {
        Quiz quiz = new Quiz();
        System.out.println("Adding a new quiz");
        System.out.print("Enter quiz title: ");
        String quizTitle = scanner.nextLine();
        quiz.setTitle(quizTitle);
        System.out.println("Enter quiz course ID: ");
        int courseId = scanner.nextInt();
        quiz.setCourseId(courseId);
        System.out.println("Enter quiz duration: ");
        float duration = scanner.nextFloat();
        quiz.setDuration(duration);
        System.out.println("How many questions do you want to add?");
        int numQuestions = scanner.nextInt();
        scanner.nextLine();  
        List<Question> questions = new ArrayList<>();
        List<Question> allQuestions = elearningService.getAllQuestions();

        System.out.println("Available questions: ");
        for (Question question : allQuestions) {
            System.out.println(question);
        }
        for (int i = 0; i < numQuestions; i++) {
            System.out.println("Enter question id: ");
            int questionId = scanner.nextInt();
            scanner.nextLine();  
            Question question = elearningService.getQuestionById(questionId);

            if (question != null) {
                questions.add(question);
            } else {
                System.out.println("Question not found. Please try again.");
                i--;
            }
        }
        quiz.setQuestions(questions);
        try {
            elearningService.addQuiz(quiz);
            System.out.println("Quiz added successfully.");
        } catch (DAOException e) {
            System.out.println("Error adding quiz: " + e.getMessage());
        }
    }

    private static void addQuestion() {
        Question question = new Question();
        System.out.println("Adding a new question");
        System.out.print("Enter question: ");
        String content = scanner.nextLine();
        scanner.nextLine();  
        question.setContent(content);
        System.out.println("Enter options:");
        List<String> options = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter option " + (i + 1) + ": ");
            String option = scanner.nextLine();
            options.add(option);
        }
        question.setOptions(options);
        System.out.println("Enter correct option: ");
        int correctOption = scanner.nextInt();
        question.setCorrectOptionIndex(correctOption);

        System.out.println("Enter quiz ID: (optional)");
        int quizId = scanner.nextInt();
        if (quizId != 0) {
            question.setQuizId(quizId);
        }

        try {
            elearningService.addQuestion(question);
            System.out.println("Question added successfully.");
        } catch (DAOException e) {
            System.out.println("Error adding question: " + e.getMessage());
        }
    }
}