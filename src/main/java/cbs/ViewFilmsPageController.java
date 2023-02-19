package cbs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class ViewFilmsPageController implements Initializable {

    ArrayList<File> fileList = new ArrayList<>();
    HBox hBox = new HBox();

    @FXML
    Button backButton;

    @FXML
    ScrollPane scrollPane;

    @FXML
    GridPane grid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String path = URLDecoder.decode("src/main/resources/cbs/filmPosters/", StandardCharsets.UTF_8);
            File folder = new File(path);

            for (File file : Objects.requireNonNull(folder.listFiles())) {
                for (Film film : Main.films) {
                    if (film.getFilmPoster().substring(0, film.getFilmPoster().length() - 4).equals(file.getName().substring(0, file.getName().length() - 4)) && !(film.filmIndex == -1)) {
                        fileList.add(file);
                    }
                }

            }
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            grid.setPadding(new Insets(7, 7, 7, 7));
            grid.setHgap(10);
            grid.setVgap(10);

            int rows = (fileList.size() / 4) + 1;
            int columns = 4;
            int imageIndex = 0;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (imageIndex < fileList.size()) {
                        addImage(imageIndex, j, i);
                        imageIndex++;
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addImage(int index, int colIndex, int rowIndex) {
        String idToCut = fileList.get(index).getName();
        String id = idToCut.substring(0, (idToCut.length() - 10));
        Image image = new Image(fileList.get(index).toURI().toString());
        ImageView pic = new ImageView();
        pic.setFitWidth(160);
        pic.setFitHeight(220);
        pic.setImage(image);
        pic.setId(id);
        hBox.getChildren().add(pic);
        GridPane.setConstraints(pic, colIndex, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER);
        grid.getChildren().addAll(pic);

        pic.setOnMouseClicked(e -> {
            try {
                Main.setSelectedFilmTitle(id);
                SceneCreator.createScene("selectedFilmPage.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
    }


    public void backButtonClicked(ActionEvent event) throws IOException {

        SceneCreator.createScene("manageFilmsPage.fxml");

    }
}
