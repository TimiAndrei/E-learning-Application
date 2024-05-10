package com.timi.dao.impl;

import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;

import com.timi.dao.ApplicationDAO;
import com.timi.dao.DatabaseConnection;
import com.timi.exception.DAOException;
import com.timi.model.Application;

public class ApplicationDAOImpl implements ApplicationDAO {

    private DatabaseConnection dbConnection;

    public ApplicationDAOImpl() {
       dbConnection = DatabaseConnection.getInstance();
    }

    @Override
    public void addApplication(Application application) throws DAOException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = null;
            if (application.getApplicationId() == 0) {
                ps = connection.prepareStatement("INSERT INTO Applications (userId, courseId, applicationLetter, status, applicationDate) VALUES (?, ?, ?, ?, ?)");
            } else {
                ps = connection.prepareStatement("INSERT INTO Applications (applicationId, userId, courseId, applicationLetter, status, applicationDate) VALUES (?, ?, ?, ?, ?, ?)");
                ps.setInt(1, application.getApplicationId());
            }

            int cnt = (application.getApplicationId() != 0) ? 1 : 0;
            ps.setInt(1 + cnt, application.getUserId());
            ps.setInt(2 + cnt, application.getCourseId());
            ps.setString(3 + cnt, application.getApplicationLetter());
            ps.setString(4 + cnt, application.getStatus());
            ps.setTimestamp(5 + cnt, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new DAOException("Error adding application", e);
        }
    }

    @Override
    public Queue<Application> getAllApplications() throws DAOException {
        Queue<Application> applications = new LinkedList<>();
        Connection connection = dbConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Applications");
            while (rs.next()) {
                Application application = extractApplicationFromResultSet(rs);
                applications.offer(application);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new DAOException("Error fetching applications", e);
        }
        return applications;
    }

    private Application extractApplicationFromResultSet(ResultSet rs) throws SQLException {
        return new Application(rs.getInt("applicationId"), rs.getInt("userId"), rs.getInt("courseId"), rs.getString("applicationLetter"), rs.getString("status"), rs.getTimestamp("applicationDate"));
    }

    @Override
    public Queue<Application> getApplicationsByUserId(int userId) throws DAOException {
        Queue<Application> applications = new LinkedList<>();
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Applications WHERE userId = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Application application = extractApplicationFromResultSet(rs);
                applications.offer(application);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new DAOException("Error fetching applications by userId", e);
        }
        return applications;
    }

    @Override
    public Queue<Application> getApplicationsByCourseId(int courseId) throws DAOException {
        Queue<Application> applications = new LinkedList<>();
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Applications WHERE courseId = ?");
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Application application = extractApplicationFromResultSet(rs);
                applications.offer(application);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new DAOException("Error fetching applications by courseId", e);
        }
        return applications;
    }

    @Override
    public void updateApplicationStatus(int applicationId, String status) throws DAOException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Applications SET status = ? WHERE applicationId = ?");
            ps.setString(1, status);
            ps.setInt(2, applicationId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new DAOException("Error updating application status", e);
        }
    }

    @Override
    public void updateApprovalDate(int applicationId) throws DAOException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Applications SET approvalDate = ? WHERE applicationId = ?");
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setInt(2, applicationId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new DAOException("Error updating approval date", e);
        }
    }

    @Override
    public void deleteApplication(int applicationId) throws DAOException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Applications WHERE applicationId = ?");
            ps.setInt(1, applicationId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new DAOException("Error deleting application", e);
        }
    }
}
