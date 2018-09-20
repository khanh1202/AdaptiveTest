package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sample.models.*;

import java.io.File;
import java.util.ArrayList;

public class ListeningCategory implements TestCategory {
    @FXML
    private Label difficulty_lbl;
    @FXML
    private Label question_lbl;
    @FXML
    private Button play_btn;
    @FXML
    private Button answera_btn;
    @FXML
    private Button answerb_btn;
    @FXML
    private Button answerc_btn;
    @FXML
    private Button answerd_btn;
    @FXML
    private Button next_btn;
    @FXML
    private Button done_btn;
    @FXML
    private Label score_lbl;
    @FXML
    private Button opt_btn;

    private ListeningQuestion currentQuestion; //the Question being displayed
    private boolean isAnswerRight; //indicate if the user gets it right in previous question
    private int numQuestionsAnswered; //indicate the total number of math questions answered
    private User currentUser = TestSession.getInstance().getCurrentUser(); //The user currently taking the test
    private ArrayList<ListeningQuestion> answeredQuestions = new ArrayList<>(); /* All the questions answered by user. This list is
    to avoid generating duplicate questions */
    private boolean hasUserOptedForExtraAttempt = false; //indicate whether user has used the privilege to take an extra attempt. Scores will be deducted for second attempt

    private static final int SCORE_TOUGH = 2;
    private static final int SCORE_TOUGHER = 5;
    private static final int SCORE_TOUGHEST = 10;

    private TestsController parentController;

    @FXML
    public void initialize() {
        numQuestionsAnswered = 0;
        currentQuestion = QuestionBank.getInstance().generateRandomListeningQuestion(QuestionDifficulty.TOUGH); //First question should be easiest
        answeredQuestions.add(currentQuestion);
        parseQuestion();
        registerButtons();
        setStatusNextButton(true);
    }

    public void setParentController(TestsController parentController) {
        this.parentController = parentController;
    }

    /**
     * Parse question text and answers to buttons and labels
     */
    private void parseQuestion() {
        question_lbl.setText(currentQuestion.getText());
        difficulty_lbl.setText(currentQuestion.getDifficulty().toString());
        score_lbl.setText("Current Score: " + Integer.toString(currentUser.getScoreListeningTest()));
        answera_btn.setText(currentQuestion.getPossibleAnswers().get(0).getText());
        answerb_btn.setText(currentQuestion.getPossibleAnswers().get(1).getText());
        answerc_btn.setText(currentQuestion.getPossibleAnswers().get(2).getText());
        answerd_btn.setText(currentQuestion.getPossibleAnswers().get(3).getText());
    }

    /**
     * Register event handler for buttons
     */
    private void registerButtons() {
        answera_btn.setOnAction(e -> {
            performTaskAnswerButtons(answera_btn);
        });
        answerb_btn.setOnAction(e -> {
            performTaskAnswerButtons(answerb_btn);
        });
        answerc_btn.setOnAction(e -> {
            performTaskAnswerButtons(answerc_btn);
        });
        answerd_btn.setOnAction(e -> {
            performTaskAnswerButtons(answerd_btn);
        });
        next_btn.setOnAction(e -> {
            switchStatusAllButtons(false); //enable all buttons
            resetStyleAllButtons(); //Change style all buttons to default
            setStatusNextButton(true); //disable next button
            currentQuestion = generateNextQuestion();
            parseQuestion();
        });
        opt_btn.setOnAction(e -> {
            resetTest();
            opt_btn.setDisable(true);
            hasUserOptedForExtraAttempt = true;
        });
        play_btn.setOnAction(e -> {
            playMedia();
        });
        done_btn.setOnAction(e -> {
            if (hasUserOptedForExtraAttempt)
                currentUser.addScoreListeningTest(-5); //deduct 5pts to user if extra attempt is resorted
            finishTest();
        });
    }

    /**
     * Play audio file
     */
    private void playMedia() {
        Media media = new Media(new File(currentQuestion.getAudioURL()).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.play();
    }

    /**
     * Do necessary tasks for the answer buttons
     * @param button the answer button
     */
    private void performTaskAnswerButtons(Button button) {
        checkAnswer(button);
        numQuestionsAnswered++;
        if (numQuestionsAnswered < 5) //if user has not answered 5 questions
            setStatusNextButton(false); //enable next button
        else
        {
            if (isEligibleToOpt() && !hasUserOptedForExtraAttempt) {
                opt_btn.setDisable(false);
            }
        }
    }

    /**
     * Check if an answer is right and change appearance of buttons
     * @param button button clicked
     */
    private void checkAnswer(Button button) {
        if (button.getText().equals(currentQuestion.getCorrectAnswer())){
            isAnswerRight = true;
            addScoreToUser();
            score_lbl.setText("Current Score: " + Integer.toString(currentUser.getScoreListeningTest()));
            button.setStyle("-fx-background-color: green; -fx-text-fill: white");
        }
        else {
            isAnswerRight = false;
            button.setStyle("-fx-background-color: red; -fx-text-fill: white");
        }
        switchStatusAllButtons(true);
    }

    /**
     * Add score to user if the answer is right
     */
    private void addScoreToUser() {
        switch (currentQuestion.getDifficulty()) {
            case TOUGH:
                currentUser.addScoreListeningTest(SCORE_TOUGH); //+2 if the question is TOUGH
                break;
            case TOUGHER:
                currentUser.addScoreListeningTest(SCORE_TOUGHER); //+5 if the question is TOUGHER
                break;
            case TOUGHEST:
                currentUser.addScoreListeningTest(SCORE_TOUGHEST); //+10 if the question is TOUGHEST
                break;
        }
    }

    /**
     * Change status of answer buttons (on or off)
     * @param isDisable true of buttons should be disabled
     */
    private void switchStatusAllButtons(boolean isDisable) {
        answera_btn.setDisable(isDisable);
        answerb_btn.setDisable(isDisable);
        answerc_btn.setDisable(isDisable);
        answerd_btn.setDisable(isDisable);
    }

    /**
     * Switch on/off the next button
     * @param isOff true if next button is disabled, false if next button is available
     */
    private void setStatusNextButton(boolean isOff) {
        next_btn.setDisable(isOff);
    }

    /**
     * Reset style of all buttons to default
     */
    private void resetStyleAllButtons() {
        answera_btn.setStyle("");
        answerb_btn.setStyle("");
        answerc_btn.setStyle("");
        answerd_btn.setStyle("");
    }

    /**
     * Generate next question that is not answered by the user before
     * @return question from the question bank
     */
    private ListeningQuestion generateNextQuestion() {
        ListeningQuestion result = QuestionBank.getInstance().generateRandomListeningQuestion(DifficultyGenerator.nextQuestionDifficulty(
                currentQuestion, isAnswerRight
        ));
        if (!answeredQuestions.contains(result)) {
            answeredQuestions.add(result);
            return result;
        }
        return generateNextQuestion();
    }

    /**
     * Reset the test as if it is newly accessed
     */
    private void resetTest() {
        numQuestionsAnswered = 0; //user starts from scratch
        currentUser.setScoreListeningTest(0); //reset user score in this test
        answeredQuestions.clear();
        currentQuestion = QuestionBank.getInstance().generateRandomListeningQuestion(QuestionDifficulty.TOUGH); //First question should be easiest
        answeredQuestions.add(currentQuestion);
        parseQuestion();
        switchStatusAllButtons(false);
        resetStyleAllButtons();
    }

    /**
     * check if user is eligible for a second attempt
     * @return true if listening score of user exceeds 10
     */
    private boolean isEligibleToOpt() {
        return currentUser.getScoreListeningTest() >= 10;
    }

    /**
     * Tell parent controller to close the test category
     */
    public void finishTest() {
        parentController.finishListeningTest();
    }
}
