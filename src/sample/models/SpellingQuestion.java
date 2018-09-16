package sample.models;

public class SpellingQuestion extends Question {
    private String pathMediaFile;
    private String correctAnswer;

    public SpellingQuestion(QuestionDifficulty difficulty, String pathMediaFile, String correctAnswer) {
        super(difficulty);
        this.pathMediaFile = pathMediaFile;
        this.correctAnswer = correctAnswer;
    }

    public String getPathMediaFile() {
        return pathMediaFile;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
