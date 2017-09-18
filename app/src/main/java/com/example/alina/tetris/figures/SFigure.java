package com.example.alina.tetris.figures;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by Alina on 02.04.2017.
 */

public class SFigure extends Figure {

    @Override
    public void initFigureMask() {
        super.initFigureMask();
        figureMask[0][1] = true;
        figureMask[0][2] = true;
        figureMask[1][0] = true;
        figureMask[1][1] = true;
    }

    public SFigure(int widthSquare, Point point) {
        super(widthSquare, point);
    }

    @Override
    public int getWidthInSquare() {
        return 3;
    }

    @Override
    public int getHeightInSquare() {
        return 2;
    }

    @Override public Path getPath() {
        Path path = new Path();
        path.moveTo(point.x, point.y);
        path.lineTo(point.x, point.y - squareWidth);
        path.lineTo(point.x + squareWidth * 2, point.y - squareWidth);
        path.lineTo(point.x + squareWidth * 2, point.y);
        path.lineTo(point.x + squareWidth, point.y);
        path.lineTo(point.x + squareWidth, point.y + squareWidth);
        path.lineTo(point.x - squareWidth, point.y + squareWidth);
        path.lineTo(point.x - squareWidth, point.y);
        path.close();
        return path;
    }

    @Override
    public int getColor() {
        return Color.GREEN;
    }
}
