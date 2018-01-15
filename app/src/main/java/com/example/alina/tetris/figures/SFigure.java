package com.example.alina.tetris.figures;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by Alina on 02.04.2017.
 */

public class SFigure extends Figure {

    public SFigure(int squareWidth, int scale) {
        super(squareWidth, scale);
    }

    @Override
    public void initFigureMask() {
        super.initFigureMask();
        figureMask[0][1] = true;
        figureMask[0][2] = true;
        figureMask[1][0] = true;
        figureMask[1][1] = true;
    }

    @Override
    public int getWidthInSquare() {
        return 3;
    }

    @Override
    public int getHeightInSquare() {
        return 2;
    }

    @Override
    public Path getPath() {
        Path path = new Path();
        path.moveTo(point.x + squareWidth, point.y - scale);
        path.lineTo(point.x + squareWidth, point.y - squareWidth - scale);
        path.lineTo(point.x + squareWidth * 3, point.y - squareWidth - scale);
        path.lineTo(point.x + squareWidth * 3, point.y - scale);
        path.lineTo(point.x + squareWidth * 2, point.y - scale);
        path.lineTo(point.x + squareWidth * 2, point.y + squareWidth - scale);
        path.lineTo(point.x, point.y + squareWidth - scale);
        path.lineTo(point.x, point.y - scale);
        path.close();
        return path;
    }

    @Override
    public int getColor() {
        return Color.GREEN;
    }
}
