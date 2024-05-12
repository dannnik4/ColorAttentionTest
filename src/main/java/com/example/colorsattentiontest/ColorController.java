package com.example.colorsattentiontest;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ColorController {
    @FXML
    private Rectangle colorRectangle;

    public void initialize() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            // Здесь будет логика для смены цвета прямоугольника каждую секунду
        }));
        timeline.setCycleCount(10); // Смена цвета каждую секунду в течение 10 секунд
        timeline.play();
    }
}