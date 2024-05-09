package com.timi;

import com.timi.dao.ApplicationDAO;
import com.timi.dao.impl.ApplicationDAOImpl;
import com.timi.model.Application;
import java.util.Queue;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class ApplicationDAOImplTest {

    private ApplicationDAO applicationDAO;

    @BeforeEach
    void setUp() {
        applicationDAO = new ApplicationDAOImpl();
    }

    @Test
    @DisplayName("Add Application Test")
    void addApplicationTest() {
        Application application = new Application(1, 1, "Application letter", "PENDING", null);
        assertDoesNotThrow(() -> applicationDAO.addApplication(application));
    }

    @Test
    @DisplayName("Get All Applications Test")
    void getAllApplicationsTest() {
        Queue<Application> applications = assertDoesNotThrow(applicationDAO::getAllApplications);
        assertNotNull(applications);
    }

    @Test
    @DisplayName("Get Applications By User Id Test")
    void getApplicationsByUserIdTest() {
        int userId = 1;
        Queue<Application> applications = assertDoesNotThrow(() -> applicationDAO.getApplicationsByUserId(userId));
        assertNotNull(applications);
    }

    @Test
    @DisplayName("Get Applications By Course Id Test")
    void getApplicationsByCourseIdTest() {
        int courseId = 1;
        Queue<Application> applications = assertDoesNotThrow(() -> applicationDAO.getApplicationsByCourseId(courseId));
        assertNotNull(applications);
    }

    @Test
    @DisplayName("Update Application Status Test")
    void updateApplicationStatusTest() {
        int applicationId = 1;
        String status = "APPROVED";
        assertDoesNotThrow(() -> applicationDAO.updateApplicationStatus(applicationId, status));
    }

    @Test
    @DisplayName("Update Approval Date Test")
    void updateApprovalDateTest() {
        int applicationId = 1;
        assertDoesNotThrow(() -> applicationDAO.updateApprovalDate(applicationId));
    }

    @Test
    @DisplayName("Delete Application Test")
    void deleteApplicationTest() {
        int applicationId = 1;
        assertDoesNotThrow(() -> applicationDAO.deleteApplication(applicationId));
    }
}
