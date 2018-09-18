package sample.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.models.TestSession;
import sample.models.User;

import java.io.IOException;

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
    private ImageController imageController;
    private SpellingController spellingController;
    private ListeningController listeningController;
    private WritingController writingController;

    private final Integer duration = 20*60; //duration of the test is 20mins
    private Integer currentTime = duration; //the current time of the timer, initially set at 20mins
    private User currentUser;

    @FXML
    public void initialize() {
        currentUser = TestSession.getInstance().getCurrentUser();
        setTextToButtons();
        switchStatusAllButtons(currentUser.isHasFinishedMath(), currentUser.isHasFinishImage(), currentUser.isHasFinishSpelling(),
                currentUser.isHasFinishListening(), currentUser.isHasFinishWriting());
    }

    private void setTextToButtons() {
        math_btn.setText("Math (" + Integer.toString(currentUser.getScoreMathTest()) + ")");
        rec_btn.setText("Recog (" + Integer.toString(currentUser.getScoreImageTest()) + ")");
        spell_btn.setText("Spell (" + Integer.toString(currentUser.getScoreSpellingTest()) + ")");
        lis_btn.setText("Listen (" + Integer.toString(currentUser.getScoreListeningTest()) + ")");
        write_btn.setText("Write (" + Integer.toString(currentUser.getScoreWritingTest()) + ")");
    }

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
        switchStatusAllButtons(true, true, true, true, true);
        loadMath();
    }

    @FXML
    protected void recTestClick(ActionEvent event) {
        switchStatusAllButtons(true, true, true, true , true);
        loadImageTest();
    }

    @FXML
    protected void spellingClick(ActionEvent event) {
        switchStatusAllButtons(true, true, true, true , true);
        loadSpellingTest();
    }

    @FXML
    protected void listeningClick(ActionEvent event) {
        switchStatusAllButtons(true, true, true, true , true);
        loadListeningTest();
    }

    @FXML
    protected void writingClick(ActionEvent event) {
        switchStatusAllButtons(true, true, true, true , true);
        loadWritingTest();
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
                    switchStatusAllButtons(false, false, false, false, false);
                }
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    /**
     * Enable/Disable all buttons so that the ongoing test cannot be interrupted
     */
    private void switchStatusAllButtons(boolean mathOff, boolean imageOff, boolean spellingOff, boolean listeningOff,
                                        boolean writingOff) {
        math_btn.setDisable(mathOff);
        rec_btn.setDisable(imageOff);
        lis_btn.setDisable(listeningOff);
        spell_btn.setDisable(spellingOff);
        write_btn.setDisable(writingOff);
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
            imageController = loader.<ImageController>getController();
            imageController.setParentController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load Spelling test
     */
    public void loadSpellingTest() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/spelling_questions.fxml"));
            root.getChildren().add(loader.load());
            spellingController = loader.<SpellingController>getController();
            spellingController.setParentController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load Listening test
     */
    public void loadListeningTest() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/listening_questions.fxml"));
            root.getChildren().add(loader.load());
            listeningController = loader.<ListeningController>getController();
            listeningController.setParentController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load Writing test
     */
    public void loadWritingTest() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/writing_questions.fxml"));
            root.getChildren().add(loader.load());
            writingController = loader.<WritingController>getController();
            writingController.setParentController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Include logics when math test is done (disable Math button, remove math component from the scene)
     */
    public void finishMathTest() {
        currentUser.setHasFinishedMath(true);
        switchStatusAllButtons(currentUser.isHasFinishedMath(), currentUser.isHasFinishImage(), currentUser.isHasFinishSpelling(),
                currentUser.isHasFinishListening(), currentUser.isHasFinishWriting());
        root.getChildren().remove(root.lookup("#math_pane"));
        math_btn.setText("Math (" + Integer.toString(TestSession.getInstance().getCurrentUser().getScoreMathTest()) + ")");
    }

    /**
     * Include logics when Image test is done (disable Recognize button, remove test component from the scene)
     */
    public void finishImageTest() {
        currentUser.setHasFinishImage(true);
        switchStatusAllButtons(currentUser.isHasFinishedMath(), currentUser.isHasFinishImage(), currentUser.isHasFinishSpelling(),
                currentUser.isHasFinishListening(), currentUser.isHasFinishWriting());
        root.getChildren().remove(root.lookup("#image_pane"));
        rec_btn.setText("Recog (" + Integer.toString(TestSession.getInstance().getCurrentUser().getScoreImageTest()) + ")");
    }

    /**
     * Include logics when Spelling test is done (disable Spelling button, remove test component from the scene)
     */
    public void finishSpellingTest() {
        currentUser.setHasFinishSpelling(true);
        switchStatusAllButtons(currentUser.isHasFinishedMath(), currentUser.isHasFinishImage(), currentUser.isHasFinishSpelling(),
                currentUser.isHasFinishListening(), currentUser.isHasFinishWriting());
        root.getChildren().remove(root.lookup("#spelling_pane"));
        spell_btn.setText("Spell (" + Integer.toString(TestSession.getInstance().getCurrentUser().getScoreSpellingTest()) + ")");
    }

    /**
     * Include logics when Listening test is done (disable Listening button, remove test component from the scene)
     */
    public void finishListeningTest() {
        currentUser.setHasFinishListening(true);
        switchStatusAllButtons(currentUser.isHasFinishedMath(), currentUser.isHasFinishImage(), currentUser.isHasFinishSpelling(),
                currentUser.isHasFinishListening(), currentUser.isHasFinishWriting());
        root.getChildren().remove(root.lookup("#listening_pane"));
        lis_btn.setText("Listen (" + Integer.toString(TestSession.getInstance().getCurrentUser().getScoreListeningTest()) + ")");
    }

    /**
     * Include logics when Writing test is done (disable Spelling button, remove test component from the scene)
     */
    public void finishWritingTest() {
        currentUser.setHasFinishWriting(true);
        switchStatusAllButtons(currentUser.isHasFinishedMath(), currentUser.isHasFinishImage(), currentUser.isHasFinishSpelling(),
                currentUser.isHasFinishListening(), currentUser.isHasFinishWriting());
        root.getChildren().remove(root.lookup("#writing_pane"));
        write_btn.setText("Write (" + Integer.toString(TestSession.getInstance().getCurrentUser().getScoreWritingTest()) + ")");
    }

}
