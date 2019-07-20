package com.example.alina.tetris.ui.settings;

public interface SettingsView {

    void markChosenColor(int oldColor, int newItemId);

    void setSpeedTitle(int oldItemId, int newItemId);

    void setVerticalHintsChecked(boolean hintsEnabled);

    void setSquaresCountInRow(int squaresCountInRow);
}
