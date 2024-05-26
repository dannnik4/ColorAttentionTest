package com.example.colorsattentiontest;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class ResultController {

    @FXML
    private GridPane colorGrid;
    @FXML
    private TableView<ColorResult> selectionTable;
    @FXML
    private TableColumn<ColorResult, String> selectedColorColumn;
    @FXML
    private TableColumn<ColorResult, String> correctColorColumn;
    @FXML
    private TableColumn<ColorResult, String> indexColumn;
    @FXML
    private TableView<AttemptResult> attemptTable;
    @FXML
    private TableColumn<AttemptResult, String> attemptNumberColumn;
    @FXML
    private TableColumn<AttemptResult, String> correctCountColumn;

    private final List<Color> correctOrder = new ArrayList<>();
    private final List<Color> selectedOrder = new ArrayList<>();
    private final List<AttemptResult> attempts = new ArrayList<>();
    private int attemptNumber = 1;

    public void setCorrectOrder(List<Color> colors) {
        correctOrder.addAll(colors);
        initializeColorGrid();
    }

    private void initializeColorGrid() {
        colorGrid.getChildren().clear(); // Clear any existing children
        for (int i = 0; i < 6; i++) { // Only display 6 colors
            Rectangle rectangle = new Rectangle(50, 50, correctOrder.get(i));
            int row = i / 3;
            int col = i % 3;
            StackPane stack = new StackPane();
            stack.getChildren().add(rectangle);
            colorGrid.add(stack, col, row);

            rectangle.setOnMouseClicked(event -> {
                Color fill = (Color) rectangle.getFill();
                selectedOrder.add(fill);
                updateSelectionTable();
            });
        }
    }

    private void updateSelectionTable() {
        selectionTable.getItems().clear();
        for (int i = 0; i < selectedOrder.size(); i++) {
            String selected = colorToString(selectedOrder.get(i));
            String correct = (i < correctOrder.size()) ? colorToString(correctOrder.get(i)) : "";
            selectionTable.getItems().add(new ColorResult(String.valueOf(i + 1), selected, correct));
        }
        if (selectionTable.getItems().size() > 10) {
            selectionTable.getItems().remove(10, selectionTable.getItems().size());
        }
    }

    @FXML
    private void finishAttempt() {
        updateSelectionTable();
        int correctCount = 0;
        for (int i = 0; i < selectedOrder.size() && i < correctOrder.size(); i++) {
            if (selectedOrder.get(i).equals(correctOrder.get(i))) {
                correctCount++;
            }
        }
        if (selectionTable.getItems().size() == 10) {
            attempts.add(new AttemptResult(String.valueOf(attemptNumber), String.valueOf(correctCount)));
            attemptNumber++;
            attemptTable.getItems().setAll(attempts);
            if (attemptTable.getItems().size() > 5) {
                attemptTable.getItems().remove(5, attemptTable.getItems().size());
            }
            selectedOrder.clear();  // Clear the selectedOrder for a new attempt
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Правильні відповіді: " + correctCount);
        alert.show();
    }

    @FXML
    private void resetAttempts() {
        attempts.clear();
        attemptNumber = 1;
        attemptTable.getItems().clear();
    }

    private String colorToString(Color color) {
        if (color.equals(Color.YELLOW)) return "Жовтий";
        if (color.equals(Color.GREEN)) return "Зелений";
        if (color.equals(Color.RED)) return "Червоний";
        if (color.equals(Color.BLUE)) return "Синій";
        if (color.equals(Color.WHITE)) return "Білий";
        if (color.equals(Color.BLACK)) return "Чорний";
        return "Невідомий";
    }

    public static class ColorResult {
        private final String index;
        private final String selectedColor;
        private final String correctColor;

        public ColorResult(String index, String selectedColor, String correctColor) {
            this.index = index;
            this.selectedColor = selectedColor;
            this.correctColor = correctColor;
        }

        public String getIndex() {
            return index;
        }

        public String getSelectedColor() {
            return selectedColor;
        }

        public String getCorrectColor() {
            return correctColor;
        }
    }

    public static class AttemptResult {
        private final String attemptNumber;
        private final String correctCount;

        public AttemptResult(String attemptNumber, String correctCount) {
            this.attemptNumber = attemptNumber;
            this.correctCount = correctCount;
        }

        public String getAttemptNumber() {
            return attemptNumber;
        }

        public String getCorrectCount() {
            return correctCount;
        }
    }

    @FXML
    public void initialize() {
        indexColumn.setCellValueFactory(new PropertyValueFactory<>("index"));
        selectedColorColumn.setCellValueFactory(new PropertyValueFactory<>("selectedColor"));
        correctColorColumn.setCellValueFactory(new PropertyValueFactory<>("correctColor"));

        attemptNumberColumn.setCellValueFactory(new PropertyValueFactory<>("attemptNumber"));
        correctCountColumn.setCellValueFactory(new PropertyValueFactory<>("correctCount"));
    }
}
