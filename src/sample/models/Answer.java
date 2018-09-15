package sample.models;

public class Answer {
    private String text; //text of the answer
    private boolean isCorrect; //indicate whether the answer is right or wrong

    public Answer(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
