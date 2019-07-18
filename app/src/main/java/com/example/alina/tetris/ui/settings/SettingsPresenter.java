package com.example.alina.tetris.ui.settings;

import com.example.alina.tetris.R;
import com.example.alina.tetris.data.SharedPreferencesManager;
import com.example.alina.tetris.enums.FigureSpeed;
import com.example.alina.tetris.utils.Utils;

import static com.example.alina.tetris.Values.DEFAULT_COLOR;
import static com.example.alina.tetris.enums.FigureSpeed.*;

class SettingsPresenter {

    private SharedPreferencesManager sharedPreferencesManager;
    private SettingsView settingsView;

    SettingsPresenter(SettingsView settingsView, SharedPreferencesManager sharedPreferencesManager) {
        this.settingsView = settingsView;
        this.sharedPreferencesManager = sharedPreferencesManager;
    }

    void setValues() {
        settingsView.markChosenColor(DEFAULT_COLOR, Utils.getViewIdByColor(sharedPreferencesManager.getFiguresColor()));
        FigureSpeed figureSpeed = Utils.getFiguresSpeedByMillis(sharedPreferencesManager.getFiguresSpeed());
        settingsView.setSpeed(figureSpeed.getSpeed());
        settingsView.setSpeedTitle(figureSpeed.getFigureSpeedTitle());
    }

    void setFigureSpeed(int newValue) {
        switch (newValue) {
            case 1:
                sharedPreferencesManager.setFiguresSpeed(VERY_SLOW.getFigureSpeedInMillis());
                settingsView.setSpeedTitle(VERY_SLOW.getFigureSpeedTitle());
                break;
            case 2:
                sharedPreferencesManager.setFiguresSpeed(SLOW.getFigureSpeedInMillis());
                settingsView.setSpeedTitle(SLOW.getFigureSpeedTitle());
                break;
            case 3:
                sharedPreferencesManager.setFiguresSpeed(DEFAULT.getFigureSpeedInMillis());
                settingsView.setSpeedTitle(DEFAULT.getFigureSpeedTitle());
                break;
            case 4:
                sharedPreferencesManager.setFiguresSpeed(FAST.getFigureSpeedInMillis());
                settingsView.setSpeedTitle(FAST.getFigureSpeedTitle());
                break;
            case 5:
                sharedPreferencesManager.setFiguresSpeed(VERY_FAST.getFigureSpeedInMillis());
                settingsView.setSpeedTitle(VERY_FAST.getFigureSpeedTitle());
                break;
            default:
                break;
        }
    }

    void getEvent(int id) {
        switch (id) {
            case R.id.vLFigureColor:
                manageColorPicking(R.color.lFigure, id);
                break;
            case R.id.vSquareFigureColor:
                manageColorPicking(R.color.squareFigure, id);
                break;
            case R.id.vLongFigureColor:
                manageColorPicking(R.color.longFigure, id);
                break;
            case R.id.vZFigureColor:
                manageColorPicking(R.color.zFigure, id);
                break;
            case R.id.vTFigureColor:
                manageColorPicking(R.color.tFigure, id);
                break;
            case R.id.vJFigureColor:
                manageColorPicking(R.color.jFigure, id);
                break;
            default:
                break;
        }
    }

    private void manageColorPicking(int newColor, int newItemId) {
        int oldColor = sharedPreferencesManager.getFiguresColor();
        sharedPreferencesManager.setFiguresColor(newColor);
        settingsView.markChosenColor(oldColor, newItemId);
    }
}
