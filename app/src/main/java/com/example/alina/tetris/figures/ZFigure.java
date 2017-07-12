package com.example.alina.tetris.figures;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by Alina on 02.04.2017.
 */

public class ZFigure extends Figure {

    public ZFigure(int widthSquare, Point point) {
        super(widthSquare, point);
    }

    @Override public Path getPath() {
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

    @Override
    public int getColor() {
        return Color.GRAY;
    }
}
