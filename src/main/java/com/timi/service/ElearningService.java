package com.timi.service;

import java.util.List;
import java.util.Queue;

import com.timi.exception.DAOException;
import com.timi.model.*;

public interface ElearningService {

    List<Course> getAllCourses() throws DAOException;
    List<Quiz> getAllQuizzes() throws DAOException;
    List<Question> getAllQuestions() throws DAOException;
    List<QuizAttempt> getAllQuizAttempts() throws DAOException;
    List<User> getAllUsers() throws DAOException;
    List<Course> getUserCourses(int userId) throws DAOException;
    Queue<Application> getAllApplications() throws DAOException; 
    Queue<Application> getPendingApplications() throws DAOException;
}
