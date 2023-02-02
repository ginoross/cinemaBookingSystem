package cbs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;

public class manageFilmsPageController {

    private File selectedImage;


    @FXML
    Button backButton, uploadImageButton, addFilmButton, viewFilmsButton;

    @FXML
    Label addFilmLabel, filmNameLabel, startDateLabel, endDateLabel, screeningLabel1, screeningLabel2, screeningLabel3, fieldsUnfilled, filmExistsLabel;

    @FXML
    TextField filmNameBox, filmTrailerBox;

    @FXML
    DatePicker startDatePicker, endDatePicker;

    @FXML
    ComboBox<String> screeningBox1, screeningBox2, screeningBox3;

    @FXML
    ImageView filmPoster;

    @FXML
    TextArea filmDescriptionArea;


    @FXML
    void initialize() throws IOException {

        ObservableList<String> screeningTimes = FXCollections.observableArrayList("13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");
        screeningBox1.setItems(screeningTimes);
        screeningBox2.setItems(screeningTimes);
        screeningBox3.setItems(screeningTimes);

        filmDescriptionArea.setPromptText("Movie description (optional)");


    }


    public void backButtonClicked(ActionEvent event) throws IOException {

        sceneCreator.createScene("employeeScene.fxml");

    }

    public void viewFilmsButtonClicked(ActionEvent event) throws IOException {

        sceneCreator.createScene("viewFilmsPage.fxml");

    }

    public void uploadImageClicked(ActionEvent event) throws IOException {

        try {
            FileChooser fc = new FileChooser();
            selectedImage = fc.showOpenDialog(null);
            if (selectedImage == null)
                return;
            else if (ImageIO.read(selectedImage) == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please make sure your image is PNG or JPG format", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    return;
                }
            } else {
                Image img = SwingFXUtils.toFXImage(ImageIO.read(selectedImage), null);
                filmPoster.setImage(img);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addFilmButtonClicked(ActionEvent event) throws Exception {

        if (filmNameBox.getLength() == 0 || (startDatePicker.getValue()) == null || (endDatePicker.getValue()) == null || (screeningBox1.getValue() == null) || (screeningBox2.getValue() == null) || (screeningBox3.getValue() == null) || (filmTrailerBox.getLength() == 0) || (selectedImage == null)) {
            clearErrors();
            fieldsUnfilled.setVisible(true);


        } else {
            try{
                copyPosterFile(selectedImage, Paths.get("src/main/resources/cbs/filmPosters/" + filmNameBox.getText() + "Poster.png"));
            }catch(Exception e){
                if(e instanceof FileAlreadyExistsException){
                    clearBoxes();
                    clearErrors();
                    filmExistsLabel.setVisible(true);
                    return;
                }
            }
            Main.films.add(new Film(filmNameBox.getText(), startDatePicker.getValue(), endDatePicker.getValue(), screeningBox1.getValue(), screeningBox2.getValue(), screeningBox3.getValue(), filmTrailerBox.getText(), filmDescriptionArea.getText(), filmNameBox.getText() + "Poster.png"));
            for (Film film : Main.films) {
                System.out.println(film.toString());
            }
            databaseHandler.addFilmRecord(filmNameBox.getText(), startDatePicker.getValue(), endDatePicker.getValue(), screeningBox1.getValue(), screeningBox2.getValue(), screeningBox3.getValue(), filmTrailerBox.getText(), filmDescriptionArea.getText(), filmNameBox.getText() + "Poster.png");
            clearBoxes();



        }
    }

    public void copyPosterFile(File poster, Path destination) throws IOException {
        Files.copy(poster.toPath(), destination);

    }

    public void clearErrors(){
        filmExistsLabel.setVisible(false);
        fieldsUnfilled.setVisible(true);
    }

    public void clearBoxes(){
        filmNameBox.clear();
        startDatePicker.getEditor().clear();
        endDatePicker.getEditor().clear();
        screeningBox1.getEditor().clear();
        screeningBox2.getEditor().clear();
        screeningBox3.getEditor().clear();
        filmTrailerBox.clear();
        filmDescriptionArea.clear();
        File file = new File("src/main/resources/cbs/filmPosters/defaultPoster.png");
        Image img = new Image(file.toURI().toString());
        filmPoster.setImage(img);


    }


}
