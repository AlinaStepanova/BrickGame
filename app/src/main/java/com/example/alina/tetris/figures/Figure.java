package com.example.alina.tetris.figures;

import android.graphics.Path;
import android.graphics.Point;
import android.util.Log;

import com.example.alina.tetris.NetManager;

import java.util.Random;

/**
 * Created by Alina on 02.04.2017.
 */

public abstract class Figure {

    private int startX;

    private int startY;

    public int squareWidth;

    public int scale;

    public Point point;

    public Point coordinatesInPlayingArea;

    public boolean[][] figureMask;

    public Figure(int squareWidth, int scale) {
        this.squareWidth = squareWidth;
        this.point = initPoint();
        this.scale = scale;
        coordinatesInPlayingArea = new Point(startX, startY);
    }

    private Point initPoint() {
        int [] arrayOfPositions = new int[] {2 * squareWidth, 3 * squareWidth, 4 * squareWidth,
                5 * squareWidth, 6 * squareWidth, 7 * squareWidth};
        int position = new Random().nextInt(arrayOfPositions.length);
        Point point = new Point(arrayOfPositions[position], 0);
        startX = point.x / squareWidth;
        startY = NetManager.EXTRA_ROWS - (getHeightInSquare());
        return point;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
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
