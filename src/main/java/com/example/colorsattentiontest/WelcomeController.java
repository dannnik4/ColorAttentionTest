package com.example.colorsattentiontest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private void startTest() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("color-view.fxml"));
            Scene scene = new Scene(loader.load(), 400, 400);

            ColorController colorController = loader.getController();
            colorController.startTest();

            Stage stage = (Stage) ((Stage) ((Stage) ((javafx.scene.Node) loader.getRoot()).getScene().getWindow()));
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
