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
    private TableColumn<Result, String> colorsColumn;

    @FXML
    private TableView<Result> summaryTable;

    @FXML
    private TableColumn<Result, Integer> summaryAttemptColumn;

    @FXML
    private TableColumn<Result, String> summaryColorsColumn;

    @FXML
    private HBox colorBox;

    @FXML
    private Label statusLabel;

    private List<Result> results = new ArrayList<>();
    private List<Result> allResults = new ArrayList<>();

    @FXML
    public void initialize() {
        attemptColumn.setCellValueFactory(new PropertyValueFactory<>("attempt"));
        colorsColumn.setCellValueFactory(new PropertyValueFactory<>("colors"));
        summaryAttemptColumn.setCellValueFactory(new PropertyValueFactory<>("attempt"));
        summaryColorsColumn.setCellValueFactory(new PropertyValueFactory<>("colors"));
    }

    public void setCorrectOrder(List<Color> displayedColors) {
        int correctCount = calculateCorrectColors(displayedColors);
        statusLabel.setText("Correct colors: " + correctCount + " out of 10");

        results.add(new Result(results.size() + 1, displayedColors.toString()));
        resultTable.getItems().setAll(results);

        updateSummaryTable();
    }

    public void setAllResults(List<List<Color>> allResults) {
        this.allResults.clear();
        for (int i = 0; i < allResults.size(); i++) {
            this.allResults.add(new Result(i + 1, allResults.get(i).toString()));
        }
        updateSummaryTable();
    }

    public void showShuffledColors(List<Color> shuffledColors) {
        colorBox.getChildren().clear();
        for (Color color : shuffledColors) {
            Rectangle rectangle = new Rectangle(50, 50, color);
            colorBox.getChildren().add(rectangle);
        }
    }

    private void updateSummaryTable() {
        summaryTable.getItems().setAll(allResults);
    }

    private int calculateCorrectColors(List<Color> displayedColors) {
        // Implement the logic to calculate the number of correct colors
        return displayedColors.size(); // Placeholder logic
    }

    @FXML
    private void startNewAttempt() {
        Stage stage = (Stage) resultTable.getScene().getWindow();
        stage.close();
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
        private final String colors;

        public Result(int attempt, String colors) {
            this.attempt = attempt;
            this.colors = colors;
        }

        public int getAttempt() {
            return attempt;
        }

        public String getColors() {
            return colors;
        }
    }
}
