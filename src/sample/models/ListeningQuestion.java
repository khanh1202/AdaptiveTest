package sample.models;

import java.util.ArrayList;

public class ListeningQuestion extends Question {
    private String text;
    private String audioURL;
    private ArrayList<Answer> possibleAnswers = new ArrayList<>();
    private String correctAnswer;

    public ListeningQuestion(QuestionDifficulty difficulty, String text, String audioURL, String answerA, String answerB, String answerC, String answerD, String correctAnswer) {
        super(difficulty);
        this.text = text;
        this.audioURL = audioURL;
        possibleAnswers.add(new Answer(answerA, answerA.equals(correctAnswer)));
        possibleAnswers.add(new Answer(answerB, answerB.equals(correctAnswer)));
        possibleAnswers.add(new Answer(answerC, answerC.equals(correctAnswer)));
        possibleAnswers.add(new Answer(answerD, answerD.equals(correctAnswer)));
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public ArrayList<Answer> getPossibleAnswers() {
        return possibleAnswers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
