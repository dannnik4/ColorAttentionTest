package com.example.colorsattentiontest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private Button startButton;

    @FXML
    private void startTest() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("color-view.fxml"));
            Scene scene = new Scene(loader.load(), 400, 400);

            ColorController colorController = loader.getController();
            colorController.startTest();

            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        aboutMenuItem.setOnAction(event -> handleAboutMenuAction());
    }

    @FXML
    private void handleAboutMenuAction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Автор");
        alert.setHeaderText("Перстньов Даніїл. КН Маг 11. 2024 рік.");
        alert.showAndWait();
    }
}
