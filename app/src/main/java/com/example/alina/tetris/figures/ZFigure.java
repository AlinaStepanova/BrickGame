package com.example.alina.tetris.figures;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by Alina on 02.04.2017.
 */

public class ZFigure extends Figure {

    private final Point point;

    public ZFigure() {
        point = new Point(120, 440);
    }

    @Override public Path getPath(final int squareWidth) {
        Path path = new Path();
        path.moveTo(point.x, point.y);
        path.lineTo(point.x - squareWidth * 2, point.y);
        path.lineTo(point.x - squareWidth * 2, point.y - squareWidth);
        path.lineTo(point.x, point.y - squareWidth);
        path.lineTo(point.x, point.y);
        path.lineTo(point.x + squareWidth, point.y);
        path.lineTo(point.x + squareWidth, point.y + squareWidth);
        path.lineTo(point.x - squareWidth, point.y + squareWidth);
        path.lineTo(point.x - squareWidth, point.y);
        path.close();
        return path;
    }
}
