package com.example.colorsattentiontest;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ColorController {

    @FXML
    private Rectangle rectangle;

    private final List<Color> colors = List.of(Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE, Color.WHITE, Color.BLACK);
    private final List<Color> shownColors = new ArrayList<>();
    private int currentIndex = 0;

    public void initialize() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> showNextColor()));
        timeline.setCycleCount(10);
        timeline.play();
    }

    private void showNextColor() {
        if (currentIndex < 10) {
            Color color = getRandomColor();
            shownColors.add(color);
            rectangle.setFill(color);
            currentIndex++;
        } else {
            showResultScreen();
        }
    }

    private Color getRandomColor() {
        Random random = new Random();
        return colors.get(random.nextInt(colors.size()));
    }

    private void showResultScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("result-view.fxml"));
            Parent root = loader.load();
            ResultController controller = loader.getController();
            controller.setCorrectOrder(shownColors);
            Scene scene = new Scene(root);
            Stage stage = (Stage) rectangle.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
