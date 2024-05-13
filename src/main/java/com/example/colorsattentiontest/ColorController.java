package com.example.colorsattentiontest;

import javafx.animation.KeyFrame;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;

public class ColorController {

    @FXML
    private Rectangle[] rectangles; // Массив прямоугольников

    private final List<Color> colors = Arrays.asList(
            Color.GREEN, Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN
    );

    public void initialize() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            changeRectangleColor();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Циклическое выполнение таймлайна
        timeline.play();
    }

    private void changeRectangleColor() {
        Random random = new Random();
        for (Rectangle rectangle : rectangles) { // Итерация по всем прямоугольникам
            Color newColor = colors.get(random.nextInt(colors.size())); // Случайный выбор цвета из списка
            rectangle.setFill(newColor); // Установка нового цвета прямоугольнику
        }
    }
}