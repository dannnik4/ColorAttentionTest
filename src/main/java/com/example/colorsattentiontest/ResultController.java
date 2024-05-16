package com.example.colorsattentiontest;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ResultController {
    @FXML
    private TableView<Result> resultTable;

    @FXML
    private TableColumn<Result, Integer> attemptColumn;

    @FXML
    private TableColumn<Result, String> resultColumn;

    public void initialize() {
    }

    @FXML
    private void startNewAttempt() {
    }

    @FXML
    private void resetTable() {
        resultTable.getItems().clear();
    }

    public void addResult(int attempt, int correctColors) {
        resultTable.getItems().add(new Result(attempt, correctColors));
    }
}