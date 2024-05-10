# E-learning Application 📔

This project features an e-learning application with users being students, instructors and admins. Each course is packed with contend and a lot of quizzes for better learning. Through this project I consolidated advanced object-oriented programming concepts and applied design patterns to build a robust and scalable application.

**Tech Stack:** <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> <a href="https://www.mysql.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original-wordmark.svg" alt="mysql" width="40" height="40"/> </a> <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="40" height="40"/> </a>

## Features 🚀

### 1. Project Structure 🏗️

The project has many classes, interfaces and resources that are organized in packages.

- **Model:** Contains all the classes that represent the entities of the application.
- **Service:** Contains all the classes that provide services to the application.
- **Dao:** Contains all the classes that interact with the database.
- **Exception:** Contains all the custom exception classes.
- **Test:** Contains all the test classes, especially for the DAO classes.
- **Resources:** Contains all the resources, such as the database configuration file and the SQL script to create the database.

### 2. Database 🗃️

The application uses MySQL as the database. The database has 5 main tables: users, courses, questions, quizzes and applications, as well as linking tables (UserCourses, QuizAttempts, QuizAttemptQuestions). These were necessary to implement the many-to-many relationships between users and courses, quizzes and questions.

It was a challenge to implement the database considering the models designed in the application had lists of objects as attributes. To work with a relational database, I had to create linking tables to represent the relationships between lists of objects.

As a preview, this is the implementation of the addQuizAttempt method which inserts a new quiz attempt into the database, handling the many-to-many relationship between quizzes and questions:

```java
 public void addQuizAttempt(QuizAttempt quizAttempt) throws DAOException {
        Connection connection = dbConnection.getConnection();

        try {
            PreparedStatement ps = null;
            if (quizAttempt.getAttemptId() == 0) {
                ps = connection.prepareStatement("INSERT INTO QuizAttempts (userId, quizId, timestamp, score, durationAttempted) VALUES (?, ?, ?, ?, ?)");
            } else {
                ps = connection.prepareStatement("INSERT INTO QuizAttempts (attemptId, userId, quizId, timestamp, score, durationAttempted) VALUES (?, ?, ?, ?, ?, ?)");
                ps.setInt(1, quizAttempt.getAttemptId());
            }

            int cnt = (quizAttempt.getAttemptId() != 0) ? 1 : 0;
            ps.setInt(1 + cnt, quizAttempt.getUserId());
            ps.setInt(2 + cnt, quizAttempt.getQuizId());
            ps.setTimestamp(3 + cnt, Timestamp.valueOf(quizAttempt.getTimestamp()));
            ps.setInt(4 + cnt, quizAttempt.getScore());
            ps.setFloat(5 + cnt, quizAttempt.getDurationAttempted());
            ps.executeUpdate();
            ps.close();
            if (quizAttempt.getQuestionsAttempted() != null) {
                String insertQuizAttemptQuestionsQuery = "INSERT INTO QuizAttemptQuestions (attemptId, questionId, selectedOptionIndex) VALUES (?, ?, ?)";
                PreparedStatement psQuizAttemptQuestions = connection.prepareStatement(insertQuizAttemptQuestionsQuery);
                for (Question question : quizAttempt.getQuestionsAttempted()) {
                    psQuizAttemptQuestions.setInt(1, quizAttempt.getAttemptId());
                    psQuizAttemptQuestions.setInt(2, question.getQuestionId());
                    psQuizAttemptQuestions.setInt(3, question.getSelectedOptionIndex());
                    psQuizAttemptQuestions.executeUpdate();
                }
                psQuizAttemptQuestions.close();
            }

            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error adding quiz attempt", e);
        }
    }
```

### 3. OOP Concepts 🧩

The application is built using advanced object-oriented programming concepts, such as inheritance, polymorphism, encapsulation and abstraction. For example, the User class is the superclass of the Student, Instructor and Admin classes. The User class has a list of courses, which is a list of Course objects. The Course class has a list of quizzes, which is a list of Quiz objects. The Quiz class has a list of questions, which is a list of Question objects.

I used the concept of abstract classes as well, for example, the User class is an abstract class, and the Student, Instructor and Admin classes are concrete classes that extend the User class.

For administering the objects in the application I used multiple collection interfaces, such as List and Queue. For example, the User class has a list of courses, which is a List of Course objects and the Applications are stored and manipulated in Queues.

Enums were used to represent constants in the application, such as the Application status (PENDING, APPROVED, REJECTED) or the User role (STUDENT, INSTRUCTOR, ADMIN).

### 4. Design Patterns 🎨

Because of the database integration in the application, I used the Data Access Object (DAO) design pattern to separate the data access logic from the business logic. The DAO classes are responsible for interacting with the database.

For the connection to the database, I used the Singleton design pattern to ensure that only one instance of the database connection is created and shared across the application.

### 5. Exception Handling ⚠️

Keeping in mind the stability of the application, I implemented custom exception classes to handle the exceptions that may occur during the execution of the application. The DAOException class is used to handle exceptions that occur during the interaction with the database and the email validation exceptions are used to handle exceptions that occur during the validation of the email address.

Adding to this, almost every action is within a try-catch block to ensure that the application does not crash if an exception occurs.

### 6. Services 🛠️

The application features 3 services:

- **AuditingService:** Logs the actions performed by the users in the application in a csv file (audit_log.csv).
- **DataService:** Retrieves all data from the database at system startup so the data will be automatically loaded.
- **ElearningService** This is the main service that is used to interact with all the objects in the application and execute the business logic.

### 7. Testing 🧪

The application has a test package that contains test classes for the DAO classes. The tests are written using JUnit and test the CRUD operations of the DAO classes.

All tests were successful and the application is working as expected.

### 8. Main Class 🚀

The main class contains a demo of the application calling the service's methods. It loads the data from the database using the DataService and displays the data in the console.

## Conclusion

This project was a great opportunity to consolidate my knowledge of advanced object-oriented programming concepts and design patterns. I learned a lot about the importance of organizing the code in packages and classes and how to implement the many-to-many relationships in a relational database considering the models designed in the application.
