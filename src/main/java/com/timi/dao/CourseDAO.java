package com.timi.dao;

import com.timi.exception.DAOException;
import com.timi.model.Course;

import java.util.List;

public interface CourseDAO {
    void addCourse(Course course) throws DAOException;

    Course getCourseById(int courseId) throws DAOException;

    List<Course> getAllCourses() throws DAOException;

    List<Course> getCoursesByInstructor(String instructor) throws DAOException;

    List<Course> getCoursesByLevel(String level) throws DAOException;

    List<Course> getCoursesByCategory(String category) throws DAOException;

    void updateCourse(Course course) throws DAOException;

    void deleteCourse(int courseId) throws DAOException;
}

