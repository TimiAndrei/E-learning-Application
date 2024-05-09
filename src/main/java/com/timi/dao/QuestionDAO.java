package com.timi.dao;

import java.util.List;

import com.timi.exception.DAOException;
import com.timi.model.Question;

public interface QuestionDAO {
    void addQuestion(Question question) throws DAOException;
    Question getQuestionById(int questionId) throws DAOException;
    List<Question> getAllQuestions() throws DAOException;
    void updateQuestion(Question question) throws DAOException;
    void deleteQuestion(int questionId) throws DAOException;
}