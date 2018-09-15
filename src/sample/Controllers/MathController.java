package sample.Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.models.*;

import java.util.ArrayList;


public class MathController {
    @FXML
    private Label question_text; //The text of the question
    @FXML
    private Label difficulty_lbl; //The difficult of the current question
    @FXML
    private Label score_lbl; //user's current score
    @FXML
    private Button answerA;
    @FXML
    private Button answerB;
    @FXML
    private Button answerC;
    @FXML
    private Button answerD;
    @FXML
    private Button next_btn;
    @FXML
    private Button done_btn;

    private MathQuestion currentQuestion; //the Question being displayed
    private boolean isAnswerRight; //indicate if the user gets it right in previous question
    private int numQuestionsAnswered; //indicate the total number of math questions answered
    private User currentUser = TestSession.getInstance().getCurrentUser(); //The user currently taking the test
    private ArrayList<MathQuestion> answeredQuestions = new ArrayList<>(); /* All the questions answered by user. This list is
    to avoid generating duplicate questions */

    private TestsController parentController;

    private final int SCORE_TOUGH = 2;
    private final int SCORE_TOUGHER = 5;
    private final int SCORE_TOUGHEST = 10;


    @FXML
    public void initialize() {
        numQuestionsAnswered = 0;
        currentQuestion = QuestionBank.getInstance().generateRandomQuestion(QuestionDifficulty.TOUGH); //First question should be easiest
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
        question_text.setText(currentQuestion.getText());
        difficulty_lbl.setText(currentQuestion.getDifficulty().toString());
        score_lbl.setText("Current Score: " + Integer.toString(currentUser.getScoreMathTest()));
        answerA.setText(currentQuestion.getPossibleAnswers().get(0).getText());
        answerB.setText(currentQuestion.getPossibleAnswers().get(1).getText());
        answerC.setText(currentQuestion.getPossibleAnswers().get(2).getText());
        answerD.setText(currentQuestion.getPossibleAnswers().get(3).getText());
    }

    /**
     * Register event handler for buttons
     */
    private void registerButtons() {
        answerA.setOnAction(e -> {
            performTaskAnswerButtons(answerA);
        });
        answerB.setOnAction(e -> {
            performTaskAnswerButtons(answerB);
        });
        answerC.setOnAction(e -> {
            performTaskAnswerButtons(answerC);
        });
        answerD.setOnAction(e -> {
            performTaskAnswerButtons(answerD);
        });
        next_btn.setOnAction(e -> {
            switchStatusAllButtons(false); //enable all buttons
            resetStyleAllButtons(); //Change style all buttons to default
            setStatusNextButton(true); //disable next button
            currentQuestion = generateNextQuestion();
            parseQuestion();
        });
        done_btn.setOnAction(e -> {
            parentController.finishMathTest();
        });
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
    }

    /**
     * Check if an answer is right and change appearance of buttons
     * @param button button clicked
     */
    private void checkAnswer(Button button) {
        if (button.getText().equals(currentQuestion.getCorrectAnswer())){
            isAnswerRight = true;
            addScoreToUser();
            score_lbl.setText("Current Score: " + Integer.toString(currentUser.getScoreMathTest()));
            button.setStyle("-fx-background-color: green; -fx-text-fill: white");
        }
        else {
            isAnswerRight = false;
            button.setStyle("-fx-background-color: red; -fx-text-fill: white");
        }
        switchStatusAllButtons(true);
    }

    /**
     * Change status of answer buttons (on or off)
     * @param isDisable true of buttons should be disabled
     */
    private void switchStatusAllButtons(boolean isDisable) {
        answerA.setDisable(isDisable);
        answerB.setDisable(isDisable);
        answerC.setDisable(isDisable);
        answerD.setDisable(isDisable);
    }

    /**
     * Add score to user if the answer is right
     */
    private void addScoreToUser() {
        switch (currentQuestion.getDifficulty()) {
            case TOUGH:
                currentUser.addScoreMathTest(SCORE_TOUGH); //+2 if the question is TOUGH
                break;
            case TOUGHER:
                currentUser.addScoreMathTest(SCORE_TOUGHER); //+5 if the question is TOUGHER
                break;
            case TOUGHEST:
                currentUser.addScoreMathTest(SCORE_TOUGHEST); //+10 if the question is TOUGHEST
                break;
        }
    }

    /**
     * Reset style of all buttons to default
     */
    private void resetStyleAllButtons() {
        answerA.setStyle("");
        answerB.setStyle("");
        answerC.setStyle("");
        answerD.setStyle("");
    }

    /**
     * Switch on/off the next button
     * @param isOff true if next button is disabled, false if next button is available
     */
    private void setStatusNextButton(boolean isOff) {
        next_btn.setDisable(isOff);
    }

    /**
     * Generate next question that is not answered by the user before
     * @return question from the question bank
     */
    private MathQuestion generateNextQuestion() {
        MathQuestion result = QuestionBank.getInstance().generateRandomQuestion(nextQuestionDifficulty());
        if (!answeredQuestions.contains(result)) {
            answeredQuestions.add(result);
            return result;
        }
        return generateNextQuestion();
    }

    /**
     * Define the difficulty of next question
     * @return the difficulty of next question
     */
    private QuestionDifficulty nextQuestionDifficulty() {
        switch (currentQuestion.getDifficulty()) {
            case TOUGH:
                if (isAnswerRight)
                    return QuestionDifficulty.TOUGHER;
                return QuestionDifficulty.TOUGH;
            case TOUGHER:
                if (isAnswerRight)
                    return QuestionDifficulty.TOUGHEST;
                return QuestionDifficulty.TOUGH;
            default:
                if (isAnswerRight)
                    return QuestionDifficulty.TOUGHEST;
                return QuestionDifficulty.TOUGHER;
        }
    }
}
