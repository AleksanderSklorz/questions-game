import com.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;

public class QuizGameTest {
    @BeforeEach
    public void init() {
        Riddle riddle1 = new Riddle();
        riddle1.setQuestion("What is the capital of France?");
        riddle1.setAnswer("Paris");
        riddle1.setWrongAnswers(List.of("London", "Berlin", "Madrid"));
        Riddle riddle2 = new Riddle();
        riddle2.setQuestion("What is the capital of Spain?");
        riddle2.setAnswer("Madrid");
        riddle2.setWrongAnswers(List.of("London", "Berlin", "Paris"));
        RiddlesBase.registerRiddles(List.of(riddle1, riddle2));
    }

    @Test
    public void testEasyQuizGame() {
        QuizGame game = new EasyQuizGame();
        List<String> questions = List.of(game.getNextQuestion(), game.getNextQuestion());
        assertEquals(2, questions.size());
        assertTrue(questions.containsAll(List.of("What is the capital of France?", "What is the capital of Spain?")));
        assertNull(game.getNextQuestion());
    }

    @Test
    public void testMediumQuizGame() {
        QuizGame game = new MediumQuizGame();
        List<String> questions = List.of(game.getNextQuestion(), game.getNextQuestion());
        assertEquals(2, questions.size());
        assertTrue(questions.containsAll(List.of("What is the capital of France?", "What is the capital of Spain?")));
        assertNull(game.getNextQuestion());
    }

    @Test
    public void testHardQuizGame() {
        QuizGame game = new HardQuizGame();
        List<String> questions = List.of(game.getNextQuestion(), game.getNextQuestion());
        assertEquals(2, questions.size());
        assertTrue(questions.containsAll(List.of("What is the capital of France?", "What is the capital of Spain?")));
        assertNull(game.getNextQuestion());
    }

    @Test
    public void testEasyQuizGameHints() {
        QuizGame game = new EasyQuizGame();
        assertTrue(game.giveHint("What is the capital of France?").containsAll(List.of("Paris", "London")));
    }

    @Test
    public void testMediumQuizGameHints() {
        QuizGame game = new MediumQuizGame();
        assertTrue(game.giveHint("What is the capital of France?").containsAll(List.of("Paris", "London", "Berlin")));

    }

    @Test
    public void testHardQuizGameHints() {
        QuizGame game = new HardQuizGame();
        assertTrue(game.giveHint("What is the capital of France?").containsAll(List.of("Paris", "London", "Berlin", "Madrid")));
    }

    @Test
    public void testEasyQuizGameValidation() {
        QuizGame game = new EasyQuizGame();
        Map<String, String> answers = new HashMap<>();
        answers.put("What is the capital of France?", "Paris");
        answers.put("What is the capital of Spain?", "Madrid");
        assertTrue(game.validateAnswers(answers));
    }

    @Test
    public void testMediumQuizGameValidation() {
        QuizGame game = new MediumQuizGame();
        Map<String, String> answers = new HashMap<>();
        answers.put("What is the capital of France?", "Paris");
        answers.put("What is the capital of Spain?", "Madrid");
        assertTrue(game.validateAnswers(answers));
    }

    @Test
    public void testHardQuizGameValidation() {
        QuizGame game = new HardQuizGame();
        Map<String, String> answers = new HashMap<>();
        answers.put("What is the capital of France?", "Paris");
        answers.put("What is the capital of Spain?", "Madrid");
        assertTrue(game.validateAnswers(answers));
    }

    @Test
    public void testEasyQuizGameValidationWithMistakes() {
        prepareMoreRiddles();
        QuizGame game = new EasyQuizGame();
        Map<String, String> answers = new HashMap<>();
        answers.put("What is the capital of France?", "Paris");
        answers.put("What is the capital of Spain?", "Madrid");
        answers.put("What is the capital of Germany?", "Berlin");
        answers.put("What is the capital of Italy?", "Paris");
        answers.put("What is the capital of Poland?", "Paris");
        answers.put("What is the capital of UK?", "Paris");
        answers.put("What is the capital of USA?", "Paris");
        assertFalse(game.validateAnswers(answers));
        answers.put("What is the capital of Italy?", "Rome");
        assertTrue(game.validateAnswers(answers));
    }

    @Test
    public void testMediumQuizGameValidationWithMistakes() {
        prepareMoreRiddles();
        QuizGame game = new MediumQuizGame();
        Map<String, String> answers = new HashMap<>();
        answers.put("What is the capital of France?", "Paris");
        answers.put("What is the capital of Spain?", "Madrid");
        answers.put("What is the capital of Germany?", "Berlin");
        answers.put("What is the capital of Italy?", "Rome");
        answers.put("What is the capital of Poland?", "Warsaw");
        answers.put("What is the capital of UK?", "Paris");
        answers.put("What is the capital of USA?", "Paris");
        assertFalse(game.validateAnswers(answers));
        answers.put("What is the capital of UK?", "London");
        assertTrue(game.validateAnswers(answers));
    }

    @Test
    public void testHardQuizGameValidationWithMistakes() {
        prepareMoreRiddles();
        QuizGame game = new HardQuizGame();
        Map<String, String> answers = new HashMap<>();
        answers.put("What is the capital of France?", "Paris");
        answers.put("What is the capital of Spain?", "Madrid");
        answers.put("What is the capital of Germany?", "Berlin");
        answers.put("What is the capital of Italy?", "Rome");
        answers.put("What is the capital of Poland?", "Warsaw");
        answers.put("What is the capital of UK?", "London");
        answers.put("What is the capital of USA?", "Paris");
        assertFalse(game.validateAnswers(answers));
        answers.put("What is the capital of USA?", "Washington");
        assertTrue(game.validateAnswers(answers));
    }

    private void prepareMoreRiddles() {
        Riddle riddle1 = new Riddle();
        riddle1.setQuestion("What is the capital of France?");
        riddle1.setAnswer("Paris");
        riddle1.setWrongAnswers(List.of("London", "Berlin", "Madrid"));
        Riddle riddle2 = new Riddle();
        riddle2.setQuestion("What is the capital of Spain?");
        riddle2.setAnswer("Madrid");
        riddle2.setWrongAnswers(List.of("London", "Berlin", "Paris"));
        Riddle riddle3 = new Riddle();
        riddle3.setQuestion("What is the capital of Germany?");
        riddle3.setAnswer("Berlin");
        riddle3.setWrongAnswers(List.of("London", "Paris", "Madrid"));
        Riddle riddle4 = new Riddle();
        riddle4.setQuestion("What is the capital of Italy?");
        riddle4.setAnswer("Rome");
        riddle4.setWrongAnswers(List.of("London", "Paris", "Madrid"));
        Riddle riddle5 = new Riddle();
        riddle5.setQuestion("What is the capital of Poland?");
        riddle5.setAnswer("Warsaw");
        riddle5.setWrongAnswers(List.of("London", "Paris", "Madrid"));
        Riddle riddle6 = new Riddle();
        riddle6.setQuestion("What is the capital of UK?");
        riddle6.setAnswer("London");
        riddle6.setWrongAnswers(List.of("Paris", "Berlin", "Madrid"));
        Riddle riddle7 = new Riddle();
        riddle7.setQuestion("What is the capital of USA?");
        riddle7.setAnswer("Washington");
        riddle7.setWrongAnswers(List.of("London", "Paris", "Madrid"));
        RiddlesBase.registerRiddles(List.of(riddle1, riddle2, riddle3, riddle4, riddle5, riddle6, riddle7));
    }

}
