package cbs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;


public class SelectedFilmPageController implements Initializable {

    private static final String POSTER_PATH_FORMAT = "src/main/resources/cbs/filmPosters/%sPoster.jpg";

    private Film selectedFilm;
    private File filmPoster;

    @FXML
    private ImageView selectedFilmPoster;

    @FXML
    private Label filmTitleLabel, startDateLabel, endDateLabel, screeningTimesLabel;

    @FXML
    private Hyperlink filmTrailerLink;

    @FXML
    private TextArea filmDescriptionTextArea;

    @FXML
    private Button deleteFilmButton;
    @FXML
    private Button bookFilmButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedFilm = Main.getSelectedFilm(Main.selectedFilmTitle);
        filmPoster = new File(String.format(POSTER_PATH_FORMAT, Main.selectedFilmTitle));
        Image img = new Image(filmPoster.toURI().toString());
        selectedFilmPoster.setImage(img);
        filmTitleLabel.setText(selectedFilm.filmTitle);
        filmDescriptionTextArea.setText(selectedFilm.filmDescription);
        startDateLabel.setText("Start Date: " + selectedFilm.startDate.toString());
        endDateLabel.setText("End Date: " + selectedFilm.endDate.toString());
        filmTrailerLink.setText(selectedFilm.getFilmTrailer());
        filmTrailerLink.setOnAction(e -> {
            try {
                openLink(selectedFilm.filmTrailer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        screeningTimesLabel.setText("Screening times: " + selectedFilm.screening1 + ", " + selectedFilm.screening2 + ", " + selectedFilm.screening3);
        if (Main.getEmployeeMode()) {
            deleteFilmButton.setVisible(true);
        } else {
            bookFilmButton.setVisible(true);
        }
    }

    public void deleteFilm() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this film?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                DatabaseHandler.deleteFilmRecord(selectedFilm.filmIndex);
                Main.films.removeIf(film -> film.equals(selectedFilm));
                filmPoster.delete();
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Film deleted successfully.", ButtonType.OK);
                successAlert.showAndWait();
                SceneCreator.createScene("viewFilmsPage.fxml");
            }
        } catch (IOException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Error deleting film: " + e.getMessage(), ButtonType.OK);
            errorAlert.showAndWait();
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Error deleting film: " + e.getMessage(), ButtonType.OK);
            errorAlert.showAndWait();
            e.printStackTrace();
        }
    }

    public void openLink(String URL) throws IOException {
        Desktop desk = Desktop.getDesktop();
        desk.browse(URI.create(URL));
    }

    public void backButtonClicked() throws IOException {
        SceneCreator.createScene("viewFilmsPage.fxml");
    }

    public void bookFilm() throws IOException {
        SceneCreator.createScene("bookFilmsPage.fxml");
    }
}