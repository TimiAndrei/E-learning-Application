package com.timi.model;

import java.util.List;

public class Course {
    private int courseId;
    private String title;
    private String description;
    private int instructorId;
    private Level level;
    private Double price;
    private int duration;
    private Category category;
    private List<Quiz> quizzes;


    public Course() {
    }

    public Course(int courseId, String title, String description, int instructorId, Level level, Double price, int duration, Category category, List<Quiz> quizzes) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.level = level;
        this.price = price;
        this.duration = duration;
        this.category = category;
        this.quizzes = quizzes;
    }

    // constructor without courseId
    public Course(String title, String description, int instructorId, Level level, Double price, int duration, Category category, List<Quiz> quizzes) {
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.level = level;
        this.price = price;
        this.duration = duration;
        this.category = category;
        this.quizzes = quizzes;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
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

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructor(int instructorId) {
        this.instructorId = instructorId;
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
                + instructorId + ", price=" + price + ", duration=" + duration + ", category=" + category + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + courseId;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + instructorId;
        result = prime * result + ((level == null) ? 0 : level.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + duration;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
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
        if (instructorId != other.instructorId)
            return false;
        if (level != other.level)
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (duration != other.duration)
            return false;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (quizzes == null) {
            if (other.quizzes != null)
                return false;
        } else if (!quizzes.equals(other.quizzes))
            return false;
        return true;
    }

    
}