package com.timi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.timi.dao.DatabaseConnection;
import com.timi.dao.QuizDAO;
import com.timi.exception.DAOException;
import com.timi.model.Quiz;

public class QuizDAOImpl implements QuizDAO{
                                
        private DatabaseConnection dbConnection;
    
        public QuizDAOImpl() {
            dbConnection = DatabaseConnection.getInstance();
        }

        @Override
        public void addQuiz(Quiz quiz) throws DAOException {
            Connection connection = dbConnection.getConnection();
    
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO Quizzes (title, courseId, duration) VALUES (?, ?, ?)");
                ps.setString(1, quiz.getTitle());
                ps.setInt(2, quiz.getCourseId());
                ps.setFloat(3, quiz.getDuration());
                ps.executeUpdate();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error adding quiz", e);
            }
        }

        @Override
        public Quiz getQuizById(int quizId) throws DAOException {
            Connection connection = dbConnection.getConnection();
            Quiz quiz = null;
    
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Quizzes WHERE quizId = ?");
                ps.setInt(1, quizId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    quiz = new Quiz(rs.getInt("quizId"), rs.getString("title"), rs.getInt("courseId"), rs.getFloat("duration"));
                }
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error getting quiz", e);
            }
    
            return quiz;
        }

        @Override
        public List<Quiz> getAllQuizzes() throws DAOException {
            Connection connection = dbConnection.getConnection();
            List<Quiz> quizzes = new ArrayList<>();
    
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Quizzes");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = new Quiz(rs.getInt("quizId"), rs.getString("title"), rs.getInt("courseId"), rs.getFloat("duration"));
                    quizzes.add(quiz);
                }
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error getting all quizzes", e);
            }
    
            return quizzes;
        }

        @Override
        public List<Quiz> getQuizzesByCourseId(int courseId) throws DAOException {
            Connection connection = dbConnection.getConnection();
            List<Quiz> quizzes = new ArrayList<>();
    
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Quizzes WHERE courseId = ?");
                ps.setInt(1, courseId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = new Quiz(rs.getInt("quizId"), rs.getString("title"), rs.getInt("courseId"), rs.getFloat("duration"));
                    quizzes.add(quiz);
                }
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error getting quizzes by course ID", e);
            }
    
            return quizzes;
        }

        @Override
        public void updateQuiz(Quiz quiz) throws DAOException {
            Connection connection = dbConnection.getConnection();
    
            try {
                PreparedStatement ps = connection.prepareStatement("UPDATE Quizzes SET title = ?, courseId = ?, duration = ? WHERE quizId = ?");
                ps.setString(1, quiz.getTitle());
                ps.setInt(2, quiz.getCourseId());
                ps.setFloat(3, quiz.getDuration());
                ps.setInt(4, quiz.getQuizId());
                ps.executeUpdate();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error updating quiz", e);
            }
        }

        @Override
        public void deleteQuiz(int quizId) throws DAOException {
            Connection connection = dbConnection.getConnection();
    
            try {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM Quizzes WHERE quizId = ?");
                ps.setInt(1, quizId);
                ps.executeUpdate();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error deleting quiz", e);
            }
        }
    
}
