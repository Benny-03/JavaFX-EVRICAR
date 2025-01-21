package com.example.evricar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OptionalController  implements Initializable {
    @FXML
    private TextField autoText;
    @FXML
    private TextField optionalText;
    @FXML
    private TextField eliminaOp;

    @FXML
    private GridPane gridPane;
    @FXML
    private GridPane listaOp;

    @FXML
    private TextArea newDesc;

    @FXML
    private ChoiceBox <String> choicebox;

    private String [] choiche ={"categoria", "descrizione", "altezza", "larghezza","lunghezza", "peso", "volume_bagagliaio", "prezzo"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choicebox.getItems().setAll(choiche);
        try {
            DButils.getAutoInfo();
            DButils.getExtraOptionalTable();
            for (int col = 0; col < 3; col++) {
                for (int row = 0; row < 23; row++) {
                    Label label = new Label();
                    label.setText(StaticClass.auto.get(row*3+col));
                    gridPane.add(label, col, row);
                }
            }

            for (int col1 = 0; col1 < 3; col1++) {
                for (int row1 = 0; row1 < StaticClass.idOptional.size()/3; row1++) {
                    Label label = new Label();
                    label.setText(StaticClass.idOptional.get(row1 * 3 + col1));
                    listaOp.add(label, col1, row1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private void previus(ActionEvent event) {
        DButils.changeScene(event,"structureSegreteria.fxml",null,null,null);
    }

    @FXML
    private void inserisci(ActionEvent event) throws SQLException {
        if (autoText.getText().isEmpty() || optionalText.getText().isEmpty() ||(Integer.parseInt(autoText.getText())< 1 || Integer.parseInt(autoText.getText()) > 23)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Inserimento dati errato");
            alert.show();
        }else {
            DButils.insertOptional(autoText.getText(),optionalText.getText()+"£",1);
            autoText.setText("");
            optionalText.setText("");
            DButils.changeScene(event,"structureOptional.fxml",null,null,null);
        }
    }


    @FXML
    private void eliminaOpt(ActionEvent event) throws SQLException {
        if (eliminaOp.getText().isEmpty() || !StaticClass.idOptional.contains(eliminaOp.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Inserimento dati errato");
            alert.show();
        }else {
            DButils.eliminaOptional(eliminaOp.getText());
            DButils.changeScene(event,"structureSegreteria.fxml",null,null,null);
            eliminaOp.setText("");
        }
    }


    @FXML
    private void updateDesc(ActionEvent actionEvent) {
        if((newDesc.getText().isEmpty() || autoText.getText().isEmpty())||(Integer.parseInt(autoText.getText())< 1 || Integer.parseInt(autoText.getText()) > 23) ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Inserimento dati errato");
            alert.show();
        }else {
            DButils.updDesc(newDesc.getText(),choicebox.getValue(),autoText.getText());
            newDesc.setText("");
            autoText.setText("");
        }
    }
}