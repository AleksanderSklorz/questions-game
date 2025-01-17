package com.game;

import java.util.*;

public abstract class QuizGame {
    private final List<Riddle> riddles = RiddlesBase.getRiddles();
    private final List<Integer> askedQuestionsIndexes = new ArrayList<>();

    private double mistakesThreshold = 0.5;

    public void setMistakesThreshold(double mistakesThreshold) {}

    public String getNextQuestion() {
        return "";
    }

    public boolean validateAnswers(Map<String, String> answers) {
        return false;
    }

    public abstract List<String> giveHint(String question);
}
