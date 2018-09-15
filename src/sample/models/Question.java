package sample.models;

import java.util.ArrayList;

public class Question {
    private String text; //The question text
    private ArrayList<Answer> possibleAnswers = new ArrayList<>(); //4 possible answers for the question
    private QuestionDifficulty difficulty; //difficulty of the question
    private String correctAnswer; //correct answer to the question

    public Question(String text, QuestionDifficulty difficulty, String answerA, String answerB, String answerC, String answerD,
                    String correctAnswer) {
        this.text = text;
        this.difficulty = difficulty;
        this.correctAnswer = correctAnswer;
        possibleAnswers.add(new Answer(answerA, answerA.equals(correctAnswer)? true : false));
        possibleAnswers.add(new Answer(answerB, answerB.equals(correctAnswer)? true : false));
        possibleAnswers.add(new Answer(answerC, answerC.equals(correctAnswer)? true : false));
        possibleAnswers.add(new Answer(answerD, answerD.equals(correctAnswer)? true : false));
    }

    public String getText() {
        return text;
    }

    public ArrayList<Answer> getPossibleAnswers() {
        return possibleAnswers;
    }

    public QuestionDifficulty getDifficulty() {
        return difficulty;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
