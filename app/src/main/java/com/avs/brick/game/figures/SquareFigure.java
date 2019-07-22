package com.avs.brick.game.figures;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;

import com.avs.brick.game.enums.FigureType;

/**
 * Created by Alina on 02.04.2017.
 */

public class SquareFigure extends Figure {

    public SquareFigure(int squareWidth, int scale, int squaresCountInRow, Context context) {
        super(squareWidth, scale, squaresCountInRow, context);
        this.scale += squareWidth;
    }

    public SquareFigure(int widthSquare, Context context, Point point) {
        super(widthSquare, context, point);
    }

    public SquareFigure(int squareWidth, int scale, Context context, Point point) {
        super(squareWidth, scale, context, point);
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
    public FigureType getRotatedFigure() {
        return null;
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
        path.moveTo(pointOnScreen.x, pointOnScreen.y - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 2, pointOnScreen.y - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 2, pointOnScreen.y + squareWidth * 2 - scale);
        path.lineTo(pointOnScreen.x, pointOnScreen.y + squareWidth * 2 - scale);
        path.close();
        return path;
    }
}
