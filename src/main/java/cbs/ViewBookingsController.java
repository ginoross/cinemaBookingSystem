package cbs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class ViewBookingsController implements Initializable {

    ArrayList<Booking> customerBookingList = new ArrayList<>();

    ObservableList<Booking> observableBookingList;

    @FXML
    private TableView<Booking> table;

    @FXML
    private TableColumn<Booking, String> bookingID, filmID, bookingDate, bookingTime, bookingSeat;

    @FXML
    private TableColumn<Booking, Boolean> isCancelled;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        setTableColumns();
        try (ResultSet rs = DatabaseHandler.queryData("SELECT * from tblBookings WHERE userID = '" + Main.currentUser.getID() + "'")) {
            while (rs.next()) {
                customerBookingList.add(new Booking(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getString(7), rs.getString(5), rs.getBoolean(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        observableBookingList = FXCollections.observableArrayList(customerBookingList);
        System.out.println(Arrays.toString(customerBookingList.toArray()));
        table.getItems().addAll(observableBookingList);
    }


    private void setTableColumns() {

        bookingID.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        filmID.setCellValueFactory(new PropertyValueFactory<>("filmID"));
        bookingDate.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
        bookingTime.setCellValueFactory(new PropertyValueFactory<>("bookingTime"));
        bookingSeat.setCellValueFactory(new PropertyValueFactory<>("bookingSeat"));
        isCancelled.setCellValueFactory(new PropertyValueFactory<>("isCancelled"));

    }

    public void backButtonClicked(ActionEvent event) throws IOException {
        SceneCreator.createScene("customerScene.fxml");
    }

    public boolean bookingCancellable() {
        LocalDate currentDate = LocalDate.now();
        LocalDate selectedDate = table.getSelectionModel().getSelectedItem().bookingDate;
        return selectedDate.isAfter(currentDate);
    }

    public void cancelBooking() throws SQLException, IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            if (!table.getSelectionModel().getSelectedItem().getIsCancelled()) {
                if (bookingCancellable()) {
                    showConfirmationDialog();
                } else {
                    showWarningDialog("You cannot cancel a booking that is before the current date!");
                }
            } else {
                showWarningDialog("This booking is already cancelled!");
            }
        } else {
            showWarningDialog("Please select a booking to cancel!");
        }
    }

    private void showConfirmationDialog() throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel this booking?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            DatabaseHandler.cancelBooking(table.getSelectionModel().getSelectedItem().BookingID);
            for (Booking booking : Main.bookings) {
                if (booking.BookingID == table.getSelectionModel().getSelectedItem().BookingID) {
                    booking.setCancelled(true);
                }
            }
            SceneCreator.createScene("viewBookingsPage.fxml");
        }
    }

    private void showWarningDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        alert.showAndWait();
    }
}
