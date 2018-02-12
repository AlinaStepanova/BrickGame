package com.example.alina.tetris.figures.factory;

import android.content.Context;

import com.example.alina.tetris.enums.FigureType;
import com.example.alina.tetris.figures.Figure;
import com.example.alina.tetris.figures.JFigure;
import com.example.alina.tetris.figures.JSecondFigure;
import com.example.alina.tetris.figures.JThirdFigure;
import com.example.alina.tetris.figures.LSecondFigure;
import com.example.alina.tetris.figures.LFigure;
import com.example.alina.tetris.figures.LThirdFigure;
import com.example.alina.tetris.figures.LongFigure;
import com.example.alina.tetris.figures.LongSecondFigure;
import com.example.alina.tetris.figures.SFigure;
import com.example.alina.tetris.figures.SSecondFigure;
import com.example.alina.tetris.figures.SquareFigure;
import com.example.alina.tetris.figures.TFigure;
import com.example.alina.tetris.figures.TSecondFigure;
import com.example.alina.tetris.figures.ZFigure;
import com.example.alina.tetris.figures.ZSecondFigure;

public class FigureFactory {

    public static Figure getFigure(FigureType figureType, int widthSquare, int scale, Context context) {
        switch (figureType) {
            case J_FIGURE:
                return new JFigure(widthSquare, scale, context);
            case L_FIGURE:
                return new LFigure(widthSquare, scale, context);
            case LONG_FIGURE:
                return new LongFigure(widthSquare, scale, context);
            case S_FIGURE:
                return new SFigure(widthSquare, scale, context);
            case T_FIGURE:
                return new TFigure(widthSquare, scale, context);
            case LONG_SECOND_FIGURE:
                return new LongSecondFigure(widthSquare, scale, context);
            case L_SECOND_FIGURE:
                return new LSecondFigure(widthSquare, scale, context);
            case Z_FIGURE:
                return new ZFigure(widthSquare, scale, context);
            case J_SECOND_FIGURE:
                return new JSecondFigure(widthSquare, scale, context);
            case J_THIRD_FIGURE:
                return new JThirdFigure(widthSquare, scale, context);
            case L_THIRD_FIGURE:
                return new LThirdFigure(widthSquare, scale, context);
            case SQUARE_FIGURE:
                return new SquareFigure(widthSquare, scale, context);
            case S_SECOND_FIGURE:
                return new SSecondFigure(widthSquare, scale, context);
            case Z_SECOND_FIGURE:
                return new ZSecondFigure(widthSquare, scale, context);
            case T_SECOND_FIGURE:
                return new TSecondFigure(widthSquare, scale, context);
            default:
                return null;
        }
    }

}
