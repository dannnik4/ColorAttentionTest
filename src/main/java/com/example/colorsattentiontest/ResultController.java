package com.example.colorsattentiontest;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
    private Button finishButton;

    private final List<Color> correctOrder = new ArrayList<>();
    private final List<Color> selectedOrder = new ArrayList<>();

    public void setCorrectOrder(List<Color> colors) {
        correctOrder.addAll(colors);
        initializeColorGrid();
    }

    private void initializeColorGrid() {
        for (int i = 0; i < correctOrder.size(); i++) {
            Rectangle rectangle = new Rectangle(50, 50, correctOrder.get(i));
            int row = i / 2;
            int col = i % 2;
            colorGrid.add(rectangle, col, row);

            int colorNumber = i + 1;
            Text text = new Text(Integer.toString(colorNumber));
            text.setFill(Color.WHITE);
            StackPane stackPane = new StackPane(rectangle, text);
            StackPane.setAlignment(text, Pos.TOP_LEFT);

            stackPane.setOnMouseClicked(event -> {
                Paint fill = rectangle.getFill();
                if (fill instanceof Color) {
                    selectedOrder.add((Color) fill);
                }
                updateSelectionTable();
            });

            colorGrid.add(stackPane, col, row);
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Correct guesses: " + correctCount);
        alert.show();
    }

    private String colorToString(Color color) {
        if (color.equals(Color.YELLOW)) return "Yellow";
        if (color.equals(Color.GREEN)) return "Green";
        if (color.equals(Color.RED)) return "Red";
        if (color.equals(Color.BLUE)) return "Blue";
        if (color.equals(Color.WHITE)) return "White";
        if (color.equals(Color.BLACK)) return "Black";
        return "Unknown";
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
