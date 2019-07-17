package com.example.alina.tetris.ui.settings;

public interface SettingsView {

    void markChosenColor(int oldColor, int newItemId);

    void setSpeed(int newValue);
}
