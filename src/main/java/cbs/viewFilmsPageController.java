package cbs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class viewFilmsPageController implements Initializable {

    ArrayList<File> fileList = new ArrayList<File>();
    HBox hBox = new HBox();

    @FXML
    Button backButton;

    @FXML
    ScrollPane scrollPane;

    public void initialize(URL location, ResourceBundle resources){
        try{
            String path = URLDecoder.decode("src/main/resources/cbs/filmPosters/", StandardCharsets.UTF_8);
            File folder = new File(path);

            for(File file: folder.listFiles()){
                fileList.add(file);
            }

            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void backButtonClicked(ActionEvent event) throws IOException {

        sceneCreator.createScene("manageFilmsPage.fxml");

    }
}
