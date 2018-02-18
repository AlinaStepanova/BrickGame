package com.example.alina.tetris.figures.factory;

import com.example.alina.tetris.enums.FigureType;
import java.util.Random;

public class FigureCreator {

    private Random random = new Random();

    public FigureType selectFigure() {
        return FigureType.values()[random.nextInt(FigureType.values().length)];
    }

    public FigureType selectFigure(int length) {
        return FigureType.values()[random.nextInt(length)];
    }
}
