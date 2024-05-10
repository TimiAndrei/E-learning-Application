package com.timi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.*;
import com.timi.dao.QuizDAO;
import com.timi.dao.impl.QuizDAOImpl;
import com.timi.model.Question;
import com.timi.model.Quiz;

public class QuizDAOImplTest {

    private QuizDAO quizDAO;

    @BeforeEach
    void setUp() {
        quizDAO = new QuizDAOImpl();
    }

    @Test
    @DisplayName("Add Quiz Test")
    void addQuizTest() {
        List<Question> questions = List.of(new Question("What is the capital of Nigeria?", List.of("Lagos", "Abuja", "Kano", "Ibadan"), 1, 1));
        assertDoesNotThrow(() -> quizDAO.addQuiz(new Quiz("Nigeria Quiz", questions, 1, 10.0f)));
    }

    @Test
    @DisplayName("Get Quiz By Id Test")
    void getQuizByIdTest() {
        int quizId = 1;
        Quiz quiz = assertDoesNotThrow(() -> quizDAO.getQuizById(quizId));
        assertNotNull(quiz);
    }

    @Test
    @DisplayName("Get All Quizzes Test")
    void getAllQuizzesTest() {
        List<Quiz> quizzes = assertDoesNotThrow(quizDAO::getAllQuizzes);
        assertNotNull(quizzes);
    }

    @Test
    @DisplayName("Get Quizzes By Course Id Test")
    void getQuizzesByCourseIdTest() {
        int courseId = 1;
        List<Quiz> quizzes = assertDoesNotThrow(() -> quizDAO.getQuizzesByCourseId(courseId));
        assertNotNull(quizzes);
    }

    @Test
    @DisplayName("Update Quiz Test")
    void updateQuizTest() {
        List<Question> questions = List.of(new Question("What is the capital of Nigeria?", List.of("Lagos", "Abuja", "Kano", "Ibadan"), 1,1));
        Quiz quiz = new Quiz(1, "Nigeria Quiz", questions, 1, 10.0f);
        assertDoesNotThrow(() -> quizDAO.updateQuiz(quiz));
    }

    @Test
    @DisplayName("Delete Quiz Test")
    void deleteQuizTest() {
        int quizId = 1;
        assertDoesNotThrow(() -> quizDAO.deleteQuiz(quizId));
    }
    
}
