package com.example.alina.tetris.figures;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by Alina on 02.04.2017.
 */

public class LongFigure extends Figure {

    public LongFigure(int widthSquare, Point point) {
        super(widthSquare, point);
    }

    @Override public Path getPath() {
        Path path = new Path();
        path.moveTo(point.x, point.y);
        path.lineTo(point.x + squareWidth, point.y);
        path.lineTo(point.x + squareWidth, point.y + squareWidth * 4);
        path.lineTo(point.x, point.y + squareWidth * 4);
        path.close();
        return path;
    }

    @Override
    public int getColor() {
        return Color.CYAN;
    }
}
