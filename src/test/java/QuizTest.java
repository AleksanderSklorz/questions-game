import com.game.*;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuizTest {
    @Test
    public void testCounterOfCorrectAnswersByQuestion() throws InterruptedException, ExecutionException {
        Riddle riddle1 = new Riddle();
        riddle1.setQuestion("What is the capital of France?");
        riddle1.setAnswer("Paris");
        riddle1.setWrongAnswers(List.of("London", "Berlin", "Madrid"));
        Riddle riddle2 = new Riddle();
        riddle2.setQuestion("What is the capital of Spain?");
        riddle2.setAnswer("Madrid");
        riddle2.setWrongAnswers(List.of("London", "Berlin", "Paris"));
        RiddlesBase.registerRiddles(List.of(riddle1, riddle2));
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<?>> futures = new ArrayList<>();
        List<Quiz> quizzes = IntStream.range(0, 10).mapToObj(i -> new Quiz(DifficultyLevel.EASY)).collect(Collectors.toList());
        quizzes.forEach((quiz) -> futures.add(executorService.submit(quiz)));
        List<Map<String, Integer>> countersByQuestion = new ArrayList<>();
        for (Future<?> future : futures) {
            future.get();
            countersByQuestion.add(QuizGame.getGoodAnswersCounterByQuestion());
        }
        assertEquals(1, new HashSet<>(countersByQuestion).size());
        Map<String, Integer> counterByQuestion = countersByQuestion.getFirst();
        assertEquals((long)counterByQuestion.get(riddle1.getQuestion()), quizzes.stream()
                .filter((quiz) -> quiz.getAnswers().get(riddle1.getQuestion()).equalsIgnoreCase(riddle1.getAnswer())).count());
        assertEquals((long)counterByQuestion.get(riddle2.getQuestion()), quizzes.stream()
                .filter((quiz) -> quiz.getAnswers().get(riddle2.getQuestion()).equalsIgnoreCase(riddle2.getAnswer())).count());
        executorService.shutdown();

    }
}
