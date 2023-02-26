package cbs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class EmployeeSceneController {

    @FXML
    Button logOutButton;

    @FXML
    Label employeeViewLabel;

    //loads the requested page dependent on user button press

    public void logOutButtonClicked(ActionEvent event) throws IOException {
        Main.setCurrentUser(null);
        Main.setEmployeeMode(false);

        SceneCreator.createScene("loginPage.fxml");

    }

    public void manageFilmsButtonClicked() throws IOException {

        SceneCreator.createScene("manageFilmsPage.fxml");

    }

    public void manageUsersButtonClicked() throws IOException {

        SceneCreator.createScene("viewUsersPage.fxml");

    }
}
