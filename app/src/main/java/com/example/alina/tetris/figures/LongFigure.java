package com.example.alina.tetris.figures;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by Alina on 02.04.2017.
 */

public class LongFigure extends Figure {

    private final Point point;

    public LongFigure() {
        point = new Point(340, 0);
    }

    @Override public Path getPath(final int squareWidth) {
        Path path = new Path();
        path.moveTo(point.x, point.y);
        path.lineTo(point.x + squareWidth, point.y);
        path.lineTo(point.x + squareWidth, point.y + squareWidth * 4);
        path.lineTo(point.x, point.y + squareWidth * 4);
        path.close();
        return path;
    }
}
