package com.timi.dao;

import com.timi.exception.DAOException;
import com.timi.model.Application;
import java.util.Queue;

public interface ApplicationDAO {
    void addApplication(Application application) throws DAOException;

    Queue<Application> getAllApplications() throws DAOException;

    Queue<Application> getApplicationsByUserId(int userId) throws DAOException;

    Queue<Application> getApplicationsByCourseId(int courseId) throws DAOException;

    Queue<Application> getPendingApplications() throws DAOException;

    void updateApplicationStatus(int applicationId, String status) throws DAOException;

    void updateApprovalDate(int applicationId) throws DAOException;

    void deleteApplication(int applicationId) throws DAOException;
}
