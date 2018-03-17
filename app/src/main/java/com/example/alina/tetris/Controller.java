package com.example.alina.tetris;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.alina.tetris.R;
import com.example.alina.tetris.listeners.OnControllerListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Alina on 18.03.2017.
 */

public class Controller extends LinearLayout {

    private OnControllerListener onControllerListener;

    public Controller(Context context) {
        super(context);
        setLayout();
    }

    public Controller(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayout();
    }

    public Controller(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayout();
    }

    private void setLayout() {
        View view = inflate(getContext(), R.layout.controller, this);
        ButterKnife.bind(this, view);
    }

    public void setOnControllerListener(OnControllerListener onControllerListener) {
        this.onControllerListener = onControllerListener;
    }

    @OnClick(R.id.bLeft)
    void moveLeft() {
        if (onControllerListener != null) {
            onControllerListener.onLeftButtonClick();
        }
    }

    @OnClick (R.id.bRight)
    void moveRight() {
        if (onControllerListener != null) {
            onControllerListener.onRightButtonClick();
        }
    }
}
