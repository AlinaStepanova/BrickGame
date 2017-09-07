package com.example.alina.tetris.figures;

import android.graphics.Path;
import android.graphics.Point;

import java.util.Timer;
import java.util.TimerTask;

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

    public void moveLeft() {
        point.set(point.x - squareWidth, point.y);
    }

    public void moveRight() {
        point.set(point.x + squareWidth, point.y);
    }

    public void moveDown() {
        point.set(point.x, point.y + squareWidth);
    }

    public abstract int getColor();
}
