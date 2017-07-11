package com.example.alina.tetris.figures;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by Alina on 02.04.2017.
 */

public abstract class Figure {

    public int squareWidth;

    public Point point;

    public Figure(int squareWidth, Point point) {
        this.squareWidth = squareWidth;
        this.point = point;
    }

    public abstract Path getPath();

    public abstract void moveLeft();

    public abstract void moveRight();

    public abstract void moveDown();

    public abstract int getColor();
}
