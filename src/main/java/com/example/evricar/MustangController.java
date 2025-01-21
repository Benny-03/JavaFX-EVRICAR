package com.example.evricar;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MustangController implements Initializable {

    @FXML
    private TextField linea_suv;
    @FXML
    private TextField linea_sportiva;

    @FXML
    private Button btn_sportiva;
    @FXML
    private Button btn_suv;

    @FXML
    private Pane panel_suv;
    @FXML
    private Pane panel_sportive;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_sportiva.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AllNotVisible();
                linea_sportiva.setVisible(true);
                panel_sportive.setVisible(true);
            }
        });

        btn_suv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AllNotVisible();
                linea_suv.setVisible(true);
                panel_suv.setVisible(true);
            }
        });
    }


    private void AllNotVisible() {
        linea_sportiva.setVisible(false);
        linea_suv.setVisible(false);
        panel_suv.setVisible(false);
        panel_sportive.setVisible(false);
    }


    @FXML
    private void gt500 (MouseEvent mouseEvent) {
        DButils.getInfo(mouseEvent,11);
        DButils.ChangeSceneMouseEvent(mouseEvent,"structureShow.fxml",null,11);
    }
    @FXML
    private void Mache (MouseEvent mouseEvent) {
        DButils.getInfo(mouseEvent,10);
        DButils.ChangeSceneMouseEvent(mouseEvent,"structureShow.fxml",null,10);
    }
}