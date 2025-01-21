package com.example.evricar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class StructureShowPrevAttivi implements Initializable {
    @FXML
    private Pane popUp;

    @FXML
    private Label prezzo_scontato;
    @FXML
    private Label sconto;
    @FXML
    private Label Richiedente;
    @FXML
    private Label modelloAuto;
    @FXML
    private Label Prezzo;
    @FXML
    private Label Marca;
    @FXML
    private Label Colore;
    @FXML
    private Label Optional;
    @FXML
    private Label alimentazione;
    @FXML
    private Label dataRit;
    @FXML
    private Label LuogoRit;
    @FXML
    private Label tel;
    @FXML
    private Label marcaUsato;
    @FXML
    private Label annoUsato;
    @FXML
    private Label modelloUsato;
    @FXML
    private Label mail;

    @FXML
    private LocalDate loc;

    @FXML
    private void Accetta(ActionEvent actionEvent) {
        DButils.getIdFromImpiegati(actionEvent, StaticClass.id_name);
        DButils.mantieniPrev(StaticClass.codPreventivo, StaticClass.idUserFromImpiegati);
        System.out.println(StaticClass.esito);
        if (Objects.equals(StaticClass.esito, "true")) {
            popUp.setVisible(true);
            popUp.setDisable(false);
            StaticClass.pagato = "true";
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Preventito non è stato ancora accettato");
            alert.show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            DButils.getPrevCont(StaticClass.codPreventivo);
            if (StaticClass.displayPrev.get(1).equals("true")) {
                String[] parts = StaticClass.contenuto.split("£");
                System.out.println(parts.length);
                if( parts.length == 14 || parts[14].equals("null")) {
                    Richiedente.setText(parts[0]);
                    modelloAuto.setText(parts[1]);
                    Prezzo.setText(parts[2]);
                    Marca.setText(parts[3]);
                    Colore.setText(parts[4]);
                    Optional.setText(parts[5]);
                    alimentazione.setText(parts[6]);
                    dataRit.setText(parts[7]);
                    LuogoRit.setText(parts[8]);
                    tel.setText(parts[9]);
                    mail.setText(parts[10]);
                    modelloUsato.setVisible(true);
                    marcaUsato.setVisible(true);
                    annoUsato.setVisible(true);
                    modelloUsato.setText(parts[11]);
                    marcaUsato.setText(parts[12]);
                    annoUsato.setText(parts[13]);
                    sconto.setVisible(false);
                    prezzo_scontato.setVisible(false);
                }else{
                    Richiedente.setText(parts[0]);
                    modelloAuto.setText(parts[1]);
                    Prezzo.setText(parts[2]);
                    Marca.setText(parts[3]);
                    Colore.setText(parts[4]);
                    Optional.setText(parts[5]);
                    alimentazione.setText(parts[6]);
                    dataRit.setText(parts[7]);
                    LuogoRit.setText(parts[8]);
                    tel.setText(parts[9]);
                    mail.setText(parts[10]);
                    modelloUsato.setVisible(true);
                    marcaUsato.setVisible(true);
                    annoUsato.setVisible(true);
                    modelloUsato.setText(parts[11]);
                    marcaUsato.setText(parts[12]);
                    annoUsato.setText(parts[13]);
                    sconto.setText(parts[14]);
                    prezzo_scontato.setText(parts[15]);
                }
            }else {
                String[] parts = StaticClass.contenuto.split("£");
                if(parts.length == 11 || parts[11].equals("null")) {
                    Richiedente.setText(parts[0]);
                    modelloAuto.setText(parts[1]);
                    Prezzo.setText(parts[2]);
                    Marca.setText(parts[3]);
                    Colore.setText(parts[4]);
                    Optional.setText(parts[5]);
                    alimentazione.setText(parts[6]);
                    dataRit.setText(parts[7]);
                    LuogoRit.setText(parts[8]);
                    tel.setText(parts[9]);
                    mail.setText(parts[10]);
                    modelloUsato.setVisible(false);
                    marcaUsato.setVisible(false);
                    annoUsato.setVisible(false);
                    sconto.setVisible(false);
                    prezzo_scontato.setVisible(false);
                }else{
                    Richiedente.setText(parts[0]);
                    modelloAuto.setText(parts[1]);
                    Prezzo.setText(parts[2]);
                    Marca.setText(parts[3]);
                    Colore.setText(parts[4]);
                    Optional.setText(parts[5]);
                    alimentazione.setText(parts[6]);
                    dataRit.setText(parts[7]);
                    LuogoRit.setText(parts[8]);
                    tel.setText(parts[9]);
                    mail.setText(parts[10]);
                    modelloUsato.setVisible(false);
                    marcaUsato.setVisible(false);
                    annoUsato.setVisible(false);
                    sconto.setText(parts[11]);
                    prezzo_scontato.setText(parts[12]);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loc = LocalDate.now();
        if(loc.toString().equals(dataRit.toString())) {
            DButils.eliminaPrev(StaticClass.codPreventivo);
        }
    }


    @FXML
    private void indietro(ActionEvent event) {
        DButils.changeScene(event,"structurePrevUtente.fxml",null,null,null);
    }
}
