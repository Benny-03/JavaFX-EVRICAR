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

public class RangeController implements Initializable {

    @FXML
    private TextField linea_offRoad;
    @FXML
    private TextField linea_suv;

    @FXML
    private Button btn_suv;
    @FXML
    private Button btn_offRoad;

    @FXML
    private Pane panel_offRoad;
    @FXML
    private Pane panel_suv;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_suv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AllNotVisible();
                linea_suv.setVisible(true);
                panel_suv.setVisible(true);
            }
        });
        btn_offRoad.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AllNotVisible();
                linea_suv.setVisible(true);
                panel_suv.setVisible(true);
            }
        });
    }

    private void AllNotVisible() {
        linea_suv.setVisible(false);
        linea_offRoad.setVisible(false);
        panel_offRoad.setVisible(false);
        panel_suv.setVisible(false);
    }



    @FXML
    private void Range (MouseEvent mouseEvent) {
        DButils.getInfo(mouseEvent,15);
        DButils.ChangeSceneMouseEvent(mouseEvent,"structureShow.fxml",null,15);
    }
    @FXML
    private void Defender (MouseEvent mouseEvent) {
        DButils.getInfo(mouseEvent,16);
        DButils.ChangeSceneMouseEvent(mouseEvent,"structureShow.fxml",null,16);
    }
    @FXML
    private void Discovery (MouseEvent mouseEvent) {
        DButils.getInfo(mouseEvent,17);
        DButils.ChangeSceneMouseEvent(mouseEvent,"structureShow.fxml",null,17);
    }
}
