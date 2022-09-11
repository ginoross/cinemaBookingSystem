package cbs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class customerSceneController {

    @FXML
    Button logOutButton;

    @FXML
    Label customerViewLabel;

    public void logOutButtonClicked(ActionEvent event) throws IOException {
        Main.setCurrentUser(null);
        Main.setEmployeeMode(false);

        sceneCreator.createScene("loginPage.fxml");

    }
}
