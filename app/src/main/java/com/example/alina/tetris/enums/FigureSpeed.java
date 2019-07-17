package com.example.alina.tetris.enums;

public enum FigureSpeed {

    VERY_FAST(500),
    FAST(750),
    DEFAULT(1000),
    SLOW(1250),
    VERY_SLOW(1500);

    private int figureSpeed;

    FigureSpeed(int speed) {
        this.figureSpeed = speed;
    }

    public int getFigureSpeed() {
        return figureSpeed;
    }
}
