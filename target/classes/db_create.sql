DROP TABLE IF EXISTS Users, Courses, Course_categories, Questions, Quizzes, Quiz_questions, User_courses, QuizAttempts, QuizAttemptQuestions;

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
    FOREIGN KEY (instructor) REFERENCES Users(id)
);

CREATE TABLE IF NOT EXISTS Course_categories (
    courseId INT,
    category ENUM('JAVA', 'PYTHON', 'JAVASCRIPT', 'C', 'CPLUSPLUS', 'CSHARP', 'RUBY', 'SWIFT', 'KOTLIN', 'PHP', 'HTML', 'CSS', 'SQL', 'NO_SQL', 'REACT', 'ANGULAR', 'VUE', 'NODE_JS', 'DJANGO', 'SPRING', 'FLASK'),
    PRIMARY KEY (courseId, category),
    FOREIGN KEY (courseId) REFERENCES courses(courseId)
);

CREATE TABLE IF NOT EXISTS Questions (
    questionId INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(255) NOT NULL,
    options TEXT NOT NULL, -- will be stored as JSON
    correctOptionIndex INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Quizzes (
    quizId INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    courseId INT NOT NULL,
    FOREIGN KEY (courseId) REFERENCES courses(courseId),
    duration FLOAT NOT NULL -- minutes
);

CREATE TABLE IF NOT EXISTS Quiz_questions (
    quizId INT,
    questionId INT,
    PRIMARY KEY (quizId, questionId),
    FOREIGN KEY (quizId) REFERENCES quizzes(quizId),
    FOREIGN KEY (questionId) REFERENCES questions(questionId)
);

CREATE TABLE IF NOT EXISTS User_courses (
    userId INT,
    courseId INT,
    PRIMARY KEY (userId, courseId),
    FOREIGN KEY (userId) REFERENCES users(id),
    FOREIGN KEY (courseId) REFERENCES courses(courseId)
); -- for students and instructors

CREATE TABLE IF NOT EXISTS QuizAttempts (
    attemptId INT PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    quizId INT NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    score INT NOT NULL,
    durationAttempted FLOAT NOT NULL,
    FOREIGN KEY (userId) REFERENCES users(id),
    FOREIGN KEY (quizId) REFERENCES quizzes(quizId)
);

CREATE TABLE IF NOT EXISTS QuizAttemptQuestions (
    attemptId INT,
    questionId INT,
    selectedOptionIndex INT,
    PRIMARY KEY (attemptId, questionId),
    FOREIGN KEY (attemptId) REFERENCES quizAttempts(attemptId),
    FOREIGN KEY (questionId) REFERENCES questions(questionId)
);

COMMIT;

