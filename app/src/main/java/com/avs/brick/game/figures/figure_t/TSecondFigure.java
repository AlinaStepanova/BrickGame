package com.avs.brick.game.figures.figure_t;


import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;

import com.avs.brick.game.enums.FigureType;
import com.avs.brick.game.figures.Figure;

public class TSecondFigure extends Figure {

    public TSecondFigure(int squareWidth, int scale, int squaresCountInRow, Context context) {
        super(squareWidth, scale, squaresCountInRow, context);
        this.scale += squareWidth;
    }

    public TSecondFigure(int widthSquare, Context context, Point point) {
        super(widthSquare, context, point);
    }

    public TSecondFigure(int squareWidth, int scale, Context context, Point point) {
        super(squareWidth, scale, context, point);
    }

    @Override
    public void initFigureMask() {
        super.initFigureMask();
        figureMask[0][0] = true;
        figureMask[0][1] = true;
        figureMask[0][2] = true;
        figureMask[1][1] = true;
    }

    @Override
    public FigureType getRotatedFigure() {
        return FigureType.T_FOURTH_FIGURE;
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
        path.lineTo(pointOnScreen.x + squareWidth * 3, pointOnScreen.y + squareWidth - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 2, pointOnScreen.y + squareWidth - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 2, pointOnScreen.y + squareWidth * 2 - scale);
        path.lineTo(pointOnScreen.x + squareWidth, pointOnScreen.y + squareWidth * 2 - scale);
        path.lineTo(pointOnScreen.x + squareWidth, pointOnScreen.y + squareWidth - scale);
        path.lineTo(pointOnScreen.x, pointOnScreen.y + squareWidth - scale);
        path.close();
        return path;
    }
}
