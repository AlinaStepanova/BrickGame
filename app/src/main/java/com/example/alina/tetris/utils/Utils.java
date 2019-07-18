package com.example.alina.tetris.utils;

import com.example.alina.tetris.R;
import com.example.alina.tetris.enums.FigureSpeed;

import androidx.annotation.NonNull;

import static com.example.alina.tetris.enums.FigureSpeed.*;

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

    public static FigureSpeed getFiguresSpeedByMillis(long speedMillis) {
        FigureSpeed speed;
        if (speedMillis == VERY_FAST.getFigureSpeedInMillis()) {
            speed = VERY_FAST;
        } else if (speedMillis == FAST.getFigureSpeedInMillis()) {
            speed = FAST;
        } else if (speedMillis == DEFAULT.getFigureSpeedInMillis()) {
            speed = DEFAULT;
        } else if (speedMillis == SLOW.getFigureSpeedInMillis()) {
            speed = SLOW;
        } else {
            speed = VERY_SLOW;
        }
        return speed;
    }
}
