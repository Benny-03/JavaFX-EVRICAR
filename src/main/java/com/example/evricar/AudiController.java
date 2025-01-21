package com.example.evricar;

import javafx.event.ActionEvent;
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

public class AudiController implements Initializable {
    @FXML
    private Pane panel_sportive;
    @FXML
    private Pane panel_citycar;
    @FXML
    private Pane panel_coupe;

    @FXML
    private TextField linea_sportiva;
    @FXML
    private TextField linea_citycar;
    @FXML
    private TextField linea_coupe;

    @FXML
    private Button btn_sportiva;
    @FXML
    private Button btn_citycar;
    @FXML
    private Button btn_coupe;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_sportiva.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                AllNotVisible();
                linea_sportiva.setVisible(true);
                panel_sportive.setVisible(true);
            }
        });

        btn_citycar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                AllNotVisible();
                linea_citycar.setVisible(true);
                panel_citycar.setVisible(true);
            }
        });

        btn_coupe.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                AllNotVisible();
                linea_coupe.setVisible(true);
                panel_coupe.setVisible(true);
            }
        });
    }



    private void AllNotVisible() {
        linea_sportiva.setVisible(false);
        linea_coupe.setVisible(false);
        linea_citycar.setVisible(false);
        panel_citycar.setVisible(false);
        panel_coupe.setVisible(false);
        panel_sportive.setVisible(false);
    }



    @FXML
    private void A1 (MouseEvent mouseEvent)
    {
        DButils.getInfo(mouseEvent,3);
        DButils.ChangeSceneMouseEvent(mouseEvent,"structureShow.fxml",null,3);
    }
    @FXML
    private void A5 (MouseEvent mouseEvent)
    {
        DButils.getInfo(mouseEvent,2);
        DButils.ChangeSceneMouseEvent(mouseEvent,"structureShow.fxml",null,2);
    }
    @FXML
    private void RS_3 (MouseEvent mouseEvent)
    {
        DButils.getInfo(mouseEvent,1);
        DButils.ChangeSceneMouseEvent(mouseEvent,"structureShow.fxml",null,1);
    }

}
