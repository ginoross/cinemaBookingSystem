package cbs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class registerPageController {

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
        if (firstNameBox.getText().length() == 0 || lastNameBox.getText().length() == 0 || emailBox.getText().length() == 0 || passwordBox.getText().length() == 0 || confirmPasswordBox.getText().length() == 0) {
            clearErrorLabels();
            fieldsUnfilled.setVisible(true);
            clearBoxes();
        } else if (!(passwordBox.getText().equals(confirmPasswordBox.getText()))) {
            clearErrorLabels();
            passwordsUnmatched.setVisible(true);
        } else if (!(emailBox.getText().contains("@") || emailBox.getText().contains(".com"))) {
            clearErrorLabels();
            invalidEmailFormat.setVisible(true);
        } else {
            Main.customers.add(new Customer(firstNameBox.getText(), lastNameBox.getText(), emailBox.getText(), passwordBox.getText()));
            clearErrorLabels();
            registeredLabel.setVisible(true);

            databaseHandler.addUserRecord(firstNameBox.getText(), lastNameBox.getText(), emailBox.getText(), passwordBox.getText());

            clearBoxes();

        }
    }

    public void clearErrorLabels() {
        fieldsUnfilled.setVisible(false);
        passwordsUnmatched.setVisible(false);
        invalidEmailFormat.setVisible(false);

    }

    public void loadLoginPage(ActionEvent event) throws IOException {
        sceneCreator.createScene("loginPage.fxml");
    }

    public void clearBoxes() {
        firstNameBox.clear();
        lastNameBox.clear();
        emailBox.clear();
        passwordBox.clear();
        confirmPasswordBox.clear();
    }


}
