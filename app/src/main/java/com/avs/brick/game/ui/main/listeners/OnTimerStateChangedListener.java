package com.avs.brick.game.ui.main.listeners;

public interface OnTimerStateChangedListener {

    void isTimerRunning(boolean isRunning);

    void disableAllControls();
}
