package com.timi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.*;
import com.timi.dao.QuestionDAO;
import com.timi.dao.impl.QuestionDAOImpl;
import com.timi.model.Question;

public class QuestionDAOImplTest {

    private QuestionDAO questionDAO;

    @BeforeEach
    void setUp() {
        questionDAO = new QuestionDAOImpl();
    }

    @Test
    @DisplayName("Add Question Test")
    void addQuestionTest() {
        Question question = new Question("What is the capital of Nigeria?", List.of("Lagos", "Abuja", "Kano", "Ibadan"), 1, 1);
        assertDoesNotThrow(() -> questionDAO.addQuestion(question));
    }

    @Test
    @DisplayName("Get Question By Id Test")
    void getQuestionByIdTest() {
        int questionId = 1;
        Question question = assertDoesNotThrow(() -> questionDAO.getQuestionById(questionId));
        assertNotNull(question);
    }

    @Test
    @DisplayName("Get All Questions")
    void getAllQuestionsTest() {
        List<Question> questions = assertDoesNotThrow(questionDAO::getAllQuestions);
        assertNotNull(questions);
    }

    @Test
    @DisplayName("Update Question Test")
    void updateQuestionTest() {
        Question question = new Question(1, "What is the capital of Nigeria?", List.of("Lagos", "Abuja", "Kano", "Ibadan"), 1, 1);
        assertDoesNotThrow(() -> questionDAO.updateQuestion(question));
    }

    @Test
    @DisplayName("Delete Question Test")
    void deleteQuestionTest() {
        int questionId = 1;
        assertDoesNotThrow(() -> questionDAO.deleteQuestion(questionId));
    }

    @Test
    @DisplayName("Get Questions By Quiz Id Test")
    void getQuestionsByQuizIdTest() {
        int quizId = 1;
        List<Question> questions = assertDoesNotThrow(() -> questionDAO.getQuestionsByQuizId(quizId));
        assertNotNull(questions);
    }

}
