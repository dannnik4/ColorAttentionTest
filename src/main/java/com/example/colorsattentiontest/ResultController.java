package com.example.colorsattentiontest;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ResultController {

    @FXML
    private TableView<Result> resultTable;

    @FXML
    private TableColumn<Result, Integer> attemptColumn;

    @FXML
    private TableColumn<Result, String> selectedColorColumn;

    @FXML
    private TableColumn<Result, String> correctColorColumn;

    @FXML
    private TableView<Summary> summaryTable;

    @FXML
    private TableColumn<Summary, Integer> summaryAttemptColumn;

    @FXML
    private TableColumn<Summary, String> summaryColorsColumn;

    @FXML
    private HBox colorBox;

    @FXML
    private Label statusLabel;

    private List<Result> results = new ArrayList<>();
    private List<Summary> allResults = new ArrayList<>();
    private List<Color> correctColors;

    @FXML
    public void initialize() {
        attemptColumn.setCellValueFactory(new PropertyValueFactory<>("attempt"));
        selectedColorColumn.setCellValueFactory(new PropertyValueFactory<>("selectedColor"));
        correctColorColumn.setCellValueFactory(new PropertyValueFactory<>("correctColor"));
        summaryAttemptColumn.setCellValueFactory(new PropertyValueFactory<>("attempt"));
        summaryColorsColumn.setCellValueFactory(new PropertyValueFactory<>("summary"));

        correctColors = generateCorrectColors(); // Генерация правильных цветов
    }

    private List<Color> generateCorrectColors() {
        // Генерация правильных цветов, например, случайных
        return List.of(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PURPLE, Color.ORANGE);
    }

    public void addResult(int attempt, Color selectedColor) {
        String selectedColorName = getColorName(selectedColor);
        String correctColorName = getColorName(correctColors.get(attempt - 1));

        results.add(new Result(attempt, selectedColorName, correctColorName));
        resultTable.getItems().setAll(results);
    }

    private String getColorName(Color color) {
        if (color.equals(Color.RED)) return "Red";
        if (color.equals(Color.GREEN)) return "Green";
        if (color.equals(Color.BLUE)) return "Blue";
        if (color.equals(Color.YELLOW)) return "Yellow";
        if (color.equals(Color.PURPLE)) return "Purple";
        if (color.equals(Color.ORANGE)) return "Orange";
        return "Unknown";
    }

    public void setCorrectOrder(List<Color> displayedColors) {
        for (int i = 0; i < displayedColors.size(); i++) {
            addResult(i + 1, displayedColors.get(i));
        }
        updateSummaryTable();
    }

    public void updateSummaryTable() {
        int correctCount = calculateCorrectColors();
        allResults.add(new Summary(allResults.size() + 1, correctCount + " з 10"));
        summaryTable.getItems().setAll(allResults);
    }

    private int calculateCorrectColors() {
        int correctCount = 0;
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getSelectedColor().equals(results.get(i).getCorrectColor())) {
                correctCount++;
            }
        }
        return correctCount;
    }

    public void showShuffledColors(List<Color> shuffledColors) {
        colorBox.getChildren().clear();
        for (Color color : shuffledColors) {
            Rectangle rectangle = new Rectangle(50, 50, color);
            colorBox.getChildren().add(rectangle);
        }
    }

    @FXML
    private void startNewAttempt() {
        if (results.size() == correctColors.size()) {
            updateSummaryTable();
        }
        results.clear();
        resultTable.getItems().clear();
    }

    @FXML
    private void resetAttempts() {
        results.clear();
        resultTable.getItems().clear();
        allResults.clear();
        summaryTable.getItems().clear();
    }

    public static class Result {
        private final int attempt;
        private final String selectedColor;
        private final String correctColor;

        public Result(int attempt, String selectedColor, String correctColor) {
            this.attempt = attempt;
            this.selectedColor = selectedColor;
            this.correctColor = correctColor;
        }

        public int getAttempt() {
            return attempt;
        }

        public String getSelectedColor() {
            return selectedColor;
        }

        public String getCorrectColor() {
            return correctColor;
        }
    }

    public static class Summary {
        private final int attempt;
        private final String summary;

        public Summary(int attempt, String summary) {
            this.attempt = attempt;
            this.summary = summary;
        }

        public int getAttempt() {
            return attempt;
        }

        public String getSummary() {
            return summary;
        }
    }
}
