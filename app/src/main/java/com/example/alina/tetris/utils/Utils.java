package com.example.alina.tetris.utils;

import androidx.annotation.NonNull;

import com.example.alina.tetris.R;
import com.example.alina.tetris.enums.FigureSpeed;

public class Utils {

    public static int getViewIdByColor(@NonNull int color) {
        int id = 0;
        switch (color) {
            case R.color.lFigure:
                id = R.id.vLFigureColor;
                break;
            case R.color.squareFigure:
                id = R.id.vSquareFigureColor;
                break;
            case R.color.longFigure:
                id = R.id.vLongFigureColor;
                break;
            case R.color.zFigure:
                id = R.id.vZFigureColor;
                break;
            case R.color.tFigure:
                id = R.id.vTFigureColor;
                break;
            case R.color.jFigure:
                id = R.id.vJFigureColor;
                break;
            default:
                break;
        }
        return id;
    }

    public static int getSpeedByTime(int speed) {
        int value = 3;
        if (speed == FigureSpeed.VERY_SLOW.getFigureSpeed()) {
            value = 1;
        } else if (speed == FigureSpeed.SLOW.getFigureSpeed()) {
            value = 2;
        } else if (speed == FigureSpeed.DEFAULT.getFigureSpeed()) {
            value = 3;
        } else if (speed == FigureSpeed.FAST.getFigureSpeed()) {
            value = 4;
        } else if (speed == FigureSpeed.VERY_FAST.getFigureSpeed()) {
            value = 5;
        }
        return value;
    }
}
