package com.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String endCharacter;
        do {
            QuestionsGame game = null;
            do {
                System.out.println("Choose the game difficulty: easy (1), medium (2), hard (3)");
                switch (scanner.nextLine()) {
                    case "1":
                        game = new EasyQuestionsGame();
                        break;
                    case "2":
                        game = new MediumQuestionsGame();
                        break;
                    case "3":
                        game = new HardQuestionsGame();
                        break;
                    default:
                        System.out.println("Invalid difficulty. Please try again.");
                }
            } while (game == null);
            System.out.println("Please register your riddles:");
            Map<String, String> riddles = new HashMap<>();
            do {
                System.out.println("Enter a question:");
                String question = scanner.nextLine();
                System.out.println("Enter the answer:");
                String answer = scanner.nextLine();
                riddles.put(question, answer);
                do {
                    System.out.println("Do you want to add another riddle? (y/n)");
                    endCharacter = scanner.nextLine();
                } while (!endCharacter.equals("y") && !endCharacter.equals("n"));
            } while (!endCharacter.equals("n"));
            game.registerRiddles(riddles);
            System.out.println("The game is started. Answer the following questions:");
            Map<String, String> answers = new HashMap<>();
            String question;
            while ((question = game.getNextQuestion()) != null) {
                try {
                    System.out.println(question + " (" + game.giveHint(question) + ")");
                } catch (UnsupportedOperationException e) {
                    System.out.println(question);
                }
                answers.put(question, scanner.nextLine());
            }
            System.out.println(game.validateAnswers(answers) ? "Congratulations! You won!" : "You lost!");
            do {
                System.out.println("Do you want to play again? (y/n)");
                endCharacter = scanner.nextLine();
            } while (!endCharacter.equals("y") && !endCharacter.equals("n"));
        } while (!endCharacter.equals("n"));
    }
}