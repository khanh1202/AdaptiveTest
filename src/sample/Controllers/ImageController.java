package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.models.*;

import java.util.ArrayList;

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

    private ImageQuestion currentQuestion; //the question the user is being asked
    private int numQuestionsAsked; //total number of questions asked
    private ArrayList<ImageQuestion> questionsAsked = new ArrayList<>(); //list of all questions asked. It is used to avoid duplication
    private User currentUser = TestSession.getInstance().getCurrentUser(); //the user doing the test
    private boolean isAnswerRight; //indicate if user gets it right in previous question

    private static final int SCORE_TOUGH = 2;
    private static final int SCORE_TOUGHER = 5;
    private static final int SCORE_TOUGHEST = 10;

    private TestsController parentController;


    @FXML
    public void initialize() {
        numQuestionsAsked = 0;
        currentQuestion = QuestionBank.getInstance().generateRandomImageQuestion(QuestionDifficulty.TOUGH);
        questionsAsked.add(currentQuestion);
        parseQuestion();
        registerActions();
        setStatusNextButton(true);
    }

    public void setParentController(TestsController testsController) {
        this.parentController = testsController;
    }

    /**
     * Set the text to all labels and url of the image depending on the current question
     */
    private void parseQuestion() {
        difficulty_lbl.setText(currentQuestion.getDifficulty().toString());
        question_img.setImage(new Image(currentQuestion.getImageUrl()));
        question_lbl.setText(currentQuestion.getText());
        score_lbl.setText("Current Score: " + Integer.toString(currentUser.getScoreImageTest()));
    }

    /**
     * Add event handler to some node objects
     */
    private void registerActions() {
        question_img.setOnMouseClicked(e -> {
            if (e.getX() >= currentQuestion.getCorrectXFrom() && e.getX() <= currentQuestion.getCorrectXTo() &&
                    e.getY() >= currentQuestion.getCorrectYFrom() && e.getY() <= currentQuestion.getCorrectYTo()) { /* If
                    user clicks on the region inside the valid rectangle */
                isAnswerRight = true;
                addScoreToUser();
                score_lbl.setText("Current Score: " + Integer.toString(currentUser.getScoreImageTest()));
            }
            else
                isAnswerRight = false;
            switchStatusImage(true); //make image available to be clicked
            numQuestionsAsked++;
            if (numQuestionsAsked < 5) //if user has not answered 5 questions
                setStatusNextButton(false); //enable next button
        });
        next_btn.setOnAction(e -> {
            switchStatusImage(false); //re-enable the image click
            setStatusNextButton(true); //disable next button
            currentQuestion = generateNextQuestion();
            parseQuestion();
        });
        done_btn.setOnAction(e -> {
            parentController.finishImageTest();
        });
    }

    private void switchStatusImage(boolean isOff) {
        question_img.setDisable(isOff);
    }

    /**
     * Add score to user if the answer is right
     */
    private void addScoreToUser() {
        switch (currentQuestion.getDifficulty()) {
            case TOUGH:
                currentUser.addScoreImageTest(SCORE_TOUGH); //+2 if the question is TOUGH
                break;
            case TOUGHER:
                currentUser.addScoreImageTest(SCORE_TOUGHER); //+5 if the question is TOUGHER
                break;
            case TOUGHEST:
                currentUser.addScoreImageTest(SCORE_TOUGHEST); //+10 if the question is TOUGHEST
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
     * Generate next question that is not answered by the user before
     * @return question from the question bank
     */
    private ImageQuestion generateNextQuestion() {
        ImageQuestion result = QuestionBank.getInstance().generateRandomImageQuestion(
                DifficultyGenerator.nextQuestionDifficulty(currentQuestion, isAnswerRight)
        );
        if (!questionsAsked.contains(result)) {
            questionsAsked.add(result);
            return result;
        }
        return generateNextQuestion();
    }
}
