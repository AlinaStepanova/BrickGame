package com.example.alina.tetris.figures.factory;

import android.content.Context;
import android.graphics.Point;

import com.example.alina.tetris.figures.Figure;
import com.example.alina.tetris.figures.JFigure;
import com.example.alina.tetris.figures.LFigure;
import com.example.alina.tetris.figures.LongFigure;
import com.example.alina.tetris.figures.SFigure;
import com.example.alina.tetris.figures.SquareFigure;
import com.example.alina.tetris.figures.TFigure;
import com.example.alina.tetris.figures.ZFigure;

public class FigureFactory {

    public static Figure getFigure(FigureType figureType, int widthSquare, int scale, Context context) {
        switch (figureType) {
            case J_FIGURE:
                return new JFigure(widthSquare, scale, context);
            case L_FIGURE:
                return new LFigure(widthSquare, scale, context);
            case LONG_FIGURE:
                return new LongFigure(widthSquare, scale, context);
            case SQUARE_FIGURE:
                return new SquareFigure(widthSquare, scale, context);
            case S_FIGURE:
                return new SFigure(widthSquare, scale, context);
            case Z_FIGURE:
                return new ZFigure(widthSquare, scale, context);
            case T_FIGURE:
                return new TFigure(widthSquare, scale, context);
            default:
                return null;
        }
    }

}
