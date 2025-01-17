# Introduction
Implement a game called “Quiz Game” where people can register own questions and computer tries to answer them.
The game should have 3 difficulty levels - `Easy`, `Medium`, `Hard`. Depending on the level computer should give different hints and the threshold for correct answers should be different.
For example: for `Easy` level computer needs to select answer between two hints (hint includes one correct and one wrong answers) and the threshold for correct answers is 50%.

## Technical details
It's a maven based project, so it should be easy to import it in your favourite IDE.
There are some Junit 5 test cases. They can be executed from the IDE or from the command line by running:
```shell
mvn test
```
We use latest LTS Java version (21). For your convenience we don't use any Java 21 specific features, so you could change it if needed (Java 11 is the baseline here).
Feel free to use any Java version (11 or above) which works for you. You can change the java version in the [pom.xml](./pom.xml) file by editing following line:
```xml
<java.version>21</java.version>
```

# Tasks 
There are 3 tasks. They depend on each other so start with [Task 1](#Task-1) then continue with [Task 2](#Task-2) and finish the whole assignment with the [Task 3](#Task-3).
There are also 2 files with unit tests: `QuizGameTest` which tests Task 1 and Task 2 and `QuizTest` which tests Task 3. You can run them to check if your implementation is correct.

## Task 1

Implement a class named `QuizGame` with following functions:
- `getNextQuestion` - returns next question registered in “registerRiddle” function randomly. Please ensure that you won't get the same next question if question was already asked. If there is no more questions to ask, return null. 
- `validateAnswers` - check if answered responses on asked questions are correct. Please ensure it compares answer case insensitively. This function should to use configurable `threshold` field from `QuestionGame` class so then function returns true if number of correct answers is greater or equal to threshold. If threshold is not set then it should return true if all answers are correct.
- `giveHint` - gives hint for the answer. Should be implemented by subclasses. 

Implement a class named `RiddlesBase` with following functions: 
- `registerRiddles` - allows to add multiple riddles with correct answers. To simplify please ensure each answer is single word. If any registered answer is not single word then throw exception.
- `getRiddleByQuestion` - returns riddle object by question. 
- `getRiddles` - getter for riddles. 

## Task 2
We want to have game with 3 difficulty levels - `Easy`, `Medium`, `Hard`. We want to have `QuizGame` class created in Task 1 as base and then create separated class for each level, for example: for Easy we want to have `EasyQuizGame` class. These classes should have the following implementation of `giveHint` function:
- for `Easy` computer needs to select answer between two hints (hint includes one correct and one wrong answers),
- for `Medium` computer needs to select answer between three hints (hint includes one correct and two wrong answers),
- for `Hard` computer needs to select answer between three hints (hint includes one correct and three wrong answers),

Depending on level there should be different `threshold` for correct answers which you need to win game:
- `Easy` - 50% correct answers
- `Medium` - 75% correct answers
- `Hard` - 90% correct answers

## Task 3
Implement a class named `Quiz` which is runnable class (as we want to use multithreading). This class should to contain: 
- `constructor` - allows to set difficulty level. 
- `run` - logic of quiz. Should be following: 
  - create `QuizGame` object for selected difficulty level,
  - start quiz by asking questions to computer and getting computer's answers. You should to ask until you ask all questions.
  - at the end validate all answers
- `startQuiz` - starts quiz in a new thread.
- `getAnswers` - returns answers given by computer.

Additionally please add `goodAnswersCounterByQuestion` field to `QuizGame` class and getter for that field. This field should store number of correct answers for each question. This must be multithreading safe. 
For example: if you have Q1 question and Q2 question and you have 2 threads, then this map should store number of correct answers for Q1 and Q2 questions for both threads (like: Q1 -> 0, Q2 -> 2).