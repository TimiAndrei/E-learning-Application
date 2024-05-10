DROP TABLE IF EXISTS Applications, Users, Courses, Course_categories, Questions, Quizzes, User_courses, QuizAttempts, QuizAttemptQuestions;

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
    FOREIGN KEY (instructor) REFERENCES Users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Course_categories (
    courseId INT,
    category ENUM('JAVA', 'PYTHON', 'JAVASCRIPT', 'C', 'CPLUSPLUS', 'CSHARP', 'RUBY', 'SWIFT', 'KOTLIN', 'PHP', 'HTML', 'CSS', 'SQL', 'NO_SQL', 'REACT', 'ANGULAR', 'VUE', 'NODE_JS', 'DJANGO', 'SPRING', 'FLASK'),
    PRIMARY KEY (courseId, category),
    FOREIGN KEY (courseId) REFERENCES courses(courseId) ON DELETE CASCADE
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

CREATE TABLE IF NOT EXISTS User_courses (
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
INSERT INTO Courses (title, description, instructor, level, price, duration) VALUES ('Java for beginners', 'Learn Java from scratch', 1, 'BEGINNER', 100, 30);
INSERT INTO Course_categories (courseId, category) VALUES (1, 'JAVA');
INSERT INTO Quizzes (title, courseId, duration) VALUES ('Java Quiz', 1, 10);
INSERT INTO Questions (content, options, correctOptionIndex, quizId) VALUES ('What is Java?', '["Programming Language", "Database", "Operating System", "Web Browser"]', 0, 1);
INSERT INTO User_courses (userId, courseId) VALUES (1, 1);
INSERT INTO Applications (userId, courseId, applicationLetter, status, applicationDate) VALUES (1, 1, 'I want to learn Java', 'PENDING', NOW());
INSERT INTO QuizAttempts (userId, quizId, timestamp, score, durationAttempted) VALUES (1, 1, NOW(), 40, 22.35);
INSERT INTO QuizAttemptQuestions (attemptId, questionId, selectedOptionIndex) VALUES (1, 1, 0);


COMMIT;

