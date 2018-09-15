package sample.models;

public class ImageQuestion extends Question {
    private String text;
    private String imageUrl;
    private double correctXFrom; //The correct X-coordinate of the image (under boundary)
    private double correctXTo; //The correct X-coordinate of the image (upper boundary)
    private double correctYFrom; //The correct Y-coordinate of the image (under boundary)
    private double correctYTo; //The correct Y-coordinate of the image (upper boundary)

    public ImageQuestion(QuestionDifficulty difficulty, String text, String imageUrl, double correctXFrom, double correctXTo, double correctYFrom, double correctYTo) {
        super(difficulty);
        this.text = text;
        this.imageUrl = imageUrl;
        this.correctXFrom = correctXFrom;
        this.correctXTo = correctXTo;
        this.correctYFrom = correctYFrom;
        this.correctYTo = correctYTo;
    }

    public String getText() {
        return text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getCorrectXFrom() {
        return correctXFrom;
    }

    public double getCorrectYFrom() {
        return correctYFrom;
    }

    public double getCorrectXTo() {
        return correctXTo;
    }

    public double getCorrectYTo() {
        return correctYTo;
    }
}
