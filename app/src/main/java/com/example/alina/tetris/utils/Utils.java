package com.example.alina.tetris.utils;

import androidx.annotation.NonNull;

import com.example.alina.tetris.R;

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
}
