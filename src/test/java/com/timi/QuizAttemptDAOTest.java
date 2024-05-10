package com.timi;

import com.timi.dao.QuizAttemptDAO;
import com.timi.dao.impl.QuizAttemptDAOImpl;
import com.timi.model.QuizAttempt;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class QuizAttemptDAOTest {
    
    private QuizAttemptDAO quizAttemptDAO;

    @BeforeEach
    void setUp() {
        quizAttemptDAO = new QuizAttemptDAOImpl();
    }

    @Test
    @DisplayName("Add Quiz Attempt Test")
    void addQuizAttemptTest() {
        QuizAttempt quiz = new QuizAttempt(1, 1, LocalDateTime.now(), 0, null, 30);
        assertDoesNotThrow(() -> quizAttemptDAO.addQuizAttempt(quiz));
    }

    @Test
    @DisplayName("Get Quiz Attempt By Id Test")
    void getQuizAttemptByIdTest() {
        int attemptId = 1;
        QuizAttempt quiz = assertDoesNotThrow(() -> quizAttemptDAO.getQuizAttemptById(attemptId));
        assertNotNull(quiz);
    }

    @Test
    @DisplayName("Get All Quiz Attempts Test")
    void getAllQuizAttemptsTest() {
        List<QuizAttempt> quizzes = assertDoesNotThrow(quizAttemptDAO::getAllQuizAttempts);
        assertNotNull(quizzes);
    }

    @Test
    @DisplayName("Get Quiz Attempts By User Id Test")
    void getQuizAttemptsByUserIdTest() {
        int userId = 1;
        List<QuizAttempt> quizzes = assertDoesNotThrow(() -> quizAttemptDAO.getQuizAttemptsByUserId(userId));
        assertNotNull(quizzes);
    }

    @Test
    @DisplayName("Get Quiz Attempts By Quiz Id Test")
    void getQuizAttemptsByQuizIdTest() {
        int quizId = 1;
        List<QuizAttempt> quizzes = assertDoesNotThrow(() -> quizAttemptDAO.getQuizAttemptsByQuizId(quizId));
        assertNotNull(quizzes);
    }

    @Test
    @DisplayName("Get Quiz Attempts By User Id And Quiz Id Test")
    void getQuizAttemptsByUserIdAndQuizIdTest() {
        int userId = 1;
        int quizId = 1;
        List<QuizAttempt> quizzes = assertDoesNotThrow(() -> quizAttemptDAO.getQuizAttemptsByUserIdAndQuizId(userId, quizId));
        assertNotNull(quizzes);
    }

    @Test
    @DisplayName("Update Quiz Attempt Test")
    void updateQuizAttemptTest() {
        QuizAttempt quiz = new QuizAttempt(1, 1, LocalDateTime.now(), 0, null, 30);
        assertDoesNotThrow(() -> quizAttemptDAO.updateQuizAttempt(quiz));
    }

    @Test
    @DisplayName("Delete Quiz Attempt Test")
    void deleteQuizAttemptTest() {
        int attemptId = 1;
        assertDoesNotThrow(() -> quizAttemptDAO.deleteQuizAttempt(attemptId));
    }

}
