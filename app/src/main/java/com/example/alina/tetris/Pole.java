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
import android.util.Log;
import android.widget.FrameLayout;

import com.example.alina.tetris.figures.Figure;
import com.example.alina.tetris.figures.factory.FigureFactory;
import com.example.alina.tetris.figures.factory.FigureType;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Created by Alina on 18.03.2017.
 */

public class Pole extends FrameLayout {

    private int widthOfSquareSide;

    private int squareCount;

    private int screenHeight;

    private int screenWidth;

    private float width = 1f;

    private boolean [][]net;

    private final int SQUARE_COUNT_VERTICAL = 10;

    private final List<FigureType> figureTypeList = new ArrayList<>();

    private Point point;

    private Figure figure;

    private Paint paint = new Paint();

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
        widthOfSquareSide = MeasureSpec.getSize(widthMeasureSpec) / SQUARE_COUNT_VERTICAL;
        squareCount = MeasureSpec.getSize(heightMeasureSpec) / widthOfSquareSide;
        screenHeight = MeasureSpec.getSize(heightMeasureSpec);
        screenWidth = MeasureSpec.getSize(widthMeasureSpec);
        point  = new Point(54, 94);
        initNet();
        printNet();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(width);
        for (int i = 1; i <= SQUARE_COUNT_VERTICAL; i++) {
            canvas.drawLine(i * widthOfSquareSide, 0, i * widthOfSquareSide, screenHeight, paint);
        }
        //old version of drawing horizontal lines
        /*for (int i = 1; i <= squareCount; i++) {
            canvas.drawLine(0, i * widthOfSquareSide, screenWidth, i * widthOfSquareSide, paint);
        }*/
        for (int i = 1; i <= squareCount; i++) {
            canvas.drawLine(0, screenHeight - widthOfSquareSide * i, screenWidth, screenHeight - widthOfSquareSide * i, paint);
        }

        figure = FigureFactory.getFigure(figureTypeList.get(1), widthOfSquareSide, point);
        figure.squareWidth = widthOfSquareSide;
        Path path = figure.getPath();
        paint.setColor(figure.getColor());
        canvas.drawPath(path, paint);
        startMoveDown();
    }

    private void initNet(){
        net = new boolean[squareCount][SQUARE_COUNT_VERTICAL];
        set0Net();
    }

    private void set0Net() {
        for (int i = 0; i < squareCount; i++){
            for (int j = 0; j < SQUARE_COUNT_VERTICAL; j++){
                net[i][j] = false;
            }
        }
    }

    private void printNet() {
        String str = "";
        for (int i = 0; i < squareCount; i++){
            for (int j = 0; j < SQUARE_COUNT_VERTICAL; j++){
                str += net[i][j];
                str += " ";
            }
            str += '\n';
            Log.d("logNet", str);
        }
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
        set0Net();
        int row, column;
        row = point.y / widthOfSquareSide;
        column = point.x / widthOfSquareSide;

        Log.d("logPoint", "" + row + " " + column);
        //for L figure
        net[row][column] = true;
        net[row + 1][column] = true;
        net[row + 2][column] = true;
        net[row + 2][column + 1] = true;
        printNet();

        new CountDownTimer(4000, 2000) {
            public void onTick(long millisUntilFinished) {
                figure.moveDown();
                invalidate();
            }
            public void onFinish() {
            }
        }.start();
    }
}
