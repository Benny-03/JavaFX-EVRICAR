package com.example.evricar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;
public class Controller implements Initializable {

    @FXML
    private Button button_login;
    @FXML
    private Button button_sign_up;

    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                DButils.logInUSer(event,tf_email.getText(),tf_password.getText());
            }
        });

        button_sign_up.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                DButils.changeScene(event,"structureSignUp.fxml","Sign Up",null,null);
            }
        });
    }
}