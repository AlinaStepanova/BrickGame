package com.example.alina.tetris.figures.figure_j;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;

import com.example.alina.tetris.enums.FigureType;
import com.example.alina.tetris.figures.Figure;


public class JFourthFigure extends Figure {

    public JFourthFigure(int squareWidth, int scale, Context context) {
        super(squareWidth, scale, context);
        this.scale += squareWidth;
    }

    public JFourthFigure(int squareWidth, int scale, Context context, Point point) {
        super(squareWidth, scale, context, point);
    }

    public JFourthFigure(int widthSquare, Context context, Point point) {
        super(widthSquare, context, point);
    }

    @Override
    public void initFigureMask() {
        super.initFigureMask();
        figureMask[0][0] = true;
        figureMask[0][1] = true;
        figureMask[0][2] = true;
        figureMask[1][2] = true;
    }

    @Override
    public FigureType getRotatedFigure() {
        return FigureType.J_FIGURE;
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
        path.moveTo(pointOnScreen.x, pointOnScreen.y - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 3, pointOnScreen.y - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 3, pointOnScreen.y + squareWidth * 2 - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 2, pointOnScreen.y + squareWidth * 2 - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 2, pointOnScreen.y + squareWidth - scale);
        path.lineTo(pointOnScreen.x, pointOnScreen.y + squareWidth - scale);
        path.close();
        return path;
    }
}
