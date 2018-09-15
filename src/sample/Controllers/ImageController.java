package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.models.ImageQuestion;
import sample.models.QuestionBank;
import sample.models.QuestionDifficulty;

public class ImageController {
    @FXML
    private Label difficulty_lbl;
    @FXML
    private Label question_lbl;
    @FXML
    private ImageView question_img;
    @FXML
    private Label score_lbl;
    @FXML
    private Button next_btn;
    @FXML
    private Button done_btn;

    private ImageQuestion currentQuestion;

    @FXML
    public void initialize() {
        currentQuestion = QuestionBank.getInstance().imageQuestions.get(2);
        difficulty_lbl.setText(currentQuestion.getDifficulty().toString());
        question_img.setImage(new Image(currentQuestion.getImageUrl()));
        question_lbl.setText(currentQuestion.getText());
        question_img.setOnMouseClicked(e -> {
            if (e.getX() >= currentQuestion.getCorrectXFrom() && e.getX() <= currentQuestion.getCorrectXTo() &&
                    e.getY() >= currentQuestion.getCorrectYFrom() && e.getY() <= currentQuestion.getCorrectYTo())
                System.out.println("True");
            else
                System.out.println("False");
        });
    }
}
