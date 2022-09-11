package cbs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class mainController {
    @FXML
    TextField emailBox;

    @FXML
    PasswordField passwordBox;

    @FXML
    Button loginButton, registerButton;

    @FXML
    Label failedLogin, signIn, registerHere, fieldsUnfilled;

    @FXML
    public void loginButtonClicked(ActionEvent event) throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        users.addAll(Main.getCustomerList());
        users.addAll(Main.getEmployeeList());

        boolean loggedIn = false;
        for (User u : users) {
            if (emailBox.getText().equals(u.getEmail()) && passwordBox.getText().equals(u.getPassword())) {
                System.out.println("login success");
                loggedIn = true;
                failedLogin.setVisible(false);

                Main.setCurrentUser(u);
                Main.setEmployeeMode(u.getType().equals("employee"));
                if(Main.getEmployeeMode()){
                    sceneCreator.createScene("employeeScene.fxml");
                }else{
                    sceneCreator.createScene("customerScene.fxml");
                }
            }
        }
        if (!loggedIn) {
            if (emailBox.getText().length() == 0 || passwordBox.getText().length() == 0) {
                failedLogin.setVisible(false);
                fieldsUnfilled.setVisible(true);
            } else {
                fieldsUnfilled.setVisible(false);
                failedLogin.setVisible(true);
            }

            emailBox.clear();
            passwordBox.clear();
        }

    }

    @FXML
    public void registerButtonClicked(ActionEvent event) throws IOException {
        sceneCreator.createScene("registerPage.fxml");
    }
}