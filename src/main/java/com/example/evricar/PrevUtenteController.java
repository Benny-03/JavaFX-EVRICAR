package com.example.evricar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PrevUtenteController implements Initializable {

    @FXML
    private Hyperlink hyperlink;

    @FXML
    private TextField codice_prev;

    @FXML
    private GridPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hyperlink.setText(StaticClass.id_name);
        hyperlink.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                DButils.changeScene(event,"structureSignIn.fxml","Sign In",null,null);
            }
        });

        try {
            DButils.setTable2(StaticClass.id_name);
            for (int col = 0; col < 3; col++) {
                for (int row = 0; row < StaticClass.displayPrev.size()/3; row++) {
                    Label label = new Label();
                    label.setText(StaticClass.displayPrev.get(row*3+col));
                    root.add(label, col, row);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private void mostraPrev(ActionEvent actionEvent) {
        if (!codice_prev.getText().isEmpty() && (StaticClass.displayPrev.contains(codice_prev.getText()))) {
            StaticClass.codPreventivo = codice_prev.getText();
            DButils.changeScene(actionEvent, "structureShowPrevAttivi.fxml",null,null,null);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Non hai inserito un codice valido");
            alert.setHeaderText(null);
            alert.setContentText("Inserisci un codice valido");
            alert.show();
        }
    }


    @FXML
    private void back(ActionEvent actionEvent) {
        DButils.changeScene(actionEvent,"structureCatalog.fxml",null,null,null);
    }
}
