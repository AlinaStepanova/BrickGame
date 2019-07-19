package com.example.alina.tetris.figures.figure_s;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;

import com.example.alina.tetris.enums.FigureType;
import com.example.alina.tetris.figures.Figure;

/**
 * Created by Alina on 02.04.2017.
 */

public class SFigure extends Figure {

    public SFigure(int squareWidth, int scale, int squaresCountInRow, Context context) {
        super(squareWidth, scale, squaresCountInRow, context);
    }

    public SFigure(int widthSquare, Context context, Point point) {
        super(widthSquare, context, point);
    }

    public SFigure(int squareWidth, int scale, Context context, Point point) {
        super(squareWidth, scale, context, point);
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
    public FigureType getRotatedFigure() {
        return FigureType.S_SECOND_FIGURE;
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
        path.moveTo(pointOnScreen.x + squareWidth, pointOnScreen.y - scale);
        path.lineTo(pointOnScreen.x + squareWidth, pointOnScreen.y - squareWidth - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 3, pointOnScreen.y - squareWidth - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 3, pointOnScreen.y - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 2, pointOnScreen.y - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 2, pointOnScreen.y + squareWidth - scale);
        path.lineTo(pointOnScreen.x, pointOnScreen.y + squareWidth - scale);
        path.lineTo(pointOnScreen.x, pointOnScreen.y - scale);
        path.close();
        return path;
    }
}
