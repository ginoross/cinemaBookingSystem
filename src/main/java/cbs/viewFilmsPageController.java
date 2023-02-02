package cbs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class viewFilmsPageController {

    @FXML
    Button backButton;


    public void backButtonClicked(ActionEvent event) throws IOException {

        sceneCreator.createScene("manageFilmsPage.fxml");

    }
}
