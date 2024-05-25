package com.timi.service.impl;

import java.util.List;
import java.util.Queue;

import com.timi.dao.*;
import com.timi.dao.impl.*;
import com.timi.exception.DAOException;
import com.timi.exception.InvalidEmailException;
import com.timi.model.*;
import com.timi.service.ElearningService;

public class ElearningServiceImpl implements ElearningService{
    
    private UserDAO userDAO;
    private CourseDAO courseDAO;
    private QuizDAO quizDAO;
    private QuestionDAO questionDAO;
    private QuizAttemptDAO quizAttemptDAO;
    private ApplicationDAO applicationDAO;

    public ElearningServiceImpl() {
        this.userDAO = new UserDAOImpl();
        this.courseDAO = new CourseDAOImpl();
        this.quizDAO = new QuizDAOImpl();
        this.questionDAO = new QuestionDAOImpl();
        this.quizAttemptDAO = new QuizAttemptDAOImpl();
        this.applicationDAO = new ApplicationDAOImpl();
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public CourseDAO getCourseDAO() {
        return courseDAO;
    }

    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public QuizDAO getQuizDAO() {
        return quizDAO;
    }

    public void setQuizDAO(QuizDAO quizDAO) {
        this.quizDAO = quizDAO;
    }

    public QuestionDAO getQuestionDAO() {
        return questionDAO;
    }

    public void setQuestionDAO(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public QuizAttemptDAO getQuizAttemptDAO() {
        return quizAttemptDAO;
    }

    public void setQuizAttemptDAO(QuizAttemptDAO quizAttemptDAO) {
        this.quizAttemptDAO = quizAttemptDAO;
    }

    @Override
    public List<Course> getAllCourses() throws DAOException {
        return courseDAO.getAllCourses();
    }

    @Override
    public List<Quiz> getAllQuizzes() throws DAOException {
        return quizDAO.getAllQuizzes();
    }

    @Override
    public List<Question> getAllQuestions() throws DAOException {
        return questionDAO.getAllQuestions();
    }

    @Override
    public List<QuizAttempt> getAllQuizAttempts() throws DAOException {
        return quizAttemptDAO.getAllQuizAttempts();
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        return userDAO.getAllUsers();
    }

    @Override
    public List<Course> getUserCourses(int userId) throws DAOException {
        return courseDAO.getUserCourses(userId);
    }

    @Override
    public Queue<Application> getAllApplications() throws DAOException {
        return applicationDAO.getAllApplications();
    }

    @Override
    public Queue<Application> getPendingApplications() throws DAOException {
        return applicationDAO.getPendingApplications();
    }       

    @Override
    public User authenticateUser(String email, String password) throws DAOException {
        return userDAO.authenticateUser(email, password);
    }

    @Override
    public void registerUser(String email, String username, String password) throws DAOException {
        userDAO.registerUser(email, username, password);
    }


    @Override
    public void updateUser(User user) throws DAOException, InvalidEmailException {
        userDAO.updateUser(user);
    }

    @Override
    public User getUserById(int id) throws DAOException {
        return userDAO.getUserById(id);
    }

    @Override
    public void deleteUser(int id) throws DAOException {
        userDAO.deleteUser(id);
    }

    @Override
    public void addCourse(Course course) throws DAOException {
        courseDAO.addCourse(course);
    }

    @Override
    public void updateCourse(Course course) throws DAOException {
        courseDAO.updateCourse(course);
    }

    @Override
    public void deleteCourse(int id) throws DAOException {
        courseDAO.deleteCourse(id);
    }

    @Override
    public void addQuiz(Quiz quiz) throws DAOException {
        quizDAO.addQuiz(quiz);
    }

    @Override
    public void addQuestion(Question question) throws DAOException {
        questionDAO.addQuestion(question);
    }

    @Override
    public void editQuestion(Question question) throws DAOException {
        questionDAO.updateQuestion(question);
    }

    @Override
    public void deleteQuestion(int id) throws DAOException {
        questionDAO.deleteQuestion(id);
    }

    @Override
    public void searchCoursesByName(String name) throws DAOException {
        courseDAO.getCourseByName(name);
    }

    @Override
    public void applyForCourse(Application application) throws DAOException {
        applicationDAO.addApplication(application);
    }

    @Override
    public Question getQuestionById(int id) throws DAOException {
        return questionDAO.getQuestionById(id);
    }

    @Override
    public Course getCourseByName(String name) throws DAOException {
        return courseDAO.getCourseByName(name);
    }

    @Override
    public void takeQuiz(QuizAttempt quizAttempt) throws DAOException {
        quizAttemptDAO.addQuizAttempt(quizAttempt);
    }

    @Override
    public List<Quiz> getQuizzesByCourse(int courseId) throws DAOException {
        return quizDAO.getQuizzesByCourseId(courseId);
    }

    @Override
    public Quiz getQuizById(int id) throws DAOException {
        return quizDAO.getQuizById(id);
    }

    @Override
    public List<QuizAttempt> getUserQuizAttempts(int userId) throws DAOException {
        return quizAttemptDAO.getQuizAttemptsByUserId(userId);
    }

}
