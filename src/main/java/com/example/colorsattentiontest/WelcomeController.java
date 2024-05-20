package com.example.colorsattentiontest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {
    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    public void startTest(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("color-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
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
