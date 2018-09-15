package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.TestSession;
import sample.models.UserList;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button signup_btn; //Sign up button
    @FXML
    private Button login_btn; //Login button
    @FXML
    private TextField id_edit; //Student ID text edit field
    @FXML
    private TextField pin_edit; //PIN text edit field

    /**
     * Check user's credential display message
     * @param e type of event user interacts with the button
     */
    @FXML
    protected void loginClick(ActionEvent e) {
        String id = id_edit.getText();
        String pin = pin_edit.getText();

        //check if any field is left empty
        if (id.length() == 0 || pin.length() ==0) {
            MessageBox.show("Both fields required", "Missing data");
        }
        //if incorrect login details are provided
        else if (!UserList.getInstance().checkCredential(id, pin)) {
            MessageBox.show("ID, PIN is incorrect or your account is expired", "Invalid Credential");
        }
        else
            switchToTest();
    }

    /**
     * Switch scene when user clicks register
     * @param e type of event user interacts with the button
     */
    @FXML
    protected void signupClick(ActionEvent e) {
        Stage currentStage = (Stage) signup_btn.getScene().getWindow();
        SceneSwitcher.getInstance().switchScene("../scenes/register.fxml", currentStage);
    }

    /**
     * Switch to test Dashboard
     */
    private void switchToTest() {
        Stage currentStage = (Stage) signup_btn.getScene().getWindow();
        SceneSwitcher.getInstance().switchScene("../scenes/tests.fxml", currentStage);
    }
}
