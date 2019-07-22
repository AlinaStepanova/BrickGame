package com.avs.brick.game.figures.figure_long;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;

import com.avs.brick.game.enums.FigureType;
import com.avs.brick.game.figures.Figure;

/**
 * Created by Alina on 02.04.2017.
 */

public class LongFigure extends Figure {

    public LongFigure(int squareWidth, int scale, int squaresCountInRow, Context context) {
        super(squareWidth, scale, squaresCountInRow, context);
        int SCALE_HEIGHT = 3 * squareWidth;
        this.scale += SCALE_HEIGHT;
    }

    public LongFigure(int widthSquare, Context context, Point point) {
        super(widthSquare, context, point);
    }

    public LongFigure(int widthSquare, int scale, Context context, Point point) {
        super(widthSquare, scale, context, point);
    }

    @Override
    public void initFigureMask() {
        super.initFigureMask();
        figureMask[0][0] = true;
        figureMask[1][0] = true;
        figureMask[2][0] = true;
        figureMask[3][0] = true;
    }

    @Override
    public FigureType getRotatedFigure() {
        return FigureType.LONG_SECOND_FIGURE;
    }

    @Override
    public int getWidthInSquare() {
        return 1;
    }

    @Override
    public int getHeightInSquare() {
        return 4;
    }

    @Override
    public Path getPath() {
        Path path = new Path();
        path.moveTo(pointOnScreen.x, pointOnScreen.y - scale);
        path.lineTo(pointOnScreen.x + squareWidth, pointOnScreen.y - scale);
        path.lineTo(pointOnScreen.x + squareWidth, pointOnScreen.y + squareWidth * 4 - scale);
        path.lineTo(pointOnScreen.x, pointOnScreen.y + squareWidth * 4 - scale);
        path.close();
        return path;
    }
}
