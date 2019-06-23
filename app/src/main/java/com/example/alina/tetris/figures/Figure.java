package com.example.alina.tetris.figures;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;

import com.example.alina.tetris.R;
import com.example.alina.tetris.enums.FigureState;
import com.example.alina.tetris.enums.FigureType;

import java.util.Random;

import static com.example.alina.tetris.Values.EXTRA_ROWS;

/**
 * Created by Alina on 02.04.2017.
 */

public abstract class Figure {

    private FigureState state;

    protected final Context context;

    protected final int squareWidth;

    public int scale;

    public final Point point;

    public final Point coordinatesInNet;

    public boolean[][] figureMask;

    protected Figure(int squareWidth, int scale, Context context) {
        this.squareWidth = squareWidth;
        this.point = initPoint();
        this.scale = scale;
        this.state = FigureState.MOVING;
        this.context = context;
        coordinatesInNet = new Point(getCoordinateInNet(squareWidth, point.x), EXTRA_ROWS - (getHeightInSquare()));
    }

    protected Figure(int squareWidth, int scale, Context context, Point point) {
        this.squareWidth = squareWidth;
        this.point = point;
        this.scale = scale;
        this.state = FigureState.MOVING;
        this.context = context;
        coordinatesInNet = new Point(getCoordinateInNet(squareWidth, point.x), getCoordinateInNet(squareWidth, point.y));
    }

    protected Figure(int widthSquare, Context context, Point point) {
        this.squareWidth = widthSquare / 2;
        this.point = new Point(point.x, point.y);
        this.scale = 0;
        this.state = FigureState.MOVING;
        this.context = context;
        coordinatesInNet = new Point(point.x, point.y);
    }

    private Point initPoint() {
        int[] arrayOfPositions = new int[]{2 * squareWidth, 3 * squareWidth, 4 * squareWidth,
                5 * squareWidth, 6 * squareWidth};
        int position = new Random().nextInt(arrayOfPositions.length);
        return new Point(arrayOfPositions[position], 0);
    }

    private int getCoordinateInNet(int squareWidth, int coordinate) {
        return coordinate / squareWidth;
    }

    public int getCurrentX() {
        return coordinatesInNet.x;
    }

    public int getCurrentY() {
        return coordinatesInNet.y;
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
        coordinatesInNet.set(coordinatesInNet.x - 1, coordinatesInNet.y);
    }

    public void moveRight() {
        point.set(point.x + squareWidth, point.y);
        coordinatesInNet.set(coordinatesInNet.x + 1, coordinatesInNet.y);
    }

    public void moveDown() {
        point.set(point.x, point.y + squareWidth);
        coordinatesInNet.set(coordinatesInNet.x, coordinatesInNet.y + 1);
    }

    public final int getColor() {
        return this.context.getResources().getColor(R.color.jFigure);
    }

    public abstract FigureType getRotatedFigure();

    public abstract int getWidthInSquare();

    public abstract int getHeightInSquare();

    public abstract Path getPath();
}
