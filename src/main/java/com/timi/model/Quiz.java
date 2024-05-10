package com.timi.model;

import java.util.List;

public class Quiz {
    private int quizId;
    private String title;
    private List<Question> questions;
    private int courseId;
    private float duration;

    public Quiz() {
    }

    public Quiz(int quizId, String title, List<Question> questions, int courseId, float duration) {
        this.quizId = quizId;
        this.title = title;
        this.questions = questions;
        this.courseId = courseId;
        this.duration = duration;
    }

    // constructor without quizId
    public Quiz(String title, List<Question> questions, int courseId, float duration) {
        this.title = title;
        this.questions = questions;
        this.courseId = courseId;
        this.duration = duration;
    }

    // constructor without questions
    public Quiz(int quizId, String title, int courseId, float duration) {
        this.quizId = quizId;
        this.title = title;
        this.courseId = courseId;
        this.duration = duration;
    }

    // constructor without quizId and questions
    public Quiz(String title, int courseId, float duration) {
        this.title = title;
        this.courseId = courseId;
        this.duration = duration;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Quiz \n[quizId=" + quizId + "\ntitle=" + title + "\nquestions=\n" + questions + "\ncourseId=" + courseId
                + "\nduration=" + duration + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + quizId;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((questions == null) ? 0 : questions.hashCode());
        result = prime * result + courseId;
        result = prime * result + Float.floatToIntBits(duration);
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
        Quiz other = (Quiz) obj;
        if (quizId != other.quizId)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (questions == null) {
            if (other.questions != null)
                return false;
        } else if (!questions.equals(other.questions))
            return false;
        if (courseId != other.courseId)
            return false;
        if (Float.floatToIntBits(duration) != Float.floatToIntBits(other.duration))
            return false;
        return true;
    }

    
}
