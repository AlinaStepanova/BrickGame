package com.example.alina.tetris.figures;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by Alina on 02.04.2017.
 */

public abstract class Figure {

    public int squareWidth;

    private final int startY = 1;

    private final int startX = 4;

    public Point point;

    public Point coordinateInPole;

    protected boolean[][] figureMask;

    public void initFigureMask() {
        figureMask = new boolean[getWidthInSquare()][getHeightInSquare()];
    }

    public abstract int getWidthInSquare();

    public abstract int getHeightInSquare();

    public Figure(int squareWidth, Point point) {
        this.squareWidth = squareWidth;
        this.point = point;
        coordinateInPole = new Point(startX, startY);
    }

    public abstract Path getPath();

    public void moveLeftInNet() {
        int width = getWidthInSquare();
        int height = getHeightInSquare();
        for (int i = coordinateInPole.x; i < coordinateInPole.x + width; i++) {
            swapFigureCoordinatesInNet(coordinateInPole.y, coordinateInPole.y + height, i - 1, i);
        }
    }

    public void moveRightInNet() {
        int width = getWidthInSquare();
        int height = getHeightInSquare();
        for (int i = coordinateInPole.x + width + 1; i > coordinateInPole.x; i--) {
            swapFigureCoordinatesInNet(coordinateInPole.y, coordinateInPole.y + height, i, i + 1);
        }
    }

    public void moveDownInNet() {
        int width = getWidthInSquare();
        int height = getHeightInSquare();
        for (int i = coordinateInPole.y; i < coordinateInPole.y + height; i++) {
            swapFigureCoordinatesInNet(i - 1, i, coordinateInPole.x, coordinateInPole.x + width);
        }

    }

    private void swapFigureCoordinatesInNet(int from, int to, int top, int bottom) {
        /*for (int i = top; i < bottom; i++) {
            int tmp = net[from][i];
            net[from][i] = net[to][i];
            net[to][i] = tmp;
        }*/
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

    public abstract int getColor();
}
