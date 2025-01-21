package com.example.evricar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.event.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button signUpButton;
    @FXML
    private Button button_log_in;

    @FXML
    private TextField fullName;
    @FXML
    private TextField passwordSignUp;
    @FXML
    private TextField emailSignUp;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(!fullName.getText().trim().isEmpty() && !passwordSignUp.getText().trim().isEmpty() && !emailSignUp.getText().trim().isEmpty()){
                    if(EmailValidator.isValidEmail(emailSignUp.getText())) {
                        DButils.signUpUSer(event,fullName.getText(),passwordSignUp.getText(),emailSignUp.getText());
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Email not valid");
                        alert.setContentText("Email not valid");
                        alert.show();
                    }
                } else {
                    System.out.println("Please fill all the fields");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.show();
                }
            }
        });

       button_log_in.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent event) {
               DButils.changeScene(event,"structureSignIn.fxml","Log in",null,null);
           }
        });

    }
}
