package com.timi.model;

import java.util.List;

public class Course {
    private int courseId;
    private String title;
    private String description;
    private String instructor;
    private String level;
    private Double price;
    private int duration;
    private List<Category> categories;
    private List<Quiz> quizzes;


    public Course() {
    }

    public Course(int courseId, String title, String description, String instructor, String level, Double price, int duration, List<Category> categories, List<Quiz> quizzes) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.level = level;
        this.price = price;
        this.duration = duration;
        this.categories = categories;
        this.quizzes = quizzes;
    }

    // constructor without courseId
    public Course(String title, String description, String instructor, String level, Double price, int duration, List<Category> categories, List<Quiz> quizzes) {
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.level = level;
        this.price = price;
        this.duration = duration;
        this.categories = categories;
        this.quizzes = quizzes;
    }


    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course [courseId=" + courseId + ", title=" + title + ", description=" + description + ", instructor="
                + instructor + ", price=" + price + ", duration=" + duration + ", categories=" + categories + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + courseId;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((instructor == null) ? 0 : instructor.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + duration;
        result = prime * result + ((categories == null) ? 0 : categories.hashCode());
        result = prime * result + ((quizzes == null) ? 0 : quizzes.hashCode());
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
        Course other = (Course) obj;
        if (courseId != other.courseId)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (instructor == null) {
            if (other.instructor != null)
                return false;
        } else if (!instructor.equals(other.instructor))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (duration != other.duration)
            return false;
        if (categories == null) {
            if (other.categories != null)
                return false;
        } else if (!categories.equals(other.categories))
            return false;
        if (quizzes == null) {
            if (other.quizzes != null)
                return false;
        } else if (!quizzes.equals(other.quizzes))
            return false;
        return true;
    }

    

}