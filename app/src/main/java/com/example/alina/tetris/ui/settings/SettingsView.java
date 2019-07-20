package com.example.alina.tetris.ui.settings;

public interface SettingsView {

    void markChosenColor(int oldColor, int newItemId);

    void setSpeed(int newValue);

    void setSpeedTitle(String figureSpeedTitle);

    void setVerticalHintsChecked(boolean hintsEnabled);

    void setSquaresCountInRow(int squaresCountInRow);
}