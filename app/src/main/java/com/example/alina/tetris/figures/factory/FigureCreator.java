package com.example.alina.tetris.figures.factory;

import com.example.alina.tetris.enums.FigureType;
import com.example.alina.tetris.views.PlayingArea;

import java.util.Random;

import static com.example.alina.tetris.values.Values.ENUM_LENGTH;
import static com.example.alina.tetris.values.Values.INITIAL_FIGURE_TYPE_LIST_LENGTH;

public class FigureCreator {

    private Random random;
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

    private FigureType selectFigure(int length) {
        return FigureType.values()[random.nextInt(length)];
    }

    private FigureType selectFigure() {
        FigureType figureType;
        /*if (PlayingArea.FIGURE_TYPE_LIST_SIZE < INITIAL_FIGURE_TYPE_LIST_LENGTH) {
            figureType = selectFigure(ENUM_LENGTH);
        } else {
            figureType = FigureType.values()[random.nextInt(FigureType.values().length)];
        }*/
        figureType = FigureType.values()[random.nextInt(FigureType.values().length)];
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
}
