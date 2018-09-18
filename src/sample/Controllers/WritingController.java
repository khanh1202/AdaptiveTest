package sample.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import sample.models.*;

public class WritingController {
    @FXML
    private Label difficulty_lbl;
    @FXML
    private Label question_lbl;
    @FXML
    private TextArea sentence_edit;
    @FXML
    private Label error_lbl;
    @FXML
    private Label score_lbl;
    @FXML
    private Button check_btn;
    @FXML
    private Button next_btn;
    @FXML
    private Button done_btn;

    private WritingQuestion currentQuestion; //the Question being displayed
    private boolean isAnswerRight; //indicate if the user gets it right in previous question
    private int numQuestionsAnswered; //indicate the total number of math questions answered
    private User currentUser = TestSession.getInstance().getCurrentUser(); //The user currently taking the test

    private static final int SCORE_TOUGH = 2;
    private static final int SCORE_TOUGHER = 5;
    private static final int SCORE_TOUGHEST = 10;

    private TestsController parentController;

    @FXML
    public void initialize() {
        numQuestionsAnswered = 0;
        currentQuestion = QuestionBank.getInstance().generateWritingQuestion(QuestionDifficulty.TOUGH); //First question should be easiest
        parseQuestion();
        registerButtons();
        setStatusNextButton(true);
    }

    public void setParentController(TestsController testsController) {
        this.parentController = testsController;
    }

    /**
     * Parse question text and answers to buttons and labels
     */
    private void parseQuestion() {
        question_lbl.setText(currentQuestion.getText());
        difficulty_lbl.setText(currentQuestion.getDifficulty().toString());
        score_lbl.setText("Current Score: " + Integer.toString(currentUser.getScoreWritingTest()));
    }

    /**
     * Register event handler for buttons
     */
    private void registerButtons() {
        check_btn.setOnAction(e -> {
            checkAnswer();
            numQuestionsAnswered++;
            if (numQuestionsAnswered < 5)
                setStatusNextButton(false);
        });
        next_btn.setOnAction(e -> {
            switchStatusCheckButton(false); //enable check buttons
            resetStyleCheckButton(); //Change style all buttons to default
            setStatusNextButton(true); //disable next button
            currentQuestion = generateNextQuestion();
            parseQuestion();
        });
        done_btn.setOnAction(e -> {
            parentController.finishWritingTest();
        });
        sentence_edit.textProperty().addListener(new ChangeListener<String>() { //listen to changes on text area
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String message = "";
                if (!GrammarChecker.isSimpleSentence(newValue))
                    message += "Spaces, Full stop,";
                if (!GrammarChecker.isCompoundSentence(newValue))
                    message += "Conjunctions,";
                if (!GrammarChecker.isComplexSentence(newValue))
                    message += "Relative Pronouns";
                if (message.equals("")) {
                    error_lbl.setStyle("-fx-text-fill: green");
                    error_lbl.setText("Perfect Sentence");
                }
                else {
                    error_lbl.setStyle("-fx-text-fill: #bc1e1e");
                    error_lbl.setText("Things for complex sentence: " + message);
                }
            }
        });
    }

    private boolean isAnswerRight() {
        switch (currentQuestion.getDifficulty()) {
            case TOUGH:
                return GrammarChecker.isSimpleSentence(sentence_edit.getText());
            case TOUGHER:
                return GrammarChecker.isCompoundSentence(sentence_edit.getText());
            default:
                return GrammarChecker.isComplexSentence(sentence_edit.getText());
        }
    }

    /**
     * Check the answer of the user and change appearance of buttons
     */
    private void checkAnswer() {
        if (isAnswerRight()) {
            isAnswerRight = true;
            addScoreToUser();
            score_lbl.setText("Current Score: " + Integer.toString(currentUser.getScoreWritingTest()));
            check_btn.setStyle("-fx-background-color: green; -fx-text-fill: white");
        }
        else {
            isAnswerRight = false;
            check_btn.setStyle("-fx-background-color: red; -fx-text-fill: white");
        }
        switchStatusCheckButton(true);
    }

    /**
     * Add score to user if the answer is right
     */
    private void addScoreToUser() {
        switch (currentQuestion.getDifficulty()) {
            case TOUGH:
                currentUser.addScoreWritingTest(SCORE_TOUGH); //+2 if the question is TOUGH
                break;
            case TOUGHER:
                currentUser.addScoreWritingTest(SCORE_TOUGHER); //+5 if the question is TOUGHER
                break;
            case TOUGHEST:
                currentUser.addScoreWritingTest(SCORE_TOUGHEST); //+10 if the question is TOUGHEST
                break;
        }
    }

    /**
     * Change if the "check answer" button is disabled or not
     * @param isOff false if button is available
     */
    private void switchStatusCheckButton(boolean isOff) {
        check_btn.setDisable(isOff);
    }

    /**
     * Switch on/off the next button
     * @param isOff true if next button is disabled, false if next button is available
     */
    private void setStatusNextButton(boolean isOff) {
        next_btn.setDisable(isOff);
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
    private WritingQuestion generateNextQuestion() {
        return QuestionBank.getInstance().generateWritingQuestion(DifficultyGenerator.nextQuestionDifficulty(
                currentQuestion, isAnswerRight
        ));
    }
}
