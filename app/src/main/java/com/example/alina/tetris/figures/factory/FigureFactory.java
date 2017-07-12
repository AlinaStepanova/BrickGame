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

    
    //add point as pram for the following method
    public static Figure getFigure(FigureType figureType, int widthSquare) {
        switch (figureType) {
            case JFIGURE:
                return new JFigure(widthSquare, new Point(220, 0));
            case LFIGURE:
                return new LFigure(widthSquare, new Point(0, 0));
            case LONG_FIGURE:
                return new LongFigure(widthSquare, new Point(340, 0));
            case SQUARE_FIGURE:
                return new SquareFigure(widthSquare, new Point(0, 200));
            case SFIGURE:
                return new SFigure(widthSquare, new Point(200, 250));
            case ZFIGURE:
                return new ZFigure(widthSquare, new Point(120, 440));
            case TFIGURE:
                return new TFigure(widthSquare, new Point(220, 480));
            default:
                return null;
        }
    }

}
