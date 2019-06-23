package com.example.alina.tetris.figures.figure_j;


import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;

import com.example.alina.tetris.enums.FigureType;
import com.example.alina.tetris.figures.Figure;

public class JThirdFigure extends Figure {

    public JThirdFigure(int squareWidth, int scale, Context context) {
        super(squareWidth, scale, context);
        int SCALE_HEIGHT = 2 * squareWidth;
        this.scale += SCALE_HEIGHT;
    }

    public JThirdFigure(int widthSquare, Context context, Point point) {
        super(widthSquare, context, point);
    }

    public JThirdFigure(int squareWidth, int scale, Context context, Point point) {
        super(squareWidth, scale, context, point);
    }

    @Override
    public void initFigureMask() {
        super.initFigureMask();
        figureMask[0][0] = true;
        figureMask[0][1] = true;
        figureMask[1][0] = true;
        figureMask[2][0] = true;
    }

    @Override
    public FigureType getRotatedFigure() {
        return FigureType.J_FOURTH_FIGURE;
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
        path.lineTo(point.x + squareWidth, point.y + squareWidth * 3 - scale);
        path.lineTo(point.x + squareWidth, point.y + squareWidth - scale);
        path.lineTo(point.x + squareWidth * 2, point.y + squareWidth - scale);
        path.lineTo(point.x + squareWidth * 2, point.y - scale);
        path.close();
        return path;
    }
}
