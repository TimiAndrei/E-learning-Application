package com.timi.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

// Instructor.java
public class Instructor extends User {
    private Date dateOfEmployment;
    private String department;
    private List<Course> teachingCourses;

    public Instructor(int id, String email, String username, String password, Date dateOfEmployment, String department) {
        super(id, email, username, password);
        this.dateOfEmployment = dateOfEmployment;
        this.department = department;
        this.teachingCourses = new ArrayList<>();
    }

    // Constructor without id
    public Instructor(String email, String username, String password, Date dateOfEmployment, String department) {
        super(email, username, password, "INSTRUCTOR");
        this.dateOfEmployment = dateOfEmployment;
        this.department = department;
        this.teachingCourses = new ArrayList<>();
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Course> getTeachingCourses() {
        return teachingCourses;
    }

    public void setTeachingCourses(List<Course> teachingCourses) {
        this.teachingCourses = teachingCourses;
    }

    //implement validation for email
    public boolean validateEmail(String email) {
        return email.contains("@unibuc.ro");
    }

    // Additional methods
    public void addTeachingCourse(Course course) {
        teachingCourses.add(course);
    }

    public void viewTeachingCourses() {
        for (Course course : teachingCourses) {
            System.out.println(course.getTitle());
        }
    }

    @Override
    public String toString() {
        return "Instructor [" + super.toString() + ", dateOfEmployment=" + dateOfEmployment + ", department=" + department + ", teachingCourses="
                + teachingCourses + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dateOfEmployment == null) ? 0 : dateOfEmployment.hashCode());
        result = prime * result + ((department == null) ? 0 : department.hashCode());
        result = prime * result + ((teachingCourses == null) ? 0 : teachingCourses.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Instructor other = (Instructor) obj;
        if (dateOfEmployment == null) {
            if (other.dateOfEmployment != null)
                return false;
        } else if (!dateOfEmployment.equals(other.dateOfEmployment))
            return false;
        if (department == null) {
            if (other.department != null)
                return false;
        } else if (!department.equals(other.department))
            return false;
        if (teachingCourses == null) {
            if (other.teachingCourses != null)
                return false;
        } else if (!teachingCourses.equals(other.teachingCourses))
            return false;
        return true;
    }

    
}
