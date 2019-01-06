package com.example.alina.tetris.figures;


import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;

import com.example.alina.tetris.R;

public class LSecondFigure extends Figure {

    public LSecondFigure(int squareWidth, int scale, Context context) {
        super(squareWidth, scale, context);
    }

    public LSecondFigure(int widthSquare, Context context, Point point) {
        super(widthSquare, context, point);
    }

    @Override
    public void initFigureMask() {
        super.initFigureMask();
        figureMask[0][2] = true;
        figureMask[1][0] = true;
        figureMask[1][1] = true;
        figureMask[1][2] = true;
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
        path.moveTo(point.x, point.y - scale);
        path.lineTo(point.x, point.y + squareWidth - scale);
        path.lineTo(point.x + squareWidth * 3, point.y + squareWidth - scale);
        path.lineTo(point.x + squareWidth * 3, point.y - squareWidth - scale);
        path.lineTo(point.x + squareWidth * 2, point.y - squareWidth - scale);
        path.lineTo(point.x + squareWidth * 2, point.y - scale);
        path.close();
        return path;
    }

    @Override
    public int getColor() {
        return this.context.getResources().getColor(R.color.lFigure);
    }
}
