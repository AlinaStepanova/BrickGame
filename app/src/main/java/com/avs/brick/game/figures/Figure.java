package com.avs.brick.game.figures;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;

import com.avs.brick.game.R;
import com.avs.brick.game.enums.FigureState;
import com.avs.brick.game.enums.FigureType;

import java.util.ArrayList;
import java.util.Random;

import static com.avs.brick.game.Values.EXTRA_ROWS;

/**
 * Created by Alina on 02.04.2017.
 */

public abstract class Figure {

    private FigureState state;

    protected final Context context;

    protected final int squareWidth;

    public int scale;

    public final Point pointOnScreen;

    public final Point pointInNet;

    public boolean[][] figureMask;

    protected Figure(int squareWidth, int scale, int squaresCountInRow, Context context) {
        this.squareWidth = squareWidth;
        this.pointOnScreen = initPoint(squaresCountInRow);
        this.scale = scale;
        this.state = FigureState.MOVING;
        this.context = context;
        pointInNet = new Point(getCoordinateInNet(squareWidth, pointOnScreen.x),
                EXTRA_ROWS - (getHeightInSquare()));
    }

    protected Figure(int squareWidth, int scale, Context context, Point pointOnScreen) {
        this.squareWidth = squareWidth;
        this.pointOnScreen = pointOnScreen;
        this.scale = scale;
        this.state = FigureState.MOVING;
        this.context = context;
        pointInNet = new Point(getCoordinateInNet(squareWidth, pointOnScreen.x),
                getCoordinateInNet(squareWidth, pointOnScreen.y));
    }

    protected Figure(int widthSquare, Context context, Point pointOnScreen) {
        this.squareWidth = widthSquare / 2;
        this.pointOnScreen = new Point(pointOnScreen.x, pointOnScreen.y);
        this.scale = 0;
        this.state = FigureState.MOVING;
        this.context = context;
        pointInNet = new Point(pointOnScreen.x, pointOnScreen.y);
    }

    private Point initPoint(int squaresCountInRow) {
        ArrayList<Integer> arrayOfPositions = new ArrayList<>();
        for (int i = 2; i < squaresCountInRow - EXTRA_ROWS; i++) {
            arrayOfPositions.add(i * squareWidth);
        }
        int position = new Random().nextInt(arrayOfPositions.size());
        return new Point(arrayOfPositions.get(position), 0);
    }

    private int getCoordinateInNet(int squareWidth, int coordinate) {
        return coordinate / squareWidth;
    }

    public int getCurrentX() {
        return pointInNet.x;
    }

    public int getCurrentY() {
        return pointInNet.y;
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
        pointOnScreen.set(pointOnScreen.x - squareWidth, pointOnScreen.y);
        pointInNet.set(pointInNet.x - 1, pointInNet.y);
    }

    public void moveRight() {
        pointOnScreen.set(pointOnScreen.x + squareWidth, pointOnScreen.y);
        pointInNet.set(pointInNet.x + 1, pointInNet.y);
    }

    public void moveDown() {
        pointOnScreen.set(pointOnScreen.x, pointOnScreen.y + squareWidth);
        pointInNet.set(pointInNet.x, pointInNet.y + 1);
    }

    public final int getColor() {
        return this.context.getResources().getColor(R.color.colorPrimaryTransparent);
    }

    public abstract FigureType getRotatedFigure();

    public abstract int getWidthInSquare();

    public abstract int getHeightInSquare();

    public abstract Path getPath();
}
