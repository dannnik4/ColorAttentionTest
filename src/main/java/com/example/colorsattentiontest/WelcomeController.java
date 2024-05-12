package com.example.colorsattentiontest;

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
    protected void startTest() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("color-test.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Тест на дослідження оперативної пам’яті");
        stage.show();
    }

    @FXML
    private MenuItem aboutMenuItem;

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