package cbs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class employeeSceneController {

    @FXML
    Button logOutButton;

    @FXML
    Label employeeViewLabel;

    public void logOutButtonClicked(ActionEvent event) throws IOException{
        Main.setCurrentUser(null);
        Main.setEmployeeMode(false);

        sceneCreator.createScene("loginPage.fxml");

    }
}
