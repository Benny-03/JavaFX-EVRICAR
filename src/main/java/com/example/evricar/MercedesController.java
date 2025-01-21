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

public class MercedesController implements Initializable {

    @FXML
    private TextField linea_sportiva;
    @FXML
    private TextField linea_citycar;
    @FXML
    private TextField linea_suv;

    @FXML
    private Button btn_citycar;
    @FXML
    private Button btn_suv;
    @FXML
    private Button btn_sportiva;

    @FXML
    private Pane panel_sportive;
    @FXML
    private Pane panel_citycar;
    @FXML
    private Pane panel_suv;



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

        btn_citycar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AllNotVisible();
                linea_citycar.setVisible(true);
                panel_citycar.setVisible(true);
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
        linea_citycar.setVisible(false);
        panel_citycar.setVisible(false);
        panel_suv.setVisible(false);
        panel_sportive.setVisible(false);
    }


    @FXML
    private void classeA (MouseEvent mouseEvent) {
        DButils.getInfo(mouseEvent,9);
        DButils.ChangeSceneMouseEvent(mouseEvent,"structureShow.fxml",null,9);
    }
    @FXML
    private void Gtr (MouseEvent mouseEvent) {
        DButils.getInfo(mouseEvent,7);
        DButils.ChangeSceneMouseEvent(mouseEvent,"structureShow.fxml",null,7);
    }
    @FXML
    private void Glc (MouseEvent mouseEvent) {
        DButils.getInfo(mouseEvent,8);
        DButils.ChangeSceneMouseEvent(mouseEvent,"structureShow.fxml",null,8);
    }

}
