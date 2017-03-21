package com.example.alina.tetris;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Alina on 18.03.2017.
 */

public class Pole extends FrameLayout {
    public Pole(@NonNull Context context) {
        super(context);
    }

    public Pole(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Pole(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Pole(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
