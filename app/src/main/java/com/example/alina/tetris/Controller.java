package com.example.alina.tetris;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.alina.tetris.listeners.OnControllerListener;

/**
 * Created by Alina on 18.03.2017.
 */

public class Controller extends LinearLayout {

    private OnControllerListener onControllerListener;

    public void setOnControllerListener(OnControllerListener onControllerListener) {
        this.onControllerListener = onControllerListener;
    }

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

    public void setLayout() {
        View inflate = inflate(getContext(), R.layout.controller, this);
        Button left = (Button) inflate.findViewById(R.id.bLeft);
        Button right = (Button) inflate.findViewById(R.id.bRight);
        left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onControllerListener != null) {
                    onControllerListener.onLeftButtonClick();
                }
            }
        });
        right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onControllerListener != null) {
                    onControllerListener.onRightButtonClick();
                }
            }
        });
    }


}
