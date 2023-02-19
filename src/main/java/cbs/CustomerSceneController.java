package cbs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class CustomerSceneController {

    @FXML
    Button logOutButton, viewFilmsButton;

    @FXML
    Label customerViewLabel;

    public void logOutButtonClicked(ActionEvent event) throws IOException {
        Main.setCurrentUser(null);
        Main.setEmployeeMode(false);
        SceneCreator.createScene("loginPage.fxml");

    }

    public void viewFilmsButtonClicked(ActionEvent event) throws IOException {

        SceneCreator.createScene("viewFilmsPage.fxml");

    }

    public void viewBookingsButtonClicked(ActionEvent event) throws IOException {

        SceneCreator.createScene("viewBookingsPage.fxml");

    }
}
