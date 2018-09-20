package sample.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import sample.models.*;

import java.io.File;
import java.util.ArrayList;


public class SpellingCategory implements TestCategory {
    @FXML
    private Label difficulty_lbl;
    @FXML
    private Label score_lbl;
    @FXML
    private Button play_btn;
    @FXML
    private TextField answer_txt;
    @FXML
    private Button check_btn;
    @FXML
    private Button next_btn;
    @FXML
    private Button done_btn;
    @FXML
    private Label timer_lbl;

    private SpellingQuestion currentQuestion; //the Question being displayed
    private boolean isAnswerRight; //indicate if the user gets it right in previous question
    private int numQuestionsAnswered; //indicate the total number of math questions answered
    private User currentUser = TestSession.getInstance().getCurrentUser(); //The user currently taking the test
    private ArrayList<SpellingQuestion> answeredQuestions = new ArrayList<>(); /* All the questions answered by user. This list is
    to avoid generating duplicate questions */
    private int currentTimer;
    private Timeline time;
    private boolean isHintUsed = false;

    private static final int SCORE_TOUGH = 2;
    private static final int SCORE_TOUGHER = 5;
    private static final int SCORE_TOUGHEST = 10;
    private static final int TIME_ALLOCATED  = 10;

    private TestsController parentController;

    @FXML
    public void initialize() {
        numQuestionsAnswered = 0;
        currentTimer = TIME_ALLOCATED;
        currentQuestion = QuestionBank.getInstance().generateRandomSpellingQuestion(QuestionDifficulty.TOUGH); //First question should be easiest
        answeredQuestions.add(currentQuestion);
        parseQuestion();
        registerButtons();
        setStatusNextButton(true);
    }

    public void setParentController(TestsController testsController) {
        this.parentController = testsController;
    }

    /**
     * Parse question difficulty and score to buttons and labels
     */
    private void parseQuestion() {
        difficulty_lbl.setText("Question " + Integer.toString(numQuestionsAnswered + 1) + " (" + currentQuestion.getDifficulty().toString() + ")");
        score_lbl.setText("Current Score: " + Integer.toString(currentUser.getScoreSpellingTest()));
    }

    /**
     * Register event handler for buttons
     */
    private void registerButtons() {
        play_btn.setOnAction(e -> {
            startTimer();
            playMedia();
        });
        check_btn.setOnAction(e -> {
            checkAnswer();
            if (time != null)
                time.stop(); //stop the timer
            timer_lbl.setText(""); //hide the label
            numQuestionsAnswered++;
            if (numQuestionsAnswered < 5)
                setStatusNextButton(false);
        });
        next_btn.setOnAction(e -> {
            switchStatusCheckButton(false); //enable check buttons
            resetStyleCheckButton(); //Change style all buttons to default
            setStatusNextButton(true); //disable next button
            currentTimer = TIME_ALLOCATED; //reset the timer
            answer_txt.setText(""); //clear the content of the answer field
            currentQuestion = generateNextQuestion();
            parseQuestion();
            isHintUsed = false;
        });
        done_btn.setOnAction(e -> {
            finishTest();
        });
    }

    /**
     * Check the answer of the user and change appearance of buttons
     */
    private void checkAnswer() {
        if (answer_txt.getText().equals(currentQuestion.getCorrectAnswer())) {
            isAnswerRight = true;
            addScoreToUser();
            score_lbl.setText("Current Score: " + Integer.toString(currentUser.getScoreSpellingTest()));
            check_btn.setStyle("-fx-background-color: green; -fx-text-fill: white");
        }
        else {
            isAnswerRight = false;
            check_btn.setStyle("-fx-background-color: red; -fx-text-fill: white");
        }
        switchStatusCheckButton(true);
    }

    /**
     * Play audio to user
     */
    private void playMedia() {
        Media media = new Media(new File(currentQuestion.getPathMediaFile()).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.play();
    }

    /**
     * Start the timer when user clicks playing audio
     */
    private void startTimer() {
        if (currentTimer >= TIME_ALLOCATED) {
            time = new Timeline();
            time.setCycleCount(Timeline.INDEFINITE);
            KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    currentTimer--;
                    timer_lbl.setText(Integer.toString(currentTimer));
                    if (currentTimer <= 5) //when the time is about to run out
                        timer_lbl.setStyle("-fx-text-fill: red");
                    else
                        timer_lbl.setStyle("-fx-text-fill: #336699");
                    if (currentTimer <= 0) { //when the time runs out
                        time.stop();
                        MessageBox.showForSeconds(currentQuestion.getCorrectAnswer().substring(0, 3),
                                "First 3 characters of the answer", 5); //popup a window giving user a clue of first 3 characters
                        timer_lbl.setText("");
                        isHintUsed = true;
                    }
                }
            });
            time.getKeyFrames().add(frame);
            time.playFromStart();
        }

    }

    /**
     * Add score to user if the answer is right
     */
    private void addScoreToUser() {
        switch (currentQuestion.getDifficulty()) {
            case TOUGH:
                if (isHintUsed) //if user resorts to popup hint (first 3 characters)
                    currentUser.addScoreSpellingTest(SCORE_TOUGH/2); //+1 if the question is TOUGH
                else
                    currentUser.addScoreSpellingTest(SCORE_TOUGH); //+2 if the question is TOUGH
                break;
            case TOUGHER:
                if (isHintUsed)
                    currentUser.addScoreSpellingTest(SCORE_TOUGHER/2); //+2 if the question is TOUGHER
                else
                    currentUser.addScoreSpellingTest(SCORE_TOUGHER); //+5 if the question is TOUGHER
                break;
            case TOUGHEST:
                if (isHintUsed)
                    currentUser.addScoreSpellingTest(SCORE_TOUGHEST/2); //+5 if the question is TOUGHEST
                else
                    currentUser.addScoreSpellingTest(SCORE_TOUGHEST); //+10 if the question is TOUGHEST
                break;
        }
    }

    /**
     * Switch on/off the next button
     * @param isOff true if next button is disabled, false if next button is available
     */
    private void setStatusNextButton(boolean isOff) {
        next_btn.setDisable(isOff);
    }

    /**
     * Change if the check answer button is disabled or not
     * @param isOff false if button is available
     */
    private void switchStatusCheckButton(boolean isOff) {
        check_btn.setDisable(isOff);
    }

    /**
     * Eliminate all colors and styles of check button
     */
    private void resetStyleCheckButton() {
        check_btn.setStyle("");
    }

    /**
     * Generate next question that is not answered by the user before
     * @return question from the question bank
     */
    private SpellingQuestion generateNextQuestion() {
        SpellingQuestion result = QuestionBank.getInstance().generateRandomSpellingQuestion(DifficultyGenerator.nextQuestionDifficulty(
                currentQuestion, isAnswerRight
        ));
        if (!answeredQuestions.contains(result)) {
            answeredQuestions.add(result);
            return result;
        }
        return generateNextQuestion();
    }

    /**
     * Tell parent controller to close the test category
     */
    public void finishTest() {
        parentController.finishSpellingTest();
    }

}
