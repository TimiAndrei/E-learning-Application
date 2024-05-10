package com.timi.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private Level level;
    private int points;
    private List<Course> enrolledCourses;

    public Student(int id, String email, String username, String password, Level level, int points) {
        super(id, email, username, password);
        this.level = level;
        this.points = points;
        this.enrolledCourses = new ArrayList<>();
    }

    public Student(String email, String username, String password, Level level, int points) {
        super(email, username, password, "STUDENT");
        this.level = level;
        this.points = points;
        this.enrolledCourses = new ArrayList<>();
    }


    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }

    public void viewEnrolledCourses() {
        for (Course course : enrolledCourses) {
            System.out.println(course.getTitle());
        }
    }

    public boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }

    @Override
    public String toString() {
        return "Student [" + super.toString() + ", level=" + level + ", points=" + points + ", enrolledCourses="
                + enrolledCourses + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((level == null) ? 0 : level.hashCode());
        result = prime * result + points;
        result = prime * result + ((enrolledCourses == null) ? 0 : enrolledCourses.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (level != other.level)
            return false;
        if (points != other.points)
            return false;
        if (enrolledCourses == null) {
            if (other.enrolledCourses != null)
                return false;
        } else if (!enrolledCourses.equals(other.enrolledCourses))
            return false;
        return true;
    }
    
}
