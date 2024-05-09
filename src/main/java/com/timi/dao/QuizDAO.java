package com.timi.dao;

import java.util.List;

import com.timi.exception.DAOException;
import com.timi.model.Quiz;

public interface QuizDAO {
    void addQuiz(Quiz quiz) throws DAOException;
    Quiz getQuizById(int quizId) throws DAOException;
    List<Quiz> getAllQuizzes() throws DAOException;
    List<Quiz> getQuizzesByCourseId(int courseId) throws DAOException;
    void updateQuiz(Quiz quiz) throws DAOException;
    void deleteQuiz(int quizId) throws DAOException;
}
