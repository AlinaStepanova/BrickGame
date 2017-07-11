package com.example.alina.tetris.figures;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by Alina on 02.04.2017.
 */

public class SquareFigure extends Figure {

    private final Point point;

    public SquareFigure() {
        point = new Point(0, 200);
    }

    @Override public Path getPath(final int squareWidth) {
        Path path = new Path();
        path.moveTo(point.x, point.y);
        path.lineTo(point.x + squareWidth * 2, point.y);
        path.lineTo(point.x + squareWidth * 2, point.y + squareWidth * 2);
        path.lineTo(point.x, point.y + squareWidth * 2);
        path.close();
        return path;
    }
}
