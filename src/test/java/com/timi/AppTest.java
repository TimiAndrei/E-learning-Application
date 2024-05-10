package com.timi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ApplicationDAOImplTest.class,
    UserDAOImplTest.class,
    CourseDAOImplTest.class,
    QuizDAOImplTest.class,
    QuestionDAOImplTest.class,
    QuizAttemptDAOImplTest.class
})
public class AppTest {
    
}
