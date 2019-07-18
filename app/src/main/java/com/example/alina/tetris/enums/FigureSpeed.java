package com.example.alina.tetris.enums;

public enum FigureSpeed {

    VERY_FAST(5, 500, "Very fast"),
    FAST(4, 750, "Fast"),
    DEFAULT(3, 1000, "Default"),
    SLOW(2, 1250, "Slow"),
    VERY_SLOW(1, 1500, "Very slow");

    private int speed;
    private long figureSpeedInMillis;
    private String figureSpeedTitle;

    FigureSpeed(int speed, long speedInMillis, String speedTitle) {
        this.speed = speed;
        this.figureSpeedInMillis = speedInMillis;
        this.figureSpeedTitle = speedTitle;
    }

    public int getSpeed() { return speed; }

    public long getFigureSpeedInMillis() {
        return figureSpeedInMillis;
    }

    public String getFigureSpeedTitle() {
        return figureSpeedTitle;
    }
}
