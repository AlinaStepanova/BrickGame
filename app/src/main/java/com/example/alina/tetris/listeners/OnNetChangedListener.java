package com.example.alina.tetris.listeners;


public interface OnNetChangedListener {

    void onFigureStoppedMove();

    void onBottomLineIsTrue();

    void onTopLineHasTrue();
}
