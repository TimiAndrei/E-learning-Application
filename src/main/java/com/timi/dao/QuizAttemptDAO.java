package com.timi.dao;

import java.util.List;

import com.timi.exception.DAOException;
import com.timi.model.Question;
import com.timi.model.QuizAttempt;

public interface QuizAttemptDAO {
    void addQuizAttempt(QuizAttempt quizAttempt) throws DAOException;
    QuizAttempt getQuizAttemptById(int attemptId) throws DAOException;
    List<QuizAttempt> getAllQuizAttempts() throws DAOException;
    List<QuizAttempt> getQuizAttemptsByUserId(int userId) throws DAOException;
    List<QuizAttempt> getQuizAttemptsByQuizId(int quizId) throws DAOException;
    List<QuizAttempt> getQuizAttemptsByUserIdAndQuizId(int userId, int quizId) throws DAOException;
    List<Question> getQuestionsByAttemptId(int attemptId) throws DAOException;
    void updateQuizAttempt(QuizAttempt quizAttempt) throws DAOException;
    void deleteQuizAttempt(int attemptId) throws DAOException;
}
