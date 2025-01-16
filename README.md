# Introduction
Implement a game called “Questions Game” where people can register own questions and answers and then other people need to answer them.

## Technical details
It's a maven based project, so it should be easy to import it in your favourite IDE.
There are some Junit 5 test cases. They can be executed from the IDE or from the command line by running:
```shell
mvn test
```
We use latest LTS Java version (17). For your convenience we don't use any Java 17 specific features, so you could change it if needed (Java 11 is the baseline here).
Feel free to use any Java version (11 or above) which works for you. You can change the java version in the [pom.xml](./pom.xml) file by editing following line:
```xml
<java.version>17</java.version>
```

# Tasks 
There are 3 tasks. They depend on each other so start with [Task 1](#Task-1) then continue with [Task 2](#Task-2) and finish the whole assignment with the [Task 3](#Task-3).

## Task 1

Implement a class named QuestionsGame with following functions:
- `registerRiddles` - allows to add multiple riddles with correct answers. To simplify please ensure each answer is single word. If any registered answer is not single word then throw exception.
- `getNextQuestion` - returns next question registered in “registerRiddle” function randomly. Please ensure that you won't get the same next question if question was already asked.
- `validateAnswers` - check if answered responses on asked questions are correct. Please ensure it compares answer case insensitively. This function should to use configurable `threshold` field from `QuestionGame` class so then function returns true if number of correct answers is greater or equal to threshold. If threshold is not set then it should return true if all answers are correct.
- `giveHint` - gives hint for the answer. Should be implemented by subclasses. 

## Task 2
We want to have game with 3 difficulty levels - `Easy`, `Medium`, `Hard`. We want to have class created in Task 1 as base and then create separated class for each level, for example: for Easy we want to have `EasyQuestionsGame` class. These classes should have following implementation of `giveHint` function:
- for `Easy` mode that function should give you first and last characters of answer,
- for `Medium` mode that function should give you only first character of answer,
- for `Hard` mode that function should not give you any character of answer and should throw exception saying function is not supported.

Depending on level there should be different `threshold` for correct answers which you need to win game:
- `Easy` - 50% correct answers
- `Medium` - 75% correct answers
- `Hard` - 90% correct answers

## Task 3
Please write unit tests for code which you wrote in above tasks.  