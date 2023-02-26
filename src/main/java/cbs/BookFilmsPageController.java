package cbs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.*;

public class BookFilmsPageController implements Initializable {

    private static final Color AVAILABLE_SEAT_COLOR = Color.DODGERBLUE;
    private static final Color SELECTED_SEAT_COLOR = Color.RED;
    private static final Color BOOKED_SEAT_COLOR = Color.GREY;

    private Film selectedFilm;
    private final Set<Rectangle> seats = new HashSet<>();
    private final ArrayList<String> selectedSeats = new ArrayList<>();



    @FXML
    private Label bookingTitleLabel;

    @FXML
    private DatePicker bookingDatePicker;

    @FXML
    private ComboBox<String> bookingTimeComboBox;

    @FXML
    private Rectangle A2, A3, B1, B2, B3, C1, C2, C3, D1, D2, D3, E1, E2, E3, F2, F3;

    @Override

    //initialize resources
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedFilm = Main.getSelectedFilm(Main.selectedFilmTitle);
        assert selectedFilm != null;

        //retrieves valid times for selected film
        ObservableList<String> validTimes = FXCollections.observableArrayList(selectedFilm.screening1, selectedFilm.screening2, selectedFilm.screening3);
        bookingTitleLabel.setText("Booking for: " + selectedFilm.getFilmTitle());
        bookingTimeComboBox.setItems(validTimes);


        //adds all seat objects (stored as rectangles) to seat arraylist
        seats.add(A2);
        seats.add(A3);
        seats.add(B1);
        seats.add(B2);
        seats.add(B3);
        seats.add(C1);
        seats.add(C2);
        seats.add(C3);
        seats.add(D1);
        seats.add(D2);
        seats.add(D3);
        seats.add(E1);
        seats.add(E2);
        seats.add(E3);
        seats.add(F2);
        seats.add(F3);

        //disables seats until you select valid times
        for (Rectangle seat : seats) {
            seat.setDisable(true);
        }

        //initialises date-picker object to be between the times of the selected film
        bookingDatePicker.setDayCellFactory(datePicker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(selectedFilm.getStartDate()) < 0 || date.compareTo(selectedFilm.getEndDate()) > 0);
            }
        });
    }


    //method that updates booked seats whenever a valid date and time is selected
    public void updateBookedSeats() {
        if (bookingTimeComboBox.getValue() != null && bookingDatePicker.getValue() != null) {
            for (Rectangle seat : seats) {
                seat.setDisable(false);
                seat.setFill(AVAILABLE_SEAT_COLOR);
            }
            for (Booking booking : Main.bookings) {
                if (booking.FilmID == selectedFilm.filmIndex && !booking.getIsCancelled() && booking.bookingDate.equals(bookingDatePicker.getValue()) && booking.bookingTime.equals(bookingTimeComboBox.getValue())) {
                    for (Rectangle seat : seats) {
                        if (seat.getId().equals(booking.getBookingSeat())) {
                            seat.setDisable(true);
                            seat.setFill(BOOKED_SEAT_COLOR);
                        }
                    }

                }
            }
        }

    }

//method that colours seats to indicate that they are selected
    public void seatSelected(MouseEvent e) {
        Rectangle rect = (Rectangle) e.getSource();
        if (rect.getFill().equals(AVAILABLE_SEAT_COLOR)) {
            rect.setFill(SELECTED_SEAT_COLOR);
            selectedSeats.add(rect.getId());
        } else {
            rect.setFill(AVAILABLE_SEAT_COLOR);
            selectedSeats.remove(rect.getId());
        }
    }


//method that allows the user to confirm their booking & adds it to the database
    public void confirmBooking() throws Exception {
        int bookingID = 0;
        if (selectedSeats.size() != 0 && bookingDatePicker.getValue() != null && bookingTimeComboBox != null) {

            //alert to confirm booking
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please confirm your booking of seats " + Arrays.toString(selectedSeats.toArray()) + " for " + selectedFilm.filmTitle + " at " + bookingDatePicker.getValue() + ", " + bookingTimeComboBox.getValue(), ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {

                //adds all selected seats to database as individual bookings
                for (String seat : selectedSeats) {
                    DatabaseHandler.addBookingRecord(selectedFilm.filmIndex, Main.currentUser.getID(), bookingDatePicker.getValue(), bookingTimeComboBox.getValue(), seat);

                    //retrieves auto-assigned booking id from database so that general booking list can be updated correctly
                    ResultSet rs = DatabaseHandler.queryData("SELECT * FROM tblBookings WHERE filmID = '"+selectedFilm.filmIndex+"' and userID = '"+Main.currentUser.getID()+"' and bookingDate = '"+bookingDatePicker.getValue()+"' and bookingTime = '"+bookingTimeComboBox.getValue()+"' and bookingSeat = '"+seat+"'" );
                    if (rs.next()) {
                        bookingID = rs.getInt(1);
                    } else {
                        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Error updating database. Please restart program", ButtonType.OK);
                        alert1.showAndWait();
                    }

                    Main.bookings.add(new Booking(bookingID, selectedFilm.filmIndex, Main.currentUser.getID(), bookingDatePicker.getValue(), bookingTimeComboBox.getValue(), seat, false));
                }
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Booking completed successfully!", ButtonType.OK);
                alert1.showAndWait();

                //reloads page so bookings are correct
                SceneCreator.createScene("customerScene.fxml");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please make sure you have selected a date, time and seats to book! ", ButtonType.OK);
            alert.showAndWait();
        }
    }


    public void backButtonClicked() throws IOException {

        SceneCreator.createScene("customerScene.fxml");
    }

}
