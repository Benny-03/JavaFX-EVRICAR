package com.example.evricar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CatalogController  implements Initializable {
    @FXML
    private StackPane contentArea;

    @FXML
    private Hyperlink hyperLink;

    @FXML
    private TextField linea_audi;
    @FXML
    private TextField linea_lamborghini;
    @FXML
    private TextField linea_mustang;
    @FXML
    private TextField linea_opel;
    @FXML
    private TextField linea_landRover;
    @FXML
    private TextField linea_renault;
    @FXML
    private TextField linea_tesla;
    @FXML
    private TextField linea_mercedes;

    @FXML
    private Label esitoAcquisto;
    @FXML
    private Label consegna;

    @FXML
    private Pane popUp;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("structureAudi.fxml")));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void audiCatalog(ActionEvent event) throws IOException {
        AllNotVisible();
        linea_audi.setVisible(true);
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("structureAudi.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        popUp.setVisible(false);
        popUp.setDisable(true);
    }

    @FXML
    private void lamboCatalog(javafx.event.ActionEvent actionEvent) throws IOException {
        AllNotVisible();
        linea_lamborghini.setVisible(true);
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("structureLamborghini.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        popUp.setVisible(false);
        popUp.setDisable(true);
    }

    @FXML
    private void mercedesCatalog(javafx.event.ActionEvent actionEvent) throws IOException {
        AllNotVisible();
        linea_mercedes.setVisible(true);
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("structureMercedes.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        popUp.setVisible(false);
        popUp.setDisable(true);
    }

    @FXML
    private void mustangCatalog(javafx.event.ActionEvent actionEvent) throws IOException {
        AllNotVisible();
        linea_mustang.setVisible(true);
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("structureMustang.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        popUp.setVisible(false);
        popUp.setDisable(true);
    }

    @FXML
    private void opelCatalog(javafx.event.ActionEvent actionEvent) throws IOException {
        AllNotVisible();
        linea_opel.setVisible(true);
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("structureOpel.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        popUp.setVisible(false);
        popUp.setDisable(true);
    }

    @FXML
    private void rangeCatalog(javafx.event.ActionEvent actionEvent) throws IOException {
        AllNotVisible();
        linea_landRover.setVisible(true);
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("structureLandRover.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        popUp.setVisible(false);
        popUp.setDisable(true);
    }

    @FXML
    private void renaultCatalog(javafx.event.ActionEvent actionEvent) throws IOException {
        AllNotVisible();
        linea_renault.setVisible(true);
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("structureRenault.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        popUp.setVisible(false);
        popUp.setDisable(true);
    }

    @FXML
    private void teslaCatalog(javafx.event.ActionEvent actionEvent) throws IOException {
        AllNotVisible();
        linea_tesla.setVisible(true);
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("structureTesla.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        popUp.setVisible(false);
        popUp.setDisable(true);
    }




    public void setHyperLink(String name) {
        if (name != null) {
            StaticClass.id_name = name;
            System.out.println(StaticClass.id_name);
            DButils.getIdFromCatalog(StaticClass.id_name);

            DButils.getResult(StaticClass.idUserFromCatalog);
            DButils.getArrivo(StaticClass.idUserFromCatalog);

            if (Objects.equals(StaticClass.esito, "true"))
            {
                esitoAcquisto.setVisible(true);
                esitoAcquisto.setText("Preventivo Accettato");
            }
            else if (Objects.equals(StaticClass.esito, "false"))
            {
                esitoAcquisto.setVisible(true);
                esitoAcquisto.setText("Preventivo Rifiutato");
            }
            else {
                esitoAcquisto.setVisible(false);
            }

            if(Objects.equals(StaticClass.arrivo, "true"))
            {
                consegna.setVisible(true);
                consegna.setText("l'auto è pronta per la consegna");
            }else {
                consegna.setVisible(false);
            }

            hyperLink.setText(name);
        }
    }


    private void AllNotVisible() {
        linea_audi.setVisible(false);
        linea_lamborghini.setVisible(false);
        linea_mercedes.setVisible(false);
        linea_mustang.setVisible(false);
        linea_opel.setVisible(false);
        linea_landRover.setVisible(false);
        linea_renault.setVisible(false);
        linea_tesla.setVisible(false);
    }


    @FXML
    private void menuLogin(ActionEvent event) {
        DButils.changeScene(event, "structureSignIn.fxml", null, null, null);
    }

    @FXML
    private void menuLogout(ActionEvent event) {
        DButils.changeScene(event, "structureHome.fxml", null, null, null);
    }

    @FXML
    private void menuPrev(ActionEvent event) {
        DButils.changeScene(event, "structurePrevUtente.fxml", null, null, null);
    }

    @FXML
    private void menu(ActionEvent event) {
        popUp.setVisible(true);
        popUp.setDisable(false);
    }
}
