package com.avs.brick.game.ui.settings;

interface SettingsView {

    void markChosenColor(int oldColor, int newItemId);

    void setSpeedTitle(int newItemId);

    void setVerticalHintsChecked(boolean hintsEnabled);

    void setSquaresCountInRow(int squaresCountInRow);
}
