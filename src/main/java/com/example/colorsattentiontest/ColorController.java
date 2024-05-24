package com.example.colorsattentiontest;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    private final List<Color> colors = List.of(
            Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE, Color.WHITE, Color.BLACK
    );

    private final List<Color> displayedColors = new ArrayList<>();

    @FXML
    public void initialize() {
        List<Color> randomColors = new ArrayList<>(colors);
        Collections.shuffle(randomColors);

        Timeline timeline = new Timeline();
        for (int i = 0; i < 10; i++) {
            Color color = randomColors.get(i % colors.size());
            displayedColors.add(color);
            int colorIndex = i + 1;

            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i + 1), event -> {
                rectangle.setFill(color);
                Text colorIndexText = new Text(String.valueOf(colorIndex));
                colorIndexText.setFill(color.equals(Color.BLACK) ? Color.WHITE : Color.BLACK);
                colorIndexText.setStyle("-fx-font-size: 24px;");
                StackPane.setAlignment(colorIndexText, javafx.geometry.Pos.TOP_LEFT);
                stackPane.getChildren().clear();
                stackPane.getChildren().addAll(rectangle, colorIndexText);
            });

            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setOnFinished(event -> showResultScreen());
        timeline.play();
    }

    private void showResultScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("result-view.fxml"));
            Scene scene = new Scene(loader.load());

            ResultController resultController = loader.getController();
            resultController.setCorrectOrder(displayedColors);

            Stage stage = (Stage) stackPane.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
