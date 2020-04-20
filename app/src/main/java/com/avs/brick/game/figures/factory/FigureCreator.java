package com.avs.brick.game.figures.factory;

import com.avs.brick.game.enums.FigureType;

import java.util.Random;

import static com.avs.brick.game.enums.FigureType.*;

public class FigureCreator {

    private final Random random;
    private FigureType currentFigureType;
    private FigureType nextFigureType;
    private MainFigureType mainFigureType;

    public FigureCreator() {
        random = new Random();
        currentFigureType = null;
        nextFigureType = getNewFigure();
    }

    private void initFigures() {
        currentFigureType = nextFigureType;
        nextFigureType = getNewFigure();
    }

    public FigureType getCurrentFigureType() {
        if (currentFigureType == null) {
            return getNewFigure();
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

    private FigureType getNewFigure() {
        MainFigureType nextType = mainFigureType;
        FigureType nextFigure = nextFigureType;
        while(nextType == mainFigureType) {
            mainFigureType = MainFigureType.values()[random.nextInt(MainFigureType.values().length)];
            nextFigure = mainFigureType.types[random.nextInt(mainFigureType.types.length)];
        }
        return nextFigure;
    }

    private enum MainFigureType {

        LONG_TYPE(LONG_FIGURE, LONG_SECOND_FIGURE),
        SQUARE_TYPE(SQUARE_FIGURE),
        L_TYPE(L_FIGURE, L_SECOND_FIGURE, L_THIRD_FIGURE, L_FOURTH_FIGURE),
        T_TYPE(T_FIGURE, T_SECOND_FIGURE, T_THIRD_FIGURE, T_FOURTH_FIGURE),
        J_TYPE(J_FIGURE, J_SECOND_FIGURE, J_THIRD_FIGURE, J_FOURTH_FIGURE),
        S_TYPE(S_FIGURE, S_SECOND_FIGURE),
        Z_TYPE(Z_FIGURE, Z_SECOND_FIGURE);

        final FigureType[] types;

        MainFigureType(FigureType...types) {
            this.types = types;
        }
    }
}
