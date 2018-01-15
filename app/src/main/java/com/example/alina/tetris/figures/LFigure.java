package com.example.alina.tetris.figures;

import android.graphics.Color;
import android.graphics.Path;

/**
 * Created by Alina on 02.04.2017.
 */

public class LFigure extends Figure {

    private final int SCALE_HEIGHT = 2 * squareWidth;

    public LFigure(int squareWidth, int scale) {
        super(squareWidth, scale);
        this.scale += SCALE_HEIGHT;
    }

    @Override
    public void initFigureMask() {
        super.initFigureMask();
        figureMask[0][0] = true;
        figureMask[1][0] = true;
        figureMask[2][0] = true;
        figureMask[2][1] = true;
    }

    @Override
    public int getWidthInSquare() {
        return 2;
    }

    @Override
    public int getHeightInSquare() {
        return 3;
    }

    @Override
    public Path getPath() {
        Path path = new Path();
        path.moveTo(point.x, point.y - scale);
        path.lineTo(point.x, point.y + squareWidth * 3 - scale);
        path.lineTo(point.x + squareWidth * 2, point.y + squareWidth * 3 - scale);
        path.lineTo(point.x + squareWidth * 2, point.y + squareWidth * 2 - scale);
        path.lineTo(point.x + squareWidth, point.y + squareWidth * 2 - scale);
        path.lineTo(point.x + squareWidth, point.y - scale);
        path.close();
        return path;
    }

    @Override
    public int getColor() {
        return Color.RED;
    }
}
