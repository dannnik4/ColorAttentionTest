package com.example.colorsattentiontest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameController {
    private Color[] sequence = {Color.GREEN, Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN};
    private int currentIndex = 0;

    @FXML
    private Rectangle greenIcon;
    @FXML
    private Rectangle yellowIcon;
    @FXML
    private Rectangle redIcon;
    @FXML
    private Rectangle blueIcon;

    public void initialize() {
        initializeGame();
    }

    private void initializeGame() {
    }
}