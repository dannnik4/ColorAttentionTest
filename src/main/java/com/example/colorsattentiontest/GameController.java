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
        // Инициализация игры
    }

    @FXML
    private void onColorClicked(ActionEvent event) {
        Rectangle clickedRectangle = (Rectangle) event.getSource();
        Color selectedColor = (Color) clickedRectangle.getFill();

        if (selectedColor == sequence[currentIndex]) {
            currentIndex++;

            if (currentIndex == sequence.length) {
                // Пользователь выбрал все цвета правильно
                // Показать результат
            }
        } else {
            // Пользователь сделал ошибку
            // Показать результат
        }
    }
}