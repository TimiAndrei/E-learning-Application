package com.timi.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.timi.dao.CourseDAO;
import com.timi.dao.DatabaseConnection;
import com.timi.exception.DAOException;
import com.timi.model.Category;
import com.timi.model.Course;
import com.timi.model.Level;
import com.timi.service.AuditingService;
import com.timi.service.impl.AuditingServiceImpl;

public class CourseDAOImpl implements CourseDAO{

    private DatabaseConnection dbConnection;
    private AuditingService auditingService;

    public CourseDAOImpl() {
        dbConnection = DatabaseConnection.getInstance();
        auditingService = AuditingServiceImpl.getInstance();
    }

    @Override
    public void addCourse(Course course) throws DAOException {
        Connection connection = dbConnection.getConnection();

        try {
            PreparedStatement ps = null;
            if (course.getCourseId() == 0) {
                ps = connection.prepareStatement("INSERT INTO Courses (title, description, instructor, level, price, duration, category) VALUES (?, ?, ?, ?, ?, ?, ?)");
            } else {
                ps = connection.prepareStatement("INSERT INTO Courses (courseId, title, description, instructor, level, price, duration, category) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setInt(1, course.getCourseId());
            }

            int cnt = (course.getCourseId() != 0) ? 1 : 0;
            ps.setString(1 + cnt, course.getTitle());
            ps.setString(2 + cnt, course.getDescription());
            ps.setInt(3 + cnt, course.getInstructorId());
            ps.setString(4 + cnt, course.getLevel().toString());
            ps.setDouble(5 + cnt, course.getPrice());
            ps.setInt(6 + cnt, course.getDuration());
            ps.setString(7 + cnt, course.getCategory().toString());
            ps.executeUpdate();
            ps.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error adding course", e);
        }
    }

    private Course extractCourseFromResultSet(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setCourseId(rs.getInt("courseId"));
        course.setTitle(rs.getString("title"));
        course.setDescription(rs.getString("description"));
        course.setInstructor(rs.getInt("instructor"));
        course.setPrice(rs.getDouble("price"));
        course.setDuration(rs.getInt("duration"));
        String levelString = rs.getString("level");
        String categoryString = rs.getString("category");
        Level level = Level.valueOf(levelString);
        Category category = Category.valueOf(categoryString);
        course.setLevel(level);
        course.setCategory(category);
        return course;
    }

    @Override
    public Course getCourseById(int courseId) throws DAOException {
        Connection connection = dbConnection.getConnection();
        Course course = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Courses WHERE courseId = ?");
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                course = extractCourseFromResultSet(rs);
            }
            rs.close();
            ps.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error getting course", e);
        }
        return course;
    }

    @Override
    public List<Course> getAllCourses() throws DAOException {

        Connection connection = dbConnection.getConnection();
        List<Course> courses = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Courses");
            while (rs.next()) {
                Course course = extractCourseFromResultSet(rs);
                courses.add(course);
            }
            rs.close();
            stmt.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error fetching courses", e);
        }
        return courses;
    }

    @Override
    public List<Course> getUserCourses(int userId) throws DAOException {

        Connection connection = dbConnection.getConnection();
        List<Course> courses = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Courses WHERE courseId IN (SELECT courseId FROM UserCourses WHERE userId = ?)");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = extractCourseFromResultSet(rs);
                courses.add(course);
            }
            rs.close();
            ps.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error fetching user courses", e);
        }
        return courses;
    }


    @Override
    public List<Course> getCoursesByInstructor(String instructor) throws DAOException {

        Connection connection = dbConnection.getConnection();
        List<Course> courses = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Courses WHERE instructor = ?");
            ps.setString(1, instructor);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = extractCourseFromResultSet(rs);
                courses.add(course);
            }
            rs.close();
            ps.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error fetching courses by instructor", e);
        }
        return courses;
    }

    @Override
    public List<Course> getCoursesByLevel(String level) throws DAOException {

        Connection connection = dbConnection.getConnection();
        List<Course> courses = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Courses WHERE level = ?");
            ps.setString(1, level);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = extractCourseFromResultSet(rs);
                courses.add(course);
            }
            rs.close();
            ps.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error fetching courses by level", e);
        }
        return courses;
    }

    @Override
    public List<Course> getCoursesByCategory(String category) throws DAOException {

        Connection connection = dbConnection.getConnection();
        List<Course> courses = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Courses WHERE category = ?");
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = extractCourseFromResultSet(rs);
                courses.add(course);
            }
            rs.close();
            ps.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error fetching courses by category", e);
        }
        return courses;
    }

    @Override
    public void updateCourse(Course course) throws DAOException {

        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Courses SET title = ?, description = ?, instructor = ?, level = ?, price = ?, duration = ?, category = ? WHERE courseId = ?");
            ps.setString(1, course.getTitle());
            ps.setString(2, course.getDescription());
            ps.setInt(3, course.getInstructorId());
            ps.setString(4, course.getLevel().toString());
            ps.setDouble(5, course.getPrice());
            ps.setInt(6, course.getDuration());
            ps.setInt(7, course.getCourseId());
            ps.setString(8, course.getCategory().toString());
            ps.executeUpdate();
            ps.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error updating course", e);
        }
    }

    @Override
    public void deleteCourse(int courseId) throws DAOException {

        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Courses WHERE courseId = ?");
            ps.setInt(1, courseId);
            ps.executeUpdate();
            ps.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error deleting course", e);
        }
    }

    @Override
    public Course getCourseByName(String name) throws DAOException {
        Connection connection = dbConnection.getConnection();
        Course course = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Courses WHERE title = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                course = extractCourseFromResultSet(rs);
            }
            rs.close();
            ps.close();
            auditingService.logCurrentAction();
        } catch (SQLException e) {
            throw new DAOException("Error getting course by name", e);
        }
        return course;
    }
    
}
