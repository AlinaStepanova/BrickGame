package com.example.alina.tetris.ui.settings;

import com.example.alina.tetris.R;
import com.example.alina.tetris.data.SharedPreferencesManager;
import com.example.alina.tetris.enums.FigureSpeed;
import com.example.alina.tetris.utils.Utils;

import static com.example.alina.tetris.Values.DEFAULT_COLOR;

class SettingsPresenter {

    private SharedPreferencesManager sharedPreferencesManager;
    private SettingsView settingsView;

    SettingsPresenter(SettingsView settingsView, SharedPreferencesManager sharedPreferencesManager) {
        this.settingsView = settingsView;
        this.sharedPreferencesManager = sharedPreferencesManager;
    }

    void setValues() {
        settingsView.markChosenColor(DEFAULT_COLOR, Utils.getViewIdByColor(sharedPreferencesManager.getFiguresColor()));
        settingsView.setSpeed(Utils.getSpeedByTime(sharedPreferencesManager.getFiguresSpeed()));
    }

    void setFigureSpeed(int newValue) {
        switch (newValue) {
            case 1:
                sharedPreferencesManager.setFiguresSpeed(FigureSpeed.VERY_SLOW.getFigureSpeed());
                break;
            case 2:
                sharedPreferencesManager.setFiguresSpeed(FigureSpeed.SLOW.getFigureSpeed());
                break;
            case 3:
                sharedPreferencesManager.setFiguresSpeed(FigureSpeed.DEFAULT.getFigureSpeed());
                break;
            case 4:
                sharedPreferencesManager.setFiguresSpeed(FigureSpeed.FAST.getFigureSpeed());
                break;
            case 5:
                sharedPreferencesManager.setFiguresSpeed(FigureSpeed.VERY_FAST.getFigureSpeed());
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
