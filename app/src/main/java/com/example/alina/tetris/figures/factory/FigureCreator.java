package com.example.alina.tetris.figures.factory;

import com.example.alina.tetris.enums.FigureType;
import java.util.Random;

public class FigureCreator {

    public FigureType selectFigure() {
        Random random = new Random();
        return FigureType.values()[random.nextInt(FigureType.values().length)];
    }

    public FigureType selectFigure(int length) {
        Random random = new Random();
        return FigureType.values()[random.nextInt(length)];
    }
}
