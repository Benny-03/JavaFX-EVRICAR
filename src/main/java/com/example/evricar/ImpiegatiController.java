package com.example.evricar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ImpiegatiController implements Initializable {

    @FXML
    private GridPane root;
    @FXML
    private TextField codice_prev;
    @FXML
    private GridPane root2;
    @FXML
    private Hyperlink myEvricar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myEvricar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                DButils.changeScene(event,"structureSignIn.fxml","Sign In",null,null);
            }
        });

        try {
            DButils.setTable();
            for (int col = 0; col < 4; col++) {     //non usato
                for (int row = 0; row < StaticClass.prev.size()/4; row++) {
                    Label label = new Label();
                    label.setText(StaticClass.prev.get(row*4+col));
                    root.add(label, col, row);
                }
            }

            for (int col1 = 0; col1 < 4; col1++) {      //usato
                for (int row1 = 0; row1 < StaticClass.prevV.size()/4; row1++) {
                    Label label = new Label();
                    label.setText(StaticClass.prevV.get(row1*4+col1));
                    root2.add(label, col1, row1);
                }
            }
           setLink();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void mostraPrev(ActionEvent event) {
        if (!codice_prev.getText().isEmpty() && (StaticClass.prev.contains(codice_prev.getText()) || StaticClass.prevV.contains(codice_prev.getText()))) {
            if(StaticClass.prev.contains(codice_prev.getText())) {
                StaticClass.cond = "1";
            }
            if (StaticClass.prevV.contains(codice_prev.getText())){
                StaticClass.cond = "0";
            }

            StaticClass.codPreventivo = codice_prev.getText();
            DButils.changeScene(event,"structureShowPreventivo.fxml",null,null,null);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Non hai inserito un codice valido");
            alert.setHeaderText(null);
            alert.setContentText("Inserisci un codice valido");
            alert.show();
        }
    }

    private void setLink () {
        if (StaticClass.nameImpiegati != null && !StaticClass.nameImpiegati.isEmpty()) {
            myEvricar.setText(StaticClass.nameImpiegati);
        }
    }
}