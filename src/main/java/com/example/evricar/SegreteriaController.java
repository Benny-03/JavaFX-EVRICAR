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

public class SegreteriaController implements Initializable {

    @FXML
    private Hyperlink hyperlink;

    @FXML
    private TableView<String> table;

    @FXML
    private GridPane root;
    @FXML
    private GridPane root2;

    @FXML
    private TextField codice_prev;

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
            DButils.changeScene(event,"structureShowSegreteria.fxml",null,null,null);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Non hai inserito un codice valido");
            alert.setHeaderText(null);
            alert.setContentText("Inserisci un codice valido");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hyperlink.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                DButils.changeScene(event,"structureSignIn.fxml","Sign In",null,null);
            }
        });

        try {
            DButils.setTable();

            for (int col = 0; col < 4; col++) {
                for (int row = 0; row < StaticClass.prev.size()/4; row++) {
                    Label label = new Label();
                    label.setText(StaticClass.prev.get(row*4+col));
                    root.add(label, col, row);
                }
            }

            for (int col1 = 0; col1 < 4; col1++) {
                for (int row1 = 0; row1 < StaticClass.prevV.size()/4; row1++) {
                    Label label = new Label();
                    label.setText(StaticClass.prevV.get(row1*4+col1));
                    root2.add(label, col1, row1);
                }
            }
            setLink2();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void setLink2 () {
        if (StaticClass.nameSegreteria != null && !StaticClass.nameSegreteria.isEmpty()) {
            hyperlink.setText(StaticClass.nameSegreteria);
        }

    }

    @FXML
    private void goOptional(ActionEvent event) {
        DButils.changeScene(event,"structureOptional.fxml",null,null,null);
    }
}
