package com.example.evricar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("structureHome.fxml")));
        primaryStage.setTitle("Evricar");
        primaryStage.setScene(new Scene(root,1350,780));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException,IOException {
        launch(args);
    }
}

