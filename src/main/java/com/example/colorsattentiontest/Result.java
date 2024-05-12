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

    // Геттеры для attempt и correctColors
    public int getAttempt() {
        return attempt.get();
    }

    public int getCorrectColors() {
        return correctColors.get();
    }

    // Свойства для attempt и correctColors
    public IntegerProperty attemptProperty() {
        return attempt;
    }

    public IntegerProperty correctColorsProperty() {
        return correctColors;
    }
}
