package com.example.alina.tetris.figures;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;
import android.util.Log;

/**
 * Created by Alina on 02.04.2017.
 */

public class SquareFigure extends Figure {

    private final int SCALE_HEIGHT = squareWidth;

    public SquareFigure(int squareWidth, int scale) {
        super(squareWidth, scale);
        this.scale += SCALE_HEIGHT;
    }

    @Override
    public void initFigureMask() {
        super.initFigureMask();
        figureMask[0][0] = true;
        figureMask[0][1] = true;
        figureMask[1][0] = true;
        figureMask[1][1] = true;
    }

    @Override
    public int getWidthInSquare() {
        return 2;
    }

    @Override
    public int getHeightInSquare() {
        return 2;
    }

    @Override
    public Path getPath() {
        Path path = new Path();
        path.moveTo(point.x, point.y - scale);
        path.lineTo(point.x + squareWidth * 2, point.y - scale);
        path.lineTo(point.x + squareWidth * 2, point.y + squareWidth * 2 - scale);
        path.lineTo(point.x, point.y + squareWidth * 2 - scale);
        path.close();
        return path;
    }

    @Override
    public int getColor() {
        return Color.YELLOW;
    }
}
