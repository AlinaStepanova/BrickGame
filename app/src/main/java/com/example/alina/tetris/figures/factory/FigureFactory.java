package com.example.alina.tetris.figures.factory;

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

    public static Figure getFigure(FigureType figureType, int widthSquare, Point point) {
        switch (figureType) {
            case J_FIGURE:
                return new JFigure(widthSquare, point);
            case L_FIGURE:
                return new LFigure(widthSquare, point);
            case LONG_FIGURE:
                return new LongFigure(widthSquare, point);
            case SQUARE_FIGURE:
                return new SquareFigure(widthSquare, point);
            case S_FIGURE:
                return new SFigure(widthSquare, point);
            case Z_FIGURE:
                return new ZFigure(widthSquare, point);
            case T_FIGURE:
                return new TFigure(widthSquare, point);
            default:
                return null;
        }
    }

}
