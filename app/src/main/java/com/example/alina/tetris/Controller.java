package com.example.alina.tetris;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Alina on 18.03.2017.
 */

public class Controller extends LinearLayout {

    public Button left;

    public Button right;

    public Controller(Context context) {
        super(context);
    }

    public Controller(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Controller(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Controller(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
