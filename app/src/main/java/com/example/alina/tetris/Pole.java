package com.example.alina.tetris;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.alina.tetris.figures.Figure;
import com.example.alina.tetris.figures.JFigure;
import com.example.alina.tetris.figures.factory.FigureFactory;
import com.example.alina.tetris.figures.factory.FigureType;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Alina on 18.03.2017.
 */

public class Pole extends FrameLayout {

    private int widthOfSquareSide;

    private int squareCount;

    private int screenHeight;

    private int screenWidth;

    private final int SQUARE_COUNT = 10;

    private final List<FigureType> figureTypeList = new ArrayList<>();

    private Paint paint = new Paint();

    private Point point;

    private float width = 1f;

    private Figure figure;

    //hashmap(pole,figure)

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
        widthOfSquareSide = MeasureSpec.getSize(widthMeasureSpec) / SQUARE_COUNT;
        squareCount = MeasureSpec.getSize(heightMeasureSpec) / widthOfSquareSide;
        screenHeight = MeasureSpec.getSize(heightMeasureSpec);
        screenWidth = MeasureSpec.getSize(widthMeasureSpec);
        point  = new Point(110, 218);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(width);
        for (int i = 1; i <= SQUARE_COUNT; i++) {
            canvas.drawLine(i * widthOfSquareSide, 0, i * widthOfSquareSide, screenHeight, paint);
        }
        for (int i = 1; i <= squareCount; i++) {
            canvas.drawLine(0, i * widthOfSquareSide, screenWidth, i * widthOfSquareSide, paint);
        }

        figure = FigureFactory.getFigure(figureTypeList.get(0), widthOfSquareSide, point);
        figure.squareWidth = widthOfSquareSide;
        Path path = figure.getPath();
        paint.setColor(figure.getColor());
        canvas.drawPath(path, paint);
        startMoveDown();
    }

    public void addFigure(FigureType figureType) {
        figureTypeList.add(figureType);
        invalidate();
    }

    public void moveLeft() {
        figure.moveLeft();
        invalidate();
    }

    public void moveRight() {
        figure.moveRight();
        invalidate();
    }

    private void startMoveDown() {
        Timer timer = new Timer();
        /*timer.schedule(new TimerTask() {
            @Override
            public void run() {
                figure.moveDown();
                invalidate();
            }
        }, 0, 5000);*/

        new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                figure.moveDown();
                invalidate();
            }

        }.start();
    }
}
