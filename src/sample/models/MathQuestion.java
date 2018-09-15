package sample.models;

import java.util.ArrayList;

public class MathQuestion extends Question {
    private String text; //The question text
    private ArrayList<Answer> possibleAnswers = new ArrayList<>(); //4 possible answers for the question
    private String correctAnswer; //correct answer to the question

    public MathQuestion(String text, QuestionDifficulty difficulty, String answerA, String answerB, String answerC, String answerD,
                        String correctAnswer) {
        super(difficulty);
        this.text = text;
        possibleAnswers.add(new Answer(answerA, answerA.equals(correctAnswer)? true : false));
        possibleAnswers.add(new Answer(answerB, answerB.equals(correctAnswer)? true : false));
        possibleAnswers.add(new Answer(answerC, answerC.equals(correctAnswer)? true : false));
        possibleAnswers.add(new Answer(answerD, answerD.equals(correctAnswer)? true : false));
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Answer> getPossibleAnswers() {
        return possibleAnswers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
