package com.example.alina.tetris.figures.factory;

import com.example.alina.tetris.figures.Figure;
import com.example.alina.tetris.figures.JFigure;
import com.example.alina.tetris.figures.LFigure;
import com.example.alina.tetris.figures.LongFigure;
import com.example.alina.tetris.figures.SFigure;
import com.example.alina.tetris.figures.SquareFigure;
import com.example.alina.tetris.figures.TFigure;
import com.example.alina.tetris.figures.ZFigure;

public class FigureFactory {

    public static Figure getFigure(FigureType figureType) {
        switch (figureType) {
            case JFIGURE:
                return new JFigure();
            case LFIGURE:
                return new LFigure();
            case LONG_FIGURE:
                return new LongFigure();
            case SQUARE_FIGURE:
                return new SquareFigure();
            case SFIGURE:
                return new SFigure();
            case ZFIGURE:
                return new ZFigure();
            case TFIGURE:
                return new TFigure();
            default:
                return null;
        }
    }

}
