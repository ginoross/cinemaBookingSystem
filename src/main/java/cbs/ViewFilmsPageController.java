package cbs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
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
    ArrayList<Film> sortedFilms = new ArrayList<>();

    @FXML
    Button backButton;

    @FXML
    ScrollPane scrollPane;

    @FXML
    GridPane grid;

    @Override

    //initialize resources
    public void initialize(URL location, ResourceBundle resources) {
        sortedFilms.addAll(Main.films);
        recursiveSort(sortedFilms);

        //retrieve all film posters
        try {
            String path = URLDecoder.decode("src/main/resources/cbs/filmPosters/", StandardCharsets.UTF_8);
            File folder = new File(path);

            for (File file : Objects.requireNonNull(folder.listFiles())) {
                for (Film film : sortedFilms) {
                    if (film.getFilmPoster().substring(0, film.getFilmPoster().length() - 4).equals(file.getName().substring(0, file.getName().length() - 4))) {
                        fileList.add(file);
                    }
                }

            }

            //setup scroll pane and grid details
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setHgap(10);
            grid.setVgap(10);

            //populate grid in scroll pane
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

    //recursive bubble sort to alphabetically sort films
    public ArrayList<Film> recursiveSort(ArrayList<Film> films) {
        boolean changed = false;
        for (int i = 0; i < films.size()-1; i++) {
            if(compareNames(films.get(i), films.get(i+1))){
                changed = true;
            }
            swap(films, i, i+1);
        }
        if(changed) return recursiveSort(films);
        else return films;
    }
    public boolean compareNames(Film film1, Film film2){
        return film2.filmTitle.compareTo(film1.filmTitle) < 0;
    }
    public void swap(ArrayList<Film> films, int id1, int id2){
        Film swap = films.get(id1);
        films.set(id1, films.get(id2));
        films.set(id2, swap);
    }

    //method used to populate grid with imageviews
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


    public void backButtonClicked() throws IOException {

        if(Main.currentUser.isEmployee){
            SceneCreator.createScene("manageFilmsPage.fxml");
        }else{
            SceneCreator.createScene("customerScene.fxml");
        }


    }
}
