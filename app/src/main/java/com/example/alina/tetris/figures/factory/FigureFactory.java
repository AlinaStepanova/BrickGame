package com.example.alina.tetris.figures.factory;

import com.example.alina.tetris.figures.Figure;
import com.example.alina.tetris.figures.JFigure;
import com.example.alina.tetris.figures.LFigure;
import com.example.alina.tetris.figures.LongFigure;
import com.example.alina.tetris.figures.SquareFigure;
import com.example.alina.tetris.figures.ZFigure;

public class FigureFactory {

    public static Figure getFigure(FigureType figureType) {
        switch (figureType) {
        case JFiGURE:
            return new JFigure();
        case LFIGUER:
            return new LFigure();
        case LONG_FIGURE:
            return new LongFigure();
        case SQUARE_FIGURE:
            return new SquareFigure();
        case ZFIGUER:
            return new ZFigure();
        default:
            return null;
        }
    }

}
