package com.example.colorsattentiontest;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Result {
    private final IntegerProperty attempt;
    private final IntegerProperty correctColors;

    public Result(int attempt, int correctColors) {
        this.attempt = new SimpleIntegerProperty(attempt);
        this.correctColors = new SimpleIntegerProperty(correctColors);
    }

    public int getAttempt() {
        return attempt.get();
    }

    public int getCorrectColors() {
        return correctColors.get();
    }

    public IntegerProperty attemptProperty() {
        return attempt;
    }

    public IntegerProperty correctColorsProperty() {
        return correctColors;
    }
}
