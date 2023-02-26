package cbs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.ResultSet;

public class RegisterPageController {

    protected int customerID;

    @FXML
    Label registerLabel, firstNameLabel, lastNameLabel, emailLabel, passwordLabel, confirmPasswordLabel, employeeRegisterLabel, fieldsUnfilled, passwordsUnmatched, registeredLabel, invalidEmailFormat;

    @FXML
    TextField firstNameBox, lastNameBox, emailBox;

    @FXML
    PasswordField passwordBox, confirmPasswordBox;

    @FXML
    Button registerButton, backToLogin;

    @FXML
    public void registerButtonClicked(ActionEvent Event) throws Exception {

        //validates that data is entered, and it is correctly formatted etc
        if (firstNameBox.getText().length() == 0 || lastNameBox.getText().length() == 0 || emailBox.getText().length() == 0 || passwordBox.getText().length() == 0 || confirmPasswordBox.getText().length() == 0) {
            clearErrorLabels();
            fieldsUnfilled.setVisible(true);
            clearBoxes();
        } else if (!(passwordBox.getText().equals(confirmPasswordBox.getText()))) {
            clearErrorLabels();
            passwordsUnmatched.setVisible(true);
        } else if (!(emailBox.getText().contains("@") || emailBox.getText().contains(".com")) || emailExists(emailBox.getText())) {
            clearErrorLabels();
            invalidEmailFormat.setVisible(true);
        } else {

            //if all checks are passed user can be added to customer database
            DatabaseHandler.addUserRecord(firstNameBox.getText(), lastNameBox.getText(), emailBox.getText(), SHA256.toHexString(SHA256.getSHABytes(passwordBox.getText())));

            //retrieves auto-assigned id from database to ensure local data is correct
            try {
                ResultSet rs = DatabaseHandler.queryData("SELECT UserID, email FROM tblUsers WHERE email = '" + emailBox.getText() + "'");
                if (rs.next()) {
                    customerID = rs.getInt(1);
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Error updating database. Please restart program", ButtonType.OK);
                    alert.showAndWait();
                }

                Main.customers.add(new Customer(firstNameBox.getText(), lastNameBox.getText(), emailBox.getText(), SHA256.toHexString(SHA256.getSHABytes(passwordBox.getText())), false, customerID));
            } catch (Exception e) {
                e.printStackTrace();
            }


            clearErrorLabels();
            registeredLabel.setVisible(true);
            clearBoxes();

        }
    }

    //check to see if email already exists - having 2 users with the same email would break the system
    public boolean emailExists(String email) {
        for (Customer customer : Main.customers) {
            if(customer.email.equals(email)){
                return true;
            }
        }
        for(Employee employee: Main.employees) {
            if(employee.email.equals(email)){
                return true;
            }
        }
        return false;
    }

    //clears current error messages

    public void clearErrorLabels() {
        fieldsUnfilled.setVisible(false);
        passwordsUnmatched.setVisible(false);
        invalidEmailFormat.setVisible(false);

    }

    public void loadLoginPage(ActionEvent event) throws IOException {
        SceneCreator.createScene("loginPage.fxml");
    }

    public void clearBoxes() {
        firstNameBox.clear();
        lastNameBox.clear();
        emailBox.clear();
        passwordBox.clear();
        confirmPasswordBox.clear();
    }


}
