package com.game;

import java.util.*;

public abstract class QuestionsGame {
    public void registerRiddles(Map<String, String> riddles) {}

    public String getNextQuestion() {
        return "";
    }

    public boolean validateAnswers(Map<String, String> answers) {
        return false;
    }

    public abstract String giveHint(String question);
}
