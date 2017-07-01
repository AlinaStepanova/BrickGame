package com.example.alina.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.alina.tetris.figures.Figure;

/**
 * Created by Alina on 18.03.2017.
 */

public class Pole extends FrameLayout {

    private int widthOfSquareSide;

    private int squareCount;

    private int screenHeight;

    private int screenWidth;

    private final int SQARE_COUNT = 10;

    private Paint paint = new Paint();

    public Pole(@NonNull Context context) {
        super(context);
        init();
    }

    public Pole(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Pole(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthOfSquareSide = MeasureSpec.getSize(widthMeasureSpec) / SQARE_COUNT;
        squareCount = MeasureSpec.getSize(heightMeasureSpec) / widthOfSquareSide;
        screenHeight = MeasureSpec.getSize(heightMeasureSpec);
        screenWidth = MeasureSpec.getSize(widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1f);
        for (int i = 1; i <= 10; i++) {
            canvas.drawLine(i * widthOfSquareSide, 0, i * widthOfSquareSide, screenHeight, paint);
        }
        for (int i = 1; i <= squareCount; i++) {
            canvas.drawLine(0, i * widthOfSquareSide, screenWidth, i * widthOfSquareSide, paint);
        }
    }

    @Override
    public void addView(View child) {
        if (!(child instanceof Figure)) {
            throw new ClassCastException("Object was not of type Figure!");
        }
        child.setLayoutParams(new LayoutParams(2 * widthOfSquareSide, 3 * widthOfSquareSide));
        child.setX(10);
        child.setY(10);
        child.setBackgroundColor(Color.CYAN);
        super.addView(child);
    }
}
