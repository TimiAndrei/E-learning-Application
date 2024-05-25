package com.timi.service;

import java.util.List;
import java.util.Queue;

import com.timi.exception.DAOException;
import com.timi.exception.InvalidEmailException;
import com.timi.model.*;

public interface ElearningService {

    List<Course> getAllCourses() throws DAOException;
    List<Quiz> getAllQuizzes() throws DAOException;
    List<Quiz> getQuizzesByCourse(int courseId) throws DAOException;
    List<QuizAttempt> getUserQuizAttempts(int userId) throws DAOException;
    List<Question> getAllQuestions() throws DAOException;
    List<QuizAttempt> getAllQuizAttempts() throws DAOException;
    List<User> getAllUsers() throws DAOException;
    List<Course> getUserCourses(int userId) throws DAOException;
    Queue<Application> getAllApplications() throws DAOException; 
    Queue<Application> getPendingApplications() throws DAOException;
    Quiz getQuizById(int id) throws DAOException;
    User getUserById(int id) throws DAOException;
    Question getQuestionById(int id) throws DAOException;
    Course getCourseByName(String name) throws DAOException;

    User authenticateUser(String email, String password) throws DAOException;
    void registerUser(String email, String username, String password) throws DAOException;
    void updateUser(User user) throws DAOException, InvalidEmailException;
    void deleteUser(int id) throws DAOException;
    void addCourse(Course course) throws DAOException;
    void updateCourse(Course course) throws DAOException;
    void deleteCourse(int id) throws DAOException;
    void addQuiz(Quiz quiz) throws DAOException;
    void addQuestion(Question question) throws DAOException;
    void editQuestion(Question question) throws DAOException;
    void deleteQuestion(int id) throws DAOException;
    void searchCoursesByName(String name) throws DAOException;
    void applyForCourse(Application application) throws DAOException;
    void takeQuiz(QuizAttempt quizAttempt) throws DAOException;
    
}
