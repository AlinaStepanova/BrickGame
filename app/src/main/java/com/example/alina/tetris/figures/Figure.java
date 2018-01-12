package com.example.alina.tetris.figures;

import android.graphics.Path;
import android.graphics.Point;
import android.util.Log;

import java.util.Random;

/**
 * Created by Alina on 02.04.2017.
 */

public abstract class Figure {

    private int startX;
    private final int startY = 0;
    public int squareWidth;
    public Point point;

    public Point coordinatesInPlayingArea;

    public boolean[][] figureMask;

    public Figure(int squareWidth) {
        this.squareWidth = squareWidth;
        this.point = initPoint();
        coordinatesInPlayingArea = new Point(startX, startY);
    }

    private Point initPoint() {
        int [] array = new int[] {2 * squareWidth, 3 * squareWidth, 4 * squareWidth, 5 * squareWidth};
        int position = new Random().nextInt(array.length);
        Point point = new Point(array[position], 0);
        startX = point.x / squareWidth;
        Log.d("point", point.toString() + " " + startX);
        return point;
    }

    public int getStartX() {
        return startX;
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
