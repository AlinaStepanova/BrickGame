package com.example.alina.tetris.listeners;


public interface OnNetManagerChangedListener {

    void onFigureStoppedMove();

    void onBottomLineIsTrue();

    void onTopLineHasTrue();
}
