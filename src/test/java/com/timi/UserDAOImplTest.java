package com.timi;

import com.timi.dao.*;
import com.timi.dao.impl.*;
import com.timi.exception.DAOException;
import com.timi.exception.InvalidEmailException;
import com.timi.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImplTest {

    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        userDAO = new UserDAOImpl();
    }

    @Test
    @DisplayName("Add Student Test")
    void addUserTest() {
        User user = new Student("email", "username", "password", Level.BEGINNER, 0);
        assertDoesNotThrow(() -> userDAO.addUser(user));
    }

    @Test
    @DisplayName("Get User By Id Test")
    void getUserByIdTest() {
        int userId = 1; 
        assertDoesNotThrow(() -> {
            User user = userDAO.getUserById(userId);
            assertNotNull(user);
        });
    }

    @Test
    @DisplayName("Get All Users Test")
    void getAllUsersTest() {
        assertDoesNotThrow(() -> {
            List<User> users = userDAO.getAllUsers();
            assertNotNull(users);
            assertFalse(users.isEmpty());
        });
    }

    @Test
    @DisplayName("Update User Test")
    void updateUserTest() throws DAOException, InvalidEmailException, InterruptedException {
        String email = "test" + (int)(Math.random() * 1000) + "@gmail.com";
        int userId = (int)(Math.random() * 1000);
        User dummyUser = new Student(userId, email, "dummyusername", "dummypassword", Level.BEGINNER, 0);
        userDAO.addUser(dummyUser);
        String newEmail = "newTest" + (int)(Math.random() * 1000) + "@gmail.com";
        dummyUser.setEmail(newEmail);
        dummyUser.setUsername("newdummyusername");
        dummyUser.setPassword("newdummypassword");
        userDAO.updateUser(dummyUser);
        assertNotNull(userDAO.getUserById(userId));
        userDAO.deleteUser(userId);
    }

    @Test
    @DisplayName("Delete User Test")
    void deleteUserTest() throws DAOException {
        String email = "test" + (int)(Math.random() * 1000) + "@gmail.com";
        User dummyUser = new Student(email, "dummyusername", "dummypassword", Level.BEGINNER, 0);
        assertDoesNotThrow(() -> userDAO.addUser(dummyUser));
        int dummyUserId = userDAO.getAllUsers().get(userDAO.getAllUsers().size() - 1).getId();
        assertDoesNotThrow(() -> userDAO.deleteUser(dummyUserId));
        userDAO.deleteUser(dummyUserId);
        assertNull(userDAO.getUserById(dummyUserId));
    }
}
