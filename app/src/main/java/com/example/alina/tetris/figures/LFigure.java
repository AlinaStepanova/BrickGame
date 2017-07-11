package com.example.alina.tetris.figures;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by Alina on 02.04.2017.
 */

public class LFigure extends Figure {

    private final Point point;

    public LFigure() {
        point = new Point(0, 0);
    }

    @Override
    public Path getPath(final int squareWidth) {
        Path path = new Path();
        path.moveTo(point.x, point.y);
        path.lineTo(point.x, point.y + squareWidth * 3);
        path.lineTo(point.x + squareWidth * 2, point.y + squareWidth * 3);
        path.lineTo(point.x + squareWidth * 2, point.y + squareWidth * 2);
        path.lineTo(point.x + squareWidth, point.y + squareWidth * 2);
        path.lineTo(point.x + squareWidth, point.y);
        path.close();
        return path;
    }
}
