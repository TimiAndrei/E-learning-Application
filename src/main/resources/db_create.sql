DROP TABLE IF EXISTS Applications, Users, Courses, Questions, Quizzes, UserCourses, QuizAttempts, QuizAttemptQuestions;

CREATE TABLE IF NOT EXISTS Users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('STUDENT', 'INSTRUCTOR', 'ADMIN'),
    level ENUM('BEGINNER', 'INTERMEDIATE', 'EXPERT'), -- for student
    points INT, -- for student
    department VARCHAR(255), -- for instructor
    dateOfEmployment DATE, -- for instructor
    telephone VARCHAR(255) -- for admin
);

CREATE TABLE IF NOT EXISTS Courses(
    courseId INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    instructor INT, 
    level ENUM('BEGINNER', 'INTERMEDIATE', 'EXPERT'),
    price INT,
    duration INT,
    category VARCHAR(255),
    FOREIGN KEY (instructor) REFERENCES Users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Quizzes (
    quizId INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    courseId INT NOT NULL,
    duration FLOAT NOT NULL, -- minutes
    FOREIGN KEY (courseId) REFERENCES courses(courseId) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Questions (
    questionId INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(255) NOT NULL,
    options TEXT NOT NULL, -- will be stored as JSON
    correctOptionIndex INT NOT NULL,
    selectedOptionIndex INT, -- for quiz attempts
    quizId INT, -- for quiz questions
    FOREIGN KEY (quizId) REFERENCES quizzes(quizId) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS UserCourses (
    userId INT,
    courseId INT,
    PRIMARY KEY (userId, courseId),
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (courseId) REFERENCES courses(courseId) ON DELETE CASCADE
); -- for students and instructors

CREATE TABLE IF NOT EXISTS QuizAttempts (
    attemptId INT PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    quizId INT NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    score INT NOT NULL,
    durationAttempted FLOAT NOT NULL,
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (quizId) REFERENCES quizzes(quizId) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS QuizAttemptQuestions (
    attemptId INT,
    questionId INT,
    selectedOptionIndex INT,
    PRIMARY KEY (attemptId, questionId),
    FOREIGN KEY (attemptId) REFERENCES quizAttempts(attemptId) ON DELETE CASCADE,
    FOREIGN KEY (questionId) REFERENCES questions(questionId) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Applications (
    applicationId INT PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    courseId INT NOT NULL,
    applicationLetter TEXT NOT NULL,
    status ENUM('PENDING', 'APPROVED', 'REJECTED'),
    applicationDate TIMESTAMP NOT NULL,
    approvalDate TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (courseId) REFERENCES courses(courseId) ON DELETE CASCADE
);

INSERT INTO Users (email, username, password, role, telephone) VALUES ('admin@test.com', 'admin', 'admin', 'ADMIN', '1234567890');
INSERT INTO Users (email, username, password, role, level, points) VALUES ('student@test.com', 'student', 'student', 'STUDENT', 'BEGINNER', 10);
INSERT INTO Users (email, username, password, role, department, dateOfEmployment ) VALUES ('instructor@unibuc.ro', 'instructor', 'instructor', 'INSTRUCTOR', 'Computer Science', '2020-10-01');
INSERT INTO Courses (title, description, instructor, level, price, duration, category) VALUES ('Java for beginners', 'Learn Java from scratch', 3, 'BEGINNER', 100, 30, 'JAVA');
INSERT INTO Courses (title, description, instructor, level, price, duration, category) VALUES ('Java for experts', 'Advanced Java concepts', 3, 'EXPERT', 200, 30, 'JAVA');
INSERT INTO Quizzes (title, courseId, duration) VALUES ('Java Basics Quiz', 1, 10);
INSERT INTO Questions (content, options, correctOptionIndex, quizId) VALUES ('What is Java?', '["Programming Language", "Database", "Operating System", "Web Browser"]', 0, 1);
INSERT INTO Questions (content, options, correctOptionIndex, quizId) VALUES ('What is the latest version of Java?', '["Java 8", "Java 9", "Java 10", "Java 11"]', 3, 1);
INSERT INTO Questions (content, options, correctOptionIndex, quizId) VALUES ('What is the output of System.out.println(1 + 2 + "3")?', '["33", "6", "123", "5"]', 2, 1);
INSERT INTO Quizzes (title, courseId, duration) VALUES ('Java Design Patterns Quiz', 2, 25);
INSERT INTO Questions (content, options, correctOptionIndex, quizId) VALUES ('What is a design pattern?', '["A reusable solution to a common problem", "A new programming language", "A new IDE", "A new operating system"]', 0, 2);
INSERT INTO Questions (content, options, correctOptionIndex, quizId) VALUES ('What is the Singleton pattern?', '["A pattern that restricts the instantiation of a class to one object", "A pattern that allows multiple instances of a class", "A pattern that restricts the instantiation of a class to two objects", "A pattern that restricts the instantiation of a class to three objects"]', 0, 2);
INSERT INTO Questions (content, options, correctOptionIndex, quizId) VALUES ('What is the Observer pattern?', '["A pattern that allows a class to publish changes to its state", "A pattern that restricts the instantiation of a class to one object", "A pattern that restricts the instantiation of a class to two objects", "A pattern that restricts the instantiation of a class to three objects"]', 0, 2);
INSERT INTO UserCourses (userId, courseId) VALUES (2, 1);
INSERT INTO UserCourses (userId, courseId) VALUES (3, 2);
INSERT INTO Applications (userId, courseId, applicationLetter, status, applicationDate) VALUES (1, 1, 'I want to learn Java', 'PENDING', NOW());
INSERT INTO QuizAttempts (userId, quizId, timestamp, score, durationAttempted) VALUES (1, 1, NOW(), 40, 22.35);
INSERT INTO QuizAttemptQuestions (attemptId, questionId, selectedOptionIndex) VALUES (1, 1, 0);
INSERT INTO QuizAttemptQuestions (attemptId, questionId, selectedOptionIndex) VALUES (1, 2, 3);
INSERT INTO QuizAttemptQuestions (attemptId, questionId, selectedOptionIndex) VALUES (1, 3, 2);


COMMIT;

