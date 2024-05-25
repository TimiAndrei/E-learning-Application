package com.timi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.timi.service.ElearningService;
import com.timi.service.DataService;
import com.timi.service.impl.ElearningServiceImpl;
import com.timi.service.impl.DataServiceImpl;
import com.timi.exception.DAOException;
import com.timi.exception.InvalidEmailException;
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

    private static void showMainMenu() throws DAOException, InvalidEmailException {
        if (loggedInUser.getRole().equals("ADMIN")) {
            showAdminMenu();
        } else if (loggedInUser.getRole().equals("INSTRUCTOR")) {
            showInstructorMenu();
        } else if (loggedInUser.getRole().equals("STUDENT")) {
            showStudentMenu();
        }
    }

    private static void showAdminMenu() throws DAOException, InvalidEmailException {
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

    private static void showInstructorMenu() throws DAOException {
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
                    loggedInUser = null;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showStudentMenu() throws DAOException {
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
                    loggedInUser = null;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
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
        application.setUserId(loggedInUser.getId());
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
            List<Course> courses = elearningService.getUserCourses(loggedInUser.getId());
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
        System.out.println("Good luck " + loggedInUser.getUsername() + "!");
        System.out.println("Timer starts now!");
        LocalDateTime startTime = LocalDateTime.now();
        Quiz quiz = elearningService.getQuizById(quizId);
        quizAttempt.setQuizId(quizId);
        quizAttempt.setUserId(loggedInUser.getId());

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
            List<QuizAttempt> quizAttempts = elearningService.getUserQuizAttempts(loggedInUser.getId());
            for (QuizAttempt quizAttempt : quizAttempts) {
                System.out.println(quizAttempt);
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
                System.out.println(quiz);
            }
        } catch (DAOException e) {
            System.out.println("Error getting quizzes: " + e.getMessage());
        }
    }

}
