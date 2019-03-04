package com.example.alina.tetris.figures;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;

import com.example.alina.tetris.enums.FigureState;
import com.example.alina.tetris.enums.FigureType;

import java.util.Random;

import static com.example.alina.tetris.values.Values.EXTRA_ROWS;

/**
 * Created by Alina on 02.04.2017.
 */

public abstract class Figure {

    private int startX;

    private int startY;

    private FigureState state;

    protected Context context;

    public int squareWidth;

    public int scale;

    public Point point;

    public Point coordinatesInPlayingArea;

    public boolean[][] figureMask;

    public Figure(int squareWidth, int scale, Context context) {
        this.squareWidth = squareWidth;
        this.point = initPoint();
        this.scale = scale;
        this.state = FigureState.MOVING;
        this.context = context;
        coordinatesInPlayingArea = new Point(startX, startY);
    }

    public Figure(int squareWidth, int scale, Context context, Point point) {
        this.squareWidth = squareWidth;
        this.point = point;
        this.scale = scale;
        this.state = FigureState.MOVING;
        this.context = context;
        coordinatesInPlayingArea = new Point(point.x / squareWidth, point.y / squareWidth);
    }

    public Figure(int widthSquare, Context context, Point point) {
        this.squareWidth = widthSquare / 2;
        this.point = new Point(point.x, point.y);
        this.scale = 0;
        this.state = FigureState.MOVING;
        this.context = context;
        coordinatesInPlayingArea = new Point(point.x, point.y);
    }

    private Point initPoint() {
        int[] arrayOfPositions = new int[]{2 * squareWidth, 3 * squareWidth, 4 * squareWidth,
                5 * squareWidth, 6 * squareWidth};
        int position = new Random().nextInt(arrayOfPositions.length);
        Point point = new Point(arrayOfPositions[position], 0);
        startX = point.x / squareWidth;
        startY = EXTRA_ROWS - (getHeightInSquare());
        return point;
    }

    public void increaseScale(int scale) {
        this.scale -= scale;
    }

    public int getCurrentX() {
        return coordinatesInPlayingArea.x;
    }

    public int getCurrentY() {
        return coordinatesInPlayingArea.y;
    }

    public FigureState getState() {
        return state;
    }

    public void setState(FigureState state) {
        this.state = state;
    }

    public void initFigureMask() {
        figureMask = new boolean[getHeightInSquare()][getWidthInSquare()];
    }

    public void initMaskWithFalse() {
        for (int i = 0; i < getHeightInSquare(); i++) {
            for (int j = 0; j < getWidthInSquare(); j++) {
                figureMask[i][j] = false;
            }
        }
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

    public abstract FigureType getRotatedFigure();

    public abstract int getWidthInSquare();

    public abstract int getHeightInSquare();

    public abstract Path getPath();

    public abstract int getColor();
}
