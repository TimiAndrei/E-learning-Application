package com.timi.dao;

import com.timi.model.Application;
import com.timi.exception.DAOException;
import java.util.List;


public interface ApplicationDAO {
    void addApplication(Application application) throws DAOException;

    List<Application> getAllApplications() throws DAOException;

    List<Application> getApplicationsByUserId(int userId) throws DAOException;

    List<Application> getApplicationsByCourseId(int courseId) throws DAOException;

    void updateApplicationStatus(int applicationId, String status) throws DAOException;

    void updateApprovalDate(int applicationId) throws DAOException;

    void deleteApplication(int applicationId) throws DAOException;
}
