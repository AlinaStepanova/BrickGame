package com.avs.brick.game.figures.figure_z;


import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;

import com.avs.brick.game.enums.FigureType;
import com.avs.brick.game.figures.Figure;

public class ZSecondFigure extends Figure {

    public ZSecondFigure(int squareWidth, int scale, int squaresCountInRow, Context context) {
        super(squareWidth, scale, squaresCountInRow, context);
        int SCALE_HEIGHT = 2 * squareWidth;
        this.scale += SCALE_HEIGHT;
    }

    public ZSecondFigure(int widthSquare, Context context, Point point) {
        super(widthSquare, context, point);
    }

    public ZSecondFigure(int squareWidth, int scale, Context context, Point point) {
        super(squareWidth, scale, context, point);
    }

    @Override
    public void initFigureMask() {
        super.initFigureMask();
        figureMask[0][0] = true;
        figureMask[1][0] = true;
        figureMask[1][1] = true;
        figureMask[2][1] = true;
    }

    @Override
    public FigureType getRotatedFigure() {
        return FigureType.S_FIGURE;
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
        path.moveTo(pointOnScreen.x, pointOnScreen.y - scale);
        path.lineTo(pointOnScreen.x, pointOnScreen.y + squareWidth * 2 - scale);
        path.lineTo(pointOnScreen.x + squareWidth, pointOnScreen.y + squareWidth * 2 - scale);
        path.lineTo(pointOnScreen.x + squareWidth, pointOnScreen.y + squareWidth * 3 - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 2, pointOnScreen.y + squareWidth * 3 - scale);
        path.lineTo(pointOnScreen.x + squareWidth * 2, pointOnScreen.y + squareWidth - scale);
        path.lineTo(pointOnScreen.x + squareWidth, pointOnScreen.y + squareWidth - scale);
        path.lineTo(pointOnScreen.x + squareWidth, pointOnScreen.y - scale);
        path.close();
        return path;
    }
}
