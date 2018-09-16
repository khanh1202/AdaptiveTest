package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import sample.models.TestSession;
import sample.models.User;
import sample.models.UserList;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Date;

public class RegisterController {
    @FXML
    private Button register_btn;
    @FXML
    private Button login_btn;
    @FXML
    private TextField id_edit;
    @FXML
    private TextField name_edit;
    @FXML
    private TextField school_edit;

    /**
     * function executed when user clicks register
     * @param event the event that triggered the method
     */
    @FXML
    protected void registerClick(ActionEvent event) {
        String id = id_edit.getText();
        String name = name_edit.getText();
        String school = school_edit.getText();

        if (id.length() == 0 || name.length() == 0) {
            MessageBox.show("ID and Name are required", "Data Missing");
        }
        else if (UserList.getInstance().checkIDAlreadyExisted(id)) { //check if the ID has already existed
            MessageBox.show("This ID has existed in the system", "Error registering");
        }
        else { //create a new user and switch scene
            User newUser = new User(name, school, id, generatePIN(), new Date());
            UserList.getInstance().addUser(newUser);
            MessageBox.showAndWait("Congratulations! You registration is successful. Your PIn is " + newUser.getCredential().getPin(), "Successful");
            TestSession.getInstance().setUser(newUser);
            switchToTest();
        }
    }

    /**
     * Change to Login scene when Login is clicked
     * @param event the type of event the user interacts with the button
     */
    @FXML
    protected void loginClick(ActionEvent event) {
        Stage currentStage = (Stage) register_btn.getScene().getWindow();
        SceneSwitcher.getInstance().switchScene("../scenes/login.fxml", currentStage);
    }

    /**
     * Create a random 5-digit PIN
     * @return a string format of the PIN
     */
    private String generatePIN() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);
        String numFormatted = String.format("%05d", num);
        return numFormatted;
    }

    /**
     * Switch scene to the Dashboard
     */
    private void switchToTest() {
        Stage currentStage = (Stage) register_btn.getScene().getWindow();
        SceneSwitcher.getInstance().switchScene("../scenes/tests.fxml", currentStage);
    }
}
