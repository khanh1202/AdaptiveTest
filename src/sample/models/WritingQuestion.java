package sample.models;

public class WritingQuestion extends Question {
    private String text;

    public WritingQuestion(QuestionDifficulty difficulty) {
        super(difficulty);
        switch (difficulty) {
            case TOUGH:
                text = "Write a simple sentence";
                break;
            case TOUGHER:
                text = "Write a compound sentence";
                break;
            case TOUGHEST:
                text = "Write a complex sentence";
        }
    }

    public String getText() {
        return text;
    }
}
