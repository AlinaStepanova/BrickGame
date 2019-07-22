package com.avs.brick.game.ui.settings;

import com.avs.brick.game.R;
import com.avs.brick.game.data.SharedPreferencesManager;
import com.avs.brick.game.enums.FigureSpeed;
import com.avs.brick.game.utils.Utils;

import static com.avs.brick.game.Values.DEFAULT_COLOR;
import static com.avs.brick.game.enums.FigureSpeed.DEFAULT;
import static com.avs.brick.game.enums.FigureSpeed.FAST;
import static com.avs.brick.game.enums.FigureSpeed.SLOW;
import static com.avs.brick.game.enums.FigureSpeed.VERY_FAST;
import static com.avs.brick.game.enums.FigureSpeed.VERY_SLOW;

class SettingsPresenter {

    private SharedPreferencesManager sharedPreferencesManager;
    private SettingsView settingsView;

    SettingsPresenter(SettingsView settingsView, SharedPreferencesManager sharedPreferencesManager) {
        this.settingsView = settingsView;
        this.sharedPreferencesManager = sharedPreferencesManager;
    }

    void setValues() {
        FigureSpeed figureSpeed = Utils.getFiguresSpeedByMillis(sharedPreferencesManager.getFiguresSpeed());
        if (settingsView != null) {
            settingsView.markChosenColor(DEFAULT_COLOR, Utils.getViewIdByColor(sharedPreferencesManager.getFiguresColor()));
            settingsView.setSquaresCountInRow(sharedPreferencesManager.getSquaresCountInRow());
            settingsView.setSpeedTitle(FigureSpeed.DEFAULT.getSpeedItemId(), figureSpeed.getSpeedItemId());
            settingsView.setVerticalHintsChecked(sharedPreferencesManager.isHintsEnabled());
        }
    }

    void setSquareCountInRow(int newValue) {
        sharedPreferencesManager.setSquaresCountInRow(newValue);
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
            case R.id.sEnableHints:
                boolean isEnabled = sharedPreferencesManager.isHintsEnabled();
                sharedPreferencesManager.setHintsEnabled(!isEnabled);
                break;
            case R.id.tvVeryFast:
                manageSpeedPicking(VERY_FAST.getFigureSpeedInMillis(), id);
                break;
            case R.id.tvFast:
                manageSpeedPicking(FAST.getFigureSpeedInMillis(), id);
                break;
            case R.id.tvDefault:
                manageSpeedPicking(DEFAULT.getFigureSpeedInMillis(), id);
                break;
            case R.id.tvSlow:
                manageSpeedPicking(SLOW.getFigureSpeedInMillis(), id);
                break;
            case R.id.tvVerySlow:
                manageSpeedPicking(VERY_SLOW.getFigureSpeedInMillis(), id);
                break;
            default:
                break;
        }
    }

    private void manageSpeedPicking(long newSpeed, int newItemId) {
        int oldItemId = Utils.getFiguresSpeedByMillis(sharedPreferencesManager
                        .getFiguresSpeed()).getSpeedItemId();
        sharedPreferencesManager.setFiguresSpeed(newSpeed);
        settingsView.setSpeedTitle(oldItemId, newItemId);
    }

    private void manageColorPicking(int newColor, int newItemId) {
        int oldColor = sharedPreferencesManager.getFiguresColor();
        sharedPreferencesManager.setFiguresColor(newColor);
        settingsView.markChosenColor(oldColor, newItemId);
    }
}
