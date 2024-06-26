package com.timi.dao.impl;

import com.timi.model.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.timi.dao.DatabaseConnection;
import com.timi.dao.QuestionDAO;
import com.timi.exception.DAOException;
import com.timi.service.AuditingService;
import com.timi.service.impl.AuditingServiceImpl;


public class QuestionDAOImpl implements QuestionDAO {

    private DatabaseConnection dbConnection;
    private AuditingService auditingService;

    public QuestionDAOImpl() {
        dbConnection = DatabaseConnection.getInstance();
        auditingService = AuditingServiceImpl.getInstance();
    }

    @Override
    public void addQuestion(Question question) throws DAOException {
        Connection connection = dbConnection.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Questions (content, options, correctOptionIndex, selectedOptionIndex, quizId) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, question.getContent());
            // Convert options list to JSON string and set it to the PreparedStatement
            String optionsJson = convertOptionsToJson(question.getOptions());
            ps.setString(2, optionsJson);
            ps.setInt(3, question.getCorrectOptionIndex());
            ps.setInt(4, question.getSelectedOptionIndex());
            ps.setInt(5, question.getQuizId());
            ps.executeUpdate();
            ps.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error adding question", e);
        }
    }

    private String convertOptionsToJson(List<String> options) {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<String>>(){}.getType();
        return gson.toJson(options, listType);
    }

    @Override
    public Question getQuestionById(int questionId) throws DAOException {
        Connection connection = dbConnection.getConnection();
        Question question = null;

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Questions WHERE questionId = ?");
            ps.setInt(1, questionId);
            question = new Question();
            ps.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error getting question by ID", e);
        }

        return question;

    }

    @Override
    public List<Question> getAllQuestions() throws DAOException {
        Connection connection = dbConnection.getConnection();
        List<Question> questions = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Questions");
            
            // Execute the query to obtain the result set
            ResultSet rs = ps.executeQuery();
            
            // Convert options JSON string to list and set it to the Question object
            Gson gson = new GsonBuilder().create();

            while (rs.next()) {
                Question question = new Question();
                question.setQuestionId(rs.getInt("questionId"));
                question.setContent(rs.getString("content"));
                String optionsJson = rs.getString("options");
                Type listType = new TypeToken<List<String>>(){}.getType();
                List<String> options = gson.fromJson(optionsJson, listType);
                question.setOptions(options);
                question.setCorrectOptionIndex(rs.getInt("correctOptionIndex"));
                question.setSelectedOptionIndex(rs.getInt("selectedOptionIndex"));
                question.setQuizId(rs.getInt("quizId"));
                questions.add(question);
            }

            rs.close();
            ps.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error getting all questions", e);
        }

        return questions;
    }

    @Override
    public List<Question> getQuestionsByQuizId(int quizId) throws DAOException {
        Connection connection = dbConnection.getConnection();
        List<Question> questions = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Questions WHERE quizId = ?");
            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();
            Gson gson = new GsonBuilder().create();

            while (rs.next()) {
                Question question = new Question();
                question.setQuestionId(rs.getInt("questionId"));
                question.setContent(rs.getString("content"));
                String optionsJson = rs.getString("options");
                Type listType = new TypeToken<List<String>>(){}.getType();
                List<String> options = gson.fromJson(optionsJson, listType);
                question.setOptions(options);
                question.setCorrectOptionIndex(rs.getInt("correctOptionIndex"));
                question.setSelectedOptionIndex(rs.getInt("selectedOptionIndex"));
                questions.add(question);
            }

            rs.close();
            ps.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error getting questions by quiz ID", e);
        }
        return questions;
    }

    @Override
    public void updateQuestion(Question question) throws DAOException {
        Connection connection = dbConnection.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Questions SET content = ?, options = ?, correctOptionIndex = ?, selectedOptionIndex=?, quizId=? WHERE questionId = ?");
            ps.setString(1, question.getContent());
            // Convert options list to JSON string and set it to the PreparedStatement
            String optionsJson = convertOptionsToJson(question.getOptions());
            ps.setString(2, optionsJson);
            ps.setInt(3, question.getCorrectOptionIndex());
            ps.setInt(4, question.getSelectedOptionIndex());
            ps.setInt(5, question.getQuizId());
            ps.setInt(6, question.getQuestionId());
            ps.executeUpdate();
            ps.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error updating question", e);
        }
    }

    @Override
    public void deleteQuestion(int questionId) throws DAOException {
    
        Connection connection = dbConnection.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Questions WHERE questionId = ?");
            ps.setInt(1, questionId);
            ps.executeUpdate();
            ps.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error deleting question", e);
        }
    }
}
