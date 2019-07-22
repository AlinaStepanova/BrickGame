package com.avs.brick.game.figures.figure_long;


import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;

import com.avs.brick.game.enums.FigureType;
import com.avs.brick.game.figures.Figure;

public class LongSecondFigure extends Figure {

    public LongSecondFigure(int squareWidth, int scale, int squaresCountInRow, Context context) {
        super(squareWidth, scale, squaresCountInRow, context);
    }

    public LongSecondFigure(int widthSquare, Context context, Point point) {
        super(widthSquare, context, point);
    }

    public LongSecondFigure(int widthSquare, int scale, Context context, Point point) {
        super(widthSquare, scale, context, point);
    }

    @Override
    public void initFigureMask() {
        super.initFigureMask();
        figureMask[0][0] = true;
        figureMask[0][1] = true;
        figureMask[0][2] = true;
        figureMask[0][3] = true;
    }

    @Override
    public FigureType getRotatedFigure() {
        return FigureType.LONG_FIGURE;
    }

    @Override
    public int getWidthInSquare() {
        return 4;
    }

    @Override
    public int getHeightInSquare() {
        return 1;
    }

    @Override
    public Path getPath() {
        Path path = new Path();
        path.moveTo(pointOnScreen.x, pointOnScreen.y - scale);
        path.lineTo(pointOnScreen.x, pointOnScreen.y + squareWidth - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 4, pointOnScreen.y + squareWidth - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 4, pointOnScreen.y - scale);
        path.close();
        return path;
    }
}
