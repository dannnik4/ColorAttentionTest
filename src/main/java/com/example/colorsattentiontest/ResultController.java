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

    private final List<Color> correctOrder = new ArrayList<>();
    private final List<Color> selectedOrder = new ArrayList<>();

    public void setCorrectOrder(List<Color> colors) {
        correctOrder.addAll(colors);
        initializeColorGrid();
    }

    private void initializeColorGrid() {
        colorGrid.getChildren().clear(); // Clear any existing children
        for (int i = 0; i < 6; i++) {
            Rectangle rectangle = new Rectangle(50, 50, correctOrder.get(i % correctOrder.size()));
            int row = i / 3;
            int col = i % 3;
            StackPane stack = new StackPane();
            stack.getChildren().add(rectangle);
            colorGrid.add(stack, col, row);
        }
    }

    private void updateSelectionTable() {
        selectionTable.getItems().clear();
        for (int i = 0; i < selectedOrder.size(); i++) {
            String selected = colorToString(selectedOrder.get(i));
            String correct = (i < correctOrder.size()) ? colorToString(correctOrder.get(i)) : "";
            selectionTable.getItems().add(new ColorResult(selected, correct));
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Правильні відповіді: " + correctCount);
        alert.show();
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
        private final String selectedColor;
        private final String correctColor;

        public ColorResult(String selectedColor, String correctColor) {
            this.selectedColor = selectedColor;
            this.correctColor = correctColor;
        }

        public String getSelectedColor() {
            return selectedColor;
        }

        public String getCorrectColor() {
            return correctColor;
        }
    }

    @FXML
    public void initialize() {
        selectedColorColumn.setCellValueFactory(new PropertyValueFactory<>("selectedColor"));
        correctColorColumn.setCellValueFactory(new PropertyValueFactory<>("correctColor"));
    }
}
