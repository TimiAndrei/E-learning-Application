package com.timi;

import com.timi.dao.CourseDAO;
import com.timi.dao.impl.CourseDAOImpl;
import com.timi.model.Course;
import com.timi.model.Level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseDAOImplTest {

    private CourseDAO courseDAO;

    @BeforeEach
    void setUp() {
        courseDAO = new CourseDAOImpl();
    }

    @Test
    @DisplayName("Add Course Test")
    void addCourseTest() {
        Course course = new Course("Java Programming", "Learn Java Programming", 3, Level.BEGINNER, 100.0, 10, null, null);
        assertDoesNotThrow(() -> courseDAO.addCourse(course));
    }

    @Test
    @DisplayName("Get Course By Id Test")
    void getCourseByIdTest() {
        int courseId = 1;
        Course course = assertDoesNotThrow(() -> courseDAO.getCourseById(courseId));
        assertNotNull(course);
    }

    @Test
    @DisplayName("Get All Courses Test")
    void getAllCoursesTest() {
        List<Course> courses = assertDoesNotThrow(courseDAO::getAllCourses);
        assertNotNull(courses);
    }

    @Test
    @DisplayName("Get Courses By Instructor Test")
    void getCoursesByInstructorTest() {
        String instructor = "John Doe";
        List<Course> courses = assertDoesNotThrow(() -> courseDAO.getCoursesByInstructor(instructor));
        assertNotNull(courses);
    }

    @Test
    @DisplayName("Get Courses By Level Test")
    void getCoursesByLevelTest() {
        String level = "Beginner";
        List<Course> courses = assertDoesNotThrow(() -> courseDAO.getCoursesByLevel(level));
        assertNotNull(courses);
    }

    @Test
    @DisplayName("Get Courses By Category Test")
    void getCoursesByCategoryTest() {
        String category = "Java";
        List<Course> courses = assertDoesNotThrow(() -> courseDAO.getCoursesByCategory(category));
        assertNotNull(courses);
    }

    @Test
    @DisplayName("Update Course Test")
    void updateCourseTest() {
        Course course = new Course(1, "Java Programming", "Learn Java Programming", 3, Level.BEGINNER, 100.0, 10, null, null);
        assertDoesNotThrow(() -> courseDAO.updateCourse(course));
    }

    @Test
    @DisplayName("Delete Course Test")
    void deleteCourseTest() {
        int courseId = 1;
        assertDoesNotThrow(() -> courseDAO.deleteCourse(courseId));
    }
}
