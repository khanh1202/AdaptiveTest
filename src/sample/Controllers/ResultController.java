package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.models.TestSession;
import sample.models.User;

public class ResultController {
    @FXML
    private Label math_lbl;
    @FXML
    private Label image_lbl;
    @FXML
    private Label spelling_lbl;
    @FXML
    private Label listening_lbl;
    @FXML
    private Label writing_lbl;
    @FXML
    private Label total_lbl;

    private User currentUser;

    @FXML
    public void initialize() {
        currentUser = TestSession.getInstance().getCurrentUser();
        math_lbl.setText("Math: " + Integer.toString(currentUser.getScoreMathTest()));
        image_lbl.setText("Recognizing Critical Info: " + Integer.toString(currentUser.getScoreImageTest()));
        spelling_lbl.setText("Spelling: " + Integer.toString(currentUser.getScoreSpellingTest()));
        listening_lbl.setText("Listening: " + Integer.toString(currentUser.getScoreListeningTest()));
        writing_lbl.setText("Writing: " + Integer.toString(currentUser.getScoreWritingTest()));
        total_lbl.setText("Total: " + Integer.toString(currentUser.getTotalScore()));
    }
}
