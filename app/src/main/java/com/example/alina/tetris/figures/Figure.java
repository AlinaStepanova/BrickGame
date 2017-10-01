package com.example.alina.tetris.figures;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by Alina on 02.04.2017.
 */

public abstract class Figure {

    private final int startY = 1;
    private final int startX = 4;
    public int squareWidth;
    public Point point;

    public Point coordinateInPole;

    public boolean[][] figureMask;

    public Figure(int squareWidth, Point point) {
        this.squareWidth = squareWidth;
        this.point = point;
        coordinateInPole = new Point(startX, startY);
    }

    public void initFigureMask() {
        figureMask = new boolean[getWidthInSquare()][getHeightInSquare()];
    }

    public void moveLeft() {
        point.set(point.x - squareWidth, point.y);
        coordinateInPole.set(coordinateInPole.x - 1, coordinateInPole.y);
    }

    public void moveRight() {
        point.set(point.x + squareWidth, point.y);
        coordinateInPole.set(coordinateInPole.x + 1, coordinateInPole.y);
    }

    public void moveDown() {
        point.set(point.x, point.y + squareWidth);
        coordinateInPole.set(coordinateInPole.x, coordinateInPole.y + 1);
    }

    public abstract int getWidthInSquare();

    public abstract int getHeightInSquare();

    public abstract Path getPath();

    public abstract int getColor();
}
