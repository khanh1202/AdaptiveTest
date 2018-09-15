package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    @FXML
    private Button signup_btn;

    @FXML
    private Button login_btn;

    @FXML
    protected void signupClick(ActionEvent event) {
        Stage currentStage = (Stage) signup_btn.getScene().getWindow();
        SceneSwitcher.getInstance().switchScene("../scenes/register.fxml", currentStage);
    }

    @FXML
    protected void loginClick(ActionEvent event) {
        Stage currentStage = (Stage) signup_btn.getScene().getWindow();
        SceneSwitcher.getInstance().switchScene("../scenes/login.fxml", currentStage);
    }
}
