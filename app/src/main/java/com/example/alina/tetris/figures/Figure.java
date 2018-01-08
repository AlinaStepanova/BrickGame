package com.example.alina.tetris.figures;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by Alina on 02.04.2017.
 */

public abstract class Figure {

    private final int startX = 0;
    private final int startY = 0;
    public int squareWidth;
    public Point point;

    public Point coordinatesInPlayingArea;

    public boolean[][] figureMask;

    public Figure(int squareWidth, Point point) {
        this.squareWidth = squareWidth;
        this.point = point;
        coordinatesInPlayingArea = new Point(startX, startY);
    }

    public void initFigureMask() {
        figureMask = new boolean[getHeightInSquare()][getWidthInSquare()];
    }

    public void moveLeft() {
        point.set(point.x - squareWidth, point.y);
        coordinatesInPlayingArea.set(coordinatesInPlayingArea.x - 1, coordinatesInPlayingArea.y);
    }

    public void moveRight() {
        point.set(point.x + squareWidth, point.y);
        coordinatesInPlayingArea.set(coordinatesInPlayingArea.x + 1, coordinatesInPlayingArea.y);
    }

    public void moveDown() {
        point.set(point.x, point.y + squareWidth);
        coordinatesInPlayingArea.set(coordinatesInPlayingArea.x, coordinatesInPlayingArea.y + 1);
    }

    public abstract int getWidthInSquare();

    public abstract int getHeightInSquare();

    public abstract Path getPath();

    public abstract int getColor();
}
