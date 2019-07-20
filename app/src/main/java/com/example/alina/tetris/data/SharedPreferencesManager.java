package com.example.alina.tetris.data;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.alina.tetris.utils.NotificationUtil;

import static android.content.Context.MODE_PRIVATE;
import static com.example.alina.tetris.Values.DEFAULT_COLOR;
import static com.example.alina.tetris.Values.DEFAULT_SPEED;
import static com.example.alina.tetris.Values.SQUARES_COUNT_IN_ROW;
import static com.example.alina.tetris.Values.DEFAULT_VALUE;
import static com.example.alina.tetris.Values.ENABLED_HINTS;
import static com.example.alina.tetris.Values.ENABLE_HINTS_KEY;
import static com.example.alina.tetris.Values.FIGURE_COLOR_KEY;
import static com.example.alina.tetris.Values.FIGURE_SPEED_KEY;
import static com.example.alina.tetris.Values.FIRST_VALUE_KEY;
import static com.example.alina.tetris.Values.PREFERENCES_KEY;
import static com.example.alina.tetris.Values.SECOND_VALUE_KEY;
import static com.example.alina.tetris.Values.SQUARES_COUNT_IN_ROW_KEY;
import static com.example.alina.tetris.Values.THIRD_VALUE_KEY;

public class SharedPreferencesManager {

    private final SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final Context context;

    public SharedPreferencesManager(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(PREFERENCES_KEY, MODE_PRIVATE);
    }

    public void putNewScore(int newScore) {
        this.editor = preferences.edit();
        int firstValue = preferences.getInt(FIRST_VALUE_KEY, DEFAULT_VALUE);
        int secondValue = preferences.getInt(SECOND_VALUE_KEY, DEFAULT_VALUE);
        int thirdValue = preferences.getInt(THIRD_VALUE_KEY, DEFAULT_VALUE);
        if (newScore > firstValue && newScore != DEFAULT_VALUE) {
            editor.putInt(FIRST_VALUE_KEY, newScore);
            editor.putInt(SECOND_VALUE_KEY, firstValue);
            editor.putInt(THIRD_VALUE_KEY, secondValue);
            new NotificationUtil(context, newScore).createNotification();
        } else if (newScore > secondValue && newScore != DEFAULT_VALUE) {
            editor.putInt(SECOND_VALUE_KEY, newScore);
            editor.putInt(THIRD_VALUE_KEY, secondValue);
            new NotificationUtil(context, newScore).createNotification();
        } else if (newScore > thirdValue && newScore != DEFAULT_VALUE) {
            editor.putInt(THIRD_VALUE_KEY, newScore);
            new NotificationUtil(context, newScore).createNotification();
        }
        editor.apply();
        editor.commit();
    }

    public void setFiguresColor(int color) {
        this.editor = preferences.edit();
        editor.putInt(FIGURE_COLOR_KEY, color);
        editor.apply();
        editor.commit();
    }

    public int getFiguresColor() {
        return preferences.getInt(FIGURE_COLOR_KEY, DEFAULT_COLOR);
    }

    public void setSquaresCountInRow(int count) {
        this.editor = preferences.edit();
        editor.putInt(SQUARES_COUNT_IN_ROW_KEY, count);
        editor.apply();
        editor.commit();
    }

    public int getSquaresCountInRow() {
        return preferences.getInt(SQUARES_COUNT_IN_ROW_KEY, SQUARES_COUNT_IN_ROW);
    }


    public void setFiguresSpeed(long speed) {
        this.editor = preferences.edit();
        editor.putLong(FIGURE_SPEED_KEY, speed);
        editor.apply();
        editor.commit();
    }

    public boolean isHintsEnabled() {
        return preferences.getBoolean(ENABLE_HINTS_KEY, ENABLED_HINTS);
    }

    public void setHintsEnabled(boolean isEnabled) {
        this.editor = preferences.edit();
        editor.putBoolean(ENABLE_HINTS_KEY, isEnabled);
        editor.apply();
        editor.commit();
    }

    public long getFiguresSpeed() {
        return preferences.getLong(FIGURE_SPEED_KEY, DEFAULT_SPEED);
    }

    public String getFirstValue() {
        return String.valueOf(preferences.getInt(FIRST_VALUE_KEY, DEFAULT_VALUE));
    }

    public String getSecondValue() {
        return String.valueOf(preferences.getInt(SECOND_VALUE_KEY, DEFAULT_VALUE));
    }

    public String getThirdValue() {
        return String.valueOf(preferences.getInt(THIRD_VALUE_KEY, DEFAULT_VALUE));
    }
}
