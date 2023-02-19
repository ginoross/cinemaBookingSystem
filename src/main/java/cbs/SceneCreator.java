package cbs;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class SceneCreator {

    public static void createScene(String sceneName) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(sceneName));
        Main.setRoot(fxmlLoader.load());
        Scene scene = new Scene(Main.getRoot());
        Main.getStage().setScene(scene);
        Main.getStage().show();


    }

}


