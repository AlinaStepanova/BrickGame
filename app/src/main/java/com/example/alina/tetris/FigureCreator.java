package com.example.alina.tetris;
import android.os.CountDownTimer;

import com.example.alina.tetris.figures.factory.FigureType;
import java.util.Random;

public class FigureCreator {

    public FigureCreator() {
    }

    public FigureType selectFigure() {
        Random random = new Random();
        return FigureType.values()[random.nextInt(FigureType.values().length)];
    }
}
