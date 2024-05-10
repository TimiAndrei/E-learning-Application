package com.timi.service.impl;

import java.util.List;
import java.util.Queue;

import com.timi.model.*;
import com.timi.service.DataService;
import com.timi.service.ElearningService;

public class DataServiceImpl implements DataService{

    private List<Course> courses;
    private List<Quiz> quizzes;
    private List<Question> questions;
    private List<QuizAttempt> quizAttempts;
    private List<User> users;
    private Queue<Application> applications;
    private ElearningService elearningService;

    public DataServiceImpl() {
        this.elearningService = new ElearningServiceImpl();
    }

    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    public List<Quiz> getQuizzes() {
        return quizzes;
    }
    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
    public List<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    public List<QuizAttempt> getQuizAttempts() {
        return quizAttempts;
    }
    public void setQuizAttempts(List<QuizAttempt> quizAttempts) {
        this.quizAttempts = quizAttempts;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public Queue<Application> getApplications() {
        return applications;
    }
    public void setApplications(Queue<Application> applications) {
        this.applications = applications;
    }

    @Override
    public void loadDatabase() throws Exception {
        this.setCourses(elearningService.getAllCourses());
        this.setQuizzes(elearningService.getAllQuizzes());
        this.setQuestions(elearningService.getAllQuestions());
        this.setQuizAttempts(elearningService.getAllQuizAttempts());
        this.setUsers(elearningService.getAllUsers());
        this.setApplications(elearningService.getAllApplications());
    }

    @Override
    public void printAllData() throws Exception {
        System.out.println("All courses:");
        for (Course course : this.getCourses()) {
            System.out.println(course);
        }
        System.out.println();

        System.out.println("All users:");
        for (User user : this.getUsers()) {
            System.out.println(user);
        }
        System.out.println();
        
        System.out.println("All applications:");
        for (Application application : this.getApplications()) {
            System.out.println(application);
        }
        System.out.println();

        System.out.println("All questions:");
        for (Question question : this.getQuestions()) {
            System.out.println(question);
        }
        System.out.println();

        System.out.println("All quiz attempts:");
        for (QuizAttempt quizAttempt : this.getQuizAttempts()) {
            System.out.println(quizAttempt);
        }
        System.out.println();

        System.out.println("All quizzes:");
        for (Quiz quiz : this.getQuizzes()) {
            System.out.println(quiz);
        }
        System.out.println();
    }
}
