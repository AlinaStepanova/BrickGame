package com.example.alina.tetris.figures.figure_l;


import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;

import com.example.alina.tetris.enums.FigureType;
import com.example.alina.tetris.figures.Figure;

public class LThirdFigure extends Figure {

    public LThirdFigure(int squareWidth, int scale, Context context) {
        super(squareWidth, scale, context);
        int SCALE_HEIGHT = 2 * squareWidth;
        this.scale += SCALE_HEIGHT;
    }

    public LThirdFigure(int widthSquare, Context context, Point point) {
        super(widthSquare, context, point);
    }

    public LThirdFigure(int widthSquare, int scale, Context context, Point point) {
        super(widthSquare, scale, context, point);
    }

    @Override
    public void initFigureMask() {
        super.initFigureMask();
        figureMask[0][0] = true;
        figureMask[0][1] = true;
        figureMask[1][1] = true;
        figureMask[2][1] = true;
    }

    @Override
    public FigureType getRotatedFigure() {
        return FigureType.L_SECOND_FIGURE;
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
        path.lineTo(point.x + squareWidth * 2, point.y - scale);
        path.lineTo(point.x + squareWidth * 2, point.y + squareWidth * 3 - scale);
        path.lineTo(point.x + squareWidth, point.y + squareWidth * 3 - scale);
        path.lineTo(point.x + squareWidth, point.y + squareWidth - scale);
        path.lineTo(point.x, point.y + squareWidth - scale);
        path.close();
        return path;
    }
}
