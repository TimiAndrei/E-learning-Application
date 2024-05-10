package com.timi.service;

import java.util.List;
import java.util.Queue;

import com.timi.model.*;

public interface DataService {
    public void loadDatabase() throws Exception;
    public void printAllData() throws Exception;
    public void setCourses(List<Course> courses);
    public List<Course> getCourses();
    public void setQuizzes(List<Quiz> quizzes);
    public List<Quiz> getQuizzes();
    public void setQuestions(List<Question> questions);
    public List<Question> getQuestions();
    public void setQuizAttempts(List<QuizAttempt> quizAttempts);
    public List<QuizAttempt> getQuizAttempts();
    public void setUsers(List<User> users);
    public List<User> getUsers();
    public void setApplications(Queue<Application> applications);
    public Queue<Application> getApplications();
    
}
