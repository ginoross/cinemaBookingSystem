package cbs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ViewUsersController implements Initializable {

    public Button backButton;
    ArrayList<User> userArrayList = new ArrayList<>();

    ObservableList<User> observableUserList;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> ID, firstName, lastName, email;
    @FXML
    private TableColumn<User, Boolean> isEmployee;
    @FXML
    private Button makeEmployeeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //initializes tableview
        setTableColumns();
        try (ResultSet rs = DatabaseHandler.queryData("SELECT * from tblUsers WHERE userID != '" + Main.currentUser.getID() + "'")) {
            while (rs.next()) {
                userArrayList.add(new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        observableUserList = FXCollections.observableArrayList(userArrayList);
        System.out.println(Arrays.toString(userArrayList.toArray()));
        table.getItems().addAll(observableUserList);
    }

    //sets up the tableview columns
    private void setTableColumns() {
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        isEmployee.setCellValueFactory(new PropertyValueFactory<>("isEmployee"));

    }

    public void backButtonClicked(ActionEvent event) throws IOException {
        SceneCreator.createScene("employeeScene.fxml");
    }

    //method that checks if user can be made an employee and shows errors otherwise
    public void makeEmployee() throws SQLException, IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            if (!table.getSelectionModel().getSelectedItem().getIsEmployee()) {
                showConfirmationDialog();
            } else {
                showWarningDialog("This User is already an Employee!");
            }
        } else {
            showWarningDialog("Please select a user");
        }
    }

    //displays confirmation message and then makes user an employee
    private void showConfirmationDialog() throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Make this user an employee?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            DatabaseHandler.makeEmployee(table.getSelectionModel().getSelectedItem().ID);
            for(Customer customer: Main.customers){
                if(customer.getID()==table.getSelectionModel().getSelectedItem().ID){
                    customer.setEmployee(true);
                }
            }

            //reloads page so info is updated correctly
            SceneCreator.createScene("viewUsersPage.fxml");
        }
    }

    //displays warning dialog
    private void showWarningDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        alert.showAndWait();
    }
}
