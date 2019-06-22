package com.example.alina.tetris.figures.factory;

import android.graphics.Path;

import com.example.alina.tetris.enums.FigureType;
import com.example.alina.tetris.views.PlayingArea;

import java.util.Random;

import static com.example.alina.tetris.Values.ENUM_LENGTH;
import static com.example.alina.tetris.Values.EXTRA_ROWS;
import static com.example.alina.tetris.Values.INITIAL_FIGURE_TYPE_LIST_LENGTH;

public class FigureCreator {

    private final Random random;
    private FigureType currentFigureType;
    private FigureType nextFigureType;

    public FigureCreator() {
        random = new Random();
        currentFigureType = null;
        nextFigureType = selectFigure();
    }

    private void initFigures() {
        currentFigureType = nextFigureType;
        nextFigureType = selectFigure();
    }

    private FigureType selectFigure() {
        FigureType figureType;
        if (PlayingArea.FIGURE_TYPE_LIST_SIZE < INITIAL_FIGURE_TYPE_LIST_LENGTH) {
            figureType = FigureType.values()[random.nextInt(ENUM_LENGTH)];
        } else {
            figureType = FigureType.values()[random.nextInt(FigureType.values().length)];
        }
        return figureType;
    }

    public FigureType getCurrentFigureType() {
        if (currentFigureType == null) {
            return selectFigure();
        } else {
            return currentFigureType;
        }
    }

    public FigureType getNextFigureType() {
        return nextFigureType;
    }

    public FigureType createNextFigure() {
        initFigures();
        return getNextFigureType();
    }

    public static Path createSmallSquareFigure(int i, int j, int squareWidth, int scale) {
        Path path = new Path();
        int delta = j * squareWidth - (EXTRA_ROWS - 2) * squareWidth - scale;
        path.moveTo(i * squareWidth, delta);
        path.lineTo(i * squareWidth, delta - squareWidth);
        path.lineTo(i * squareWidth + squareWidth, delta - squareWidth);
        path.lineTo(i * squareWidth + squareWidth, delta);
        path.close();
        return path;
    }
}
