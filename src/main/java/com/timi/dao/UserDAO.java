package com.timi.dao;

import com.timi.exception.DAOException;
import com.timi.exception.InvalidEmailException;
import com.timi.model.User;
import java.util.List;

public interface UserDAO {
    
    void addUser(User user) throws DAOException;
    User getUserById(int id) throws DAOException;
    List<User> getAllUsers() throws DAOException;
    void updateUser(User user) throws DAOException, InvalidEmailException;
    void deleteUser(int id) throws DAOException;
    void addCourseToUser(int userId, int courseId) throws DAOException;

    User authenticateUser(String email, String password) throws DAOException;
    void registerUser(String email, String username, String password) throws DAOException;
}