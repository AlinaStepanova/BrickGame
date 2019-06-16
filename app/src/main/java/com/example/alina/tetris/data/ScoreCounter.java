package com.example.alina.tetris.data;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.alina.tetris.utils.NotificationUtil;

import static android.content.Context.MODE_PRIVATE;
import static com.example.alina.tetris.Values.DEFAULT_VALUE;
import static com.example.alina.tetris.Values.FIRST_VALUE_KEY;
import static com.example.alina.tetris.Values.PREFERENCES_KEY;
import static com.example.alina.tetris.Values.SECOND_VALUE_KEY;
import static com.example.alina.tetris.Values.THIRD_VALUE_KEY;

public class ScoreCounter {

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;
    private final Context context;
    private int firstValue;
    private int secondValue;
    private int thirdValue;

    public ScoreCounter(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(PREFERENCES_KEY, MODE_PRIVATE);
        this.editor = preferences.edit();
        initValues();
    }

    private void initValues() {
        firstValue = preferences.getInt(FIRST_VALUE_KEY, DEFAULT_VALUE);
        secondValue = preferences.getInt(SECOND_VALUE_KEY, DEFAULT_VALUE);
        thirdValue = preferences.getInt(THIRD_VALUE_KEY, DEFAULT_VALUE);
    }

    public void putNewScore(int newScore) {
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
