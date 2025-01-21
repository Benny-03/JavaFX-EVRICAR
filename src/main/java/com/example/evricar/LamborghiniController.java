package com.example.evricar;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class LamborghiniController {
    @FXML
    private void Aventador (MouseEvent mouseEvent) {
        DButils.getInfo(mouseEvent,6);
        DButils.ChangeSceneMouseEvent(mouseEvent,"structureShow.fxml",null,6);
    }

    @FXML
    private void Huracan (MouseEvent mouseEvent) {
        DButils.getInfo(mouseEvent,5);
        DButils.ChangeSceneMouseEvent(mouseEvent,"structureShow.fxml",null,5);
    }

    @FXML
    private void Sto (MouseEvent mouseEvent) {
        DButils.getInfo(mouseEvent,4);
        DButils.ChangeSceneMouseEvent(mouseEvent,"structureShow.fxml",null,4);
    }
}
