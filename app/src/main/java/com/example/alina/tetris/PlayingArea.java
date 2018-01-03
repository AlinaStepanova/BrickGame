package com.example.alina.tetris;

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
import android.view.View;

import com.example.alina.tetris.figures.Figure;
import com.example.alina.tetris.figures.factory.FigureFactory;
import com.example.alina.tetris.figures.factory.FigureType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alina on 18.03.2017.
 */

public class PlayingArea extends View {

    private int widthOfSquareSide;

    private int squareCount;

    private int screenHeight;

    private int screenWidth;

    private final float LINE_WIDTH = 1f;

    private final int SQUARE_COUNT_VERTICAL = 10;

    private final List<FigureType> figureTypeList = new ArrayList<>();

    private Point point;

    private Figure figure;

    private Paint paint;

    private NetManager netManager;

    public PlayingArea(@NonNull Context context) {
        super(context);
        init();
    }

    public PlayingArea(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlayingArea(@NonNull Context context, @Nullable AttributeSet attrs,
                       @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        point = new Point(54, 94);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(LINE_WIDTH);
    }

    private void drawHorizontalLines(Canvas canvas) {
        for (int i = 1; i <= squareCount; i++) {
            canvas.drawLine(0, screenHeight - widthOfSquareSide * i, screenWidth,
                    screenHeight - widthOfSquareSide * i, paint);
        }
    }

    private void drawVerticalLines(Canvas canvas) {
        for (int i = 1; i <= SQUARE_COUNT_VERTICAL; i++) {
            canvas.drawLine(i * widthOfSquareSide, 0, i * widthOfSquareSide,
                    screenHeight, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthOfSquareSide = MeasureSpec.getSize(widthMeasureSpec) / SQUARE_COUNT_VERTICAL;
        squareCount = MeasureSpec.getSize(heightMeasureSpec) / widthOfSquareSide;
        screenHeight = MeasureSpec.getSize(heightMeasureSpec);
        screenWidth = MeasureSpec.getSize(widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawVerticalLines(canvas);
        drawHorizontalLines(canvas);
        if (figure != null) {
            Path path = figure.getPath();
            paint.setColor(figure.getColor());
            canvas.drawPath(path, paint);
            startMoveDown();
        }
    }

    public void addFigure(FigureType figureType) {
        figureTypeList.add(figureType);
        figure = FigureFactory.getFigure(figureTypeList.get(0), widthOfSquareSide, point);
        netManager = new NetManager(figure);
        netManager.initNet(squareCount,  SQUARE_COUNT_VERTICAL);
        figure.squareWidth = widthOfSquareSide;
        figure.initFigureMask();
        netManager.printNet();
        for (int i = 0; i < figure.figureMask.length; i++) {
            System.arraycopy(figure.figureMask[i], 0, netManager.getNetElement(i), 0,
                    figure.figureMask[0].length);
        }
        netManager.printNet();
        invalidate();
    }

    public void moveLeft() {
        figure.moveLeft();
        netManager.moveLeftInNet();
        invalidate();
    }

    public void moveRight() {
        figure.moveRight();
        netManager.moveRightInNet();
        invalidate();
    }

    private void startMoveDown() {
        netManager.printNet();
        new CountDownTimer(120 * 60 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished % 10 * 1000 == 0) {
                    figure.moveDown();
                    netManager.moveDownInNet();
                    invalidate();
                }
            }
            public void onFinish() {
            }
        }.start();
    }
}
