package com.example.alina.tetris.figures;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by Alina on 02.04.2017.
 */

public class SquareFigure extends Figure {

    public SquareFigure(int widthSquare, Point point) {
        super(widthSquare, point);
    }

    @Override public Path getPath() {
        Path path = new Path();
        path.moveTo(point.x, point.y);
        path.lineTo(point.x + squareWidth * 2, point.y);
        path.lineTo(point.x + squareWidth * 2, point.y + squareWidth * 2);
        path.lineTo(point.x, point.y + squareWidth * 2);
        path.close();
        return path;
    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public int getColor() {
        return Color.YELLOW;
    }
}
