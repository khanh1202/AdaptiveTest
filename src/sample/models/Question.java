package sample.models;

import java.util.ArrayList;

public abstract class Question {
    private QuestionDifficulty difficulty; //difficulty of the question

    public Question(QuestionDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public QuestionDifficulty getDifficulty() {
        return difficulty;
    }
}
