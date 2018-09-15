package sample.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    private static SceneSwitcher instance;
    private SceneSwitcher() {
    }

    public void switchScene(String file, Stage currentStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
        try {
            currentStage.setScene(new Scene(loader.load()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static SceneSwitcher getInstance() {
        if (instance == null)
            instance = new SceneSwitcher();
        return instance;
    }
}
