package sample.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.models.TestSession;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TestsController {
    @FXML
    private VBox root;
    @FXML
    private Button logout_btn;
    @FXML
    private Button math_btn; //math button
    @FXML
    private Button rec_btn; //recognizing critical information button
    @FXML
    private Button spell_btn; //spelling button
    @FXML
    private Button write_btn; //writing button
    @FXML
    private Button lis_btn; //listening button
    @FXML
    private Label timer_lbl;

    private MathController mathController;

    private final Integer duration = 10; //duration of the test is 20mins
    private Integer currentTime = duration; //the current time of the timer, initially set at 20mins

    /**
     * Log user out and switch scene to login screen
     * @param event the event type user interacts with the logout button
     */
    @FXML
    protected void logoutClick(ActionEvent event) {
        Stage currentStage = (Stage) logout_btn.getScene().getWindow();
        SceneSwitcher.getInstance().switchScene("../scenes/login.fxml", currentStage);
        TestSession.getInstance().endSession();
    }

    @FXML
    protected void mathTestClick(ActionEvent event) {
        startTimer();
        switchStatusAllButtons(true);
        loadMath();
    }

    @FXML
    protected void recTestClick(ActionEvent event) {
        switchStatusAllButtons(true);
        loadImageTest();
    }

    @FXML
    protected void spellingClick(ActionEvent event) {

    }

    @FXML
    protected void listeningClick(ActionEvent event) {

    }

    @FXML
    protected void writingClick(ActionEvent event) {

    }

    /**
     * Begin the test and start counting the timer
     */
    private void startTimer() {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentTime--;
                timer_lbl.setText(currentTime.toString());
                if (currentTime <= 5)
                    timer_lbl.setStyle("-fx-text-fill: red");
                if (currentTime <= 0) {
                    time.stop();
                    switchStatusAllButtons(false);
                }
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    /**
     * Enable/Disable all buttons so that the ongoing test cannot be interrupted
     */
    private void switchStatusAllButtons(boolean isDisable) {
        math_btn.setDisable(isDisable);
        rec_btn.setDisable(isDisable);
        lis_btn.setDisable(isDisable);
        spell_btn.setDisable(isDisable);
        write_btn.setDisable(isDisable);
    }

    /**
     * Load Math Test to the Tests screen
     */
    public void loadMath() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/math_questions.fxml"));
            root.getChildren().add(loader.load());
            mathController = loader.<MathController>getController();
            mathController.setParentController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load Recognizing Critical Information test
     */
    public void loadImageTest() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/image_questions.fxml"));
            root.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Include logics when math test is done (disable Math button, remove math component from the scene)
     */
    public void finishMathTest() {
        math_btn.setDisable(true);
        root.getChildren().remove(root.lookup("#math_pane"));
        math_btn.setText("Math (" + Integer.toString(TestSession.getInstance().getCurrentUser().getScoreMathTest()) + ")");
    }

}
