package com.example.alina.tetris.figures;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by Alina on 02.04.2017.
 */

public abstract class Figure {

    public int squareWidth;

    private final int StartY = 1;

    private final int startX = 4;

    public Point point;

    public Point coordinateInPole;

    protected boolean[][] figureMask;

    public void initFigureMask() {
        figureMask = new boolean[getWidthInSquare()][getHeightInSquare()];
    }

    /**
     * @return width in squares
     */
    public abstract int getWidthInSquare();

    /**
     * @return height in squares
     */
    public abstract int getHeightInSquare();

    public Figure(int squareWidth, Point point) {
        this.squareWidth = squareWidth;
        this.point = point;
        coordinateInPole = new Point(startX, StartY);
    }

    public abstract Path getPath();

    public void moveLeft() {
        // TODO: 9/13/17 add change #coordinateInPole
        point.set(point.x - squareWidth, point.y);
        coordinateInPole.set(coordinateInPole.x - 1, coordinateInPole.y);
    }

    public void moveRight() {
        // TODO: 9/13/17 add change #coordinateInPole
        point.set(point.x + squareWidth, point.y);
        coordinateInPole.set(coordinateInPole.x + 1, coordinateInPole.y);
    }

    public void moveDown() {
        // TODO: 9/13/17 add change #coordinateInPole
        point.set(point.x, point.y + squareWidth);
        coordinateInPole.set(coordinateInPole.x, coordinateInPole.y + 1);
    }

    public abstract int getColor();
}
