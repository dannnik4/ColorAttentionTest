package com.example.colorsattentiontest;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColorController {

    @FXML
    private StackPane stackPane;

    @FXML
    private Rectangle rectangle;

    @FXML
    private HBox colorBox;

    private final List<Color> colors = List.of(
            Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE, Color.WHITE, Color.BLACK
    );

    private final List<Color> displayedColors = new ArrayList<>();
    private final List<List<Color>> allResults = new ArrayList<>();

    @FXML
    public void initialize() {
        rectangle.setFill(Color.WHITE);
    }

    public void startTest() {
        List<Color> randomColors = new ArrayList<>(colors);
        Collections.shuffle(randomColors);

        Timeline countdown = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> showCountdown(3)),
                new KeyFrame(Duration.seconds(2), event -> showCountdown(2)),
                new KeyFrame(Duration.seconds(3), event -> showCountdown(1)),
                new KeyFrame(Duration.seconds(4), event -> startColorSequence(randomColors))
        );
        countdown.play();
    }

    private void showCountdown(int number) {
        stackPane.getChildren().clear();
        rectangle.setFill(Color.WHITE);
        Text countdownText = new Text(String.valueOf(number));
        countdownText.setStyle("-fx-font-size: 72px;");
        stackPane.getChildren().addAll(rectangle, countdownText);
    }

    private void startColorSequence(List<Color> randomColors) {
        displayedColors.clear(); // Очистка перед новым тестом
        Timeline timeline = new Timeline();
        for (int i = 0; i < 10; i++) {
            Color color = randomColors.get(i % colors.size());
            displayedColors.add(color);
            int colorIndex = i + 1;

            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i + 1), event -> {
                rectangle.setFill(color);
                Text colorIndexText = new Text(String.valueOf(colorIndex));
                colorIndexText.setFill((color.equals(Color.BLACK) || color.equals(Color.BLUE) || color.equals(Color.RED)) ? Color.WHITE : Color.BLACK);
                colorIndexText.setStyle("-fx-font-size: 24px;");
                stackPane.getChildren().clear();
                stackPane.getChildren().addAll(rectangle, colorIndexText);

                if (colorIndex == 10) {
                    showResults();
                }
            });

            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.play();
    }

    private void showResults() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("result-view.fxml"));
            Scene scene = new Scene(loader.load(), 600, 600);

            ResultController resultController = loader.getController();
            resultController.setCorrectOrder(displayedColors);
            resultController.setAllResults(allResults);

            List<Color> shuffledColors = new ArrayList<>(colors);
            Collections.shuffle(shuffledColors);
            resultController.showShuffledColors(shuffledColors);

            Stage stage = (Stage) stackPane.getScene().getWindow();
            stage.setScene(scene);

            allResults.add(new ArrayList<>(displayedColors)); // Сохранение текущего результата

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void finishAttempt() {
        startTest();
    }

    @FXML
    private void resetAttempts() {
        allResults.clear();
        startTest();
    }
}
