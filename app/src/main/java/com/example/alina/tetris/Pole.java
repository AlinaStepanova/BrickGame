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
import java.util.concurrent.TimeUnit;

/**
 * Created by Alina on 18.03.2017.
 */

public class Pole extends FrameLayout {

    private int widthOfSquareSide;

    private int squareCount;

    private int screenHeight;

    private int screenWidth;

    private float width = 1f;

    private boolean[][] net;

    private final int SQUARE_COUNT_VERTICAL = 10;

    private final List<FigureType> figureTypeList = new ArrayList<>();

    private Point point;

    private Figure figure;

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
        widthOfSquareSide = MeasureSpec.getSize(widthMeasureSpec) / SQUARE_COUNT_VERTICAL;
        squareCount = MeasureSpec.getSize(heightMeasureSpec) / widthOfSquareSide;
        screenHeight = MeasureSpec.getSize(heightMeasureSpec);
        screenWidth = MeasureSpec.getSize(widthMeasureSpec);
        point = new Point(54, 94);
        initNet();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(width);
        for (int i = 1; i <= SQUARE_COUNT_VERTICAL; i++) {
            canvas.drawLine(i * widthOfSquareSide, 0, i * widthOfSquareSide, screenHeight, paint);
        }

        for (int i = 1; i <= squareCount; i++) {
            canvas.drawLine(0, screenHeight - widthOfSquareSide * i, screenWidth,
                    screenHeight - widthOfSquareSide * i, paint);
        }

        Path path = figure.getPath();
        paint.setColor(figure.getColor());
        canvas.drawPath(path, paint);
        startMoveDown();
    }

    private void initNet() {
        net = new boolean[squareCount][SQUARE_COUNT_VERTICAL];
        setFalseNet();
    }

    private void setFalseNet() {
        for (int i = 0; i < squareCount; i++) {
            for (int j = 0; j < SQUARE_COUNT_VERTICAL; j++) {
                net[i][j] = false;
            }
        }
    }

    private void printNet() {
        String str = "";
        for (int i = 0; i < squareCount; i++) {
            for (int j = 0; j < SQUARE_COUNT_VERTICAL; j++) {
                str += net[i][j];
                str += " ";
            }
            str += '\n';
            Log.d("logNet", str);
        }
    }

    public void addFigure(FigureType figureType) {
        figureTypeList.add(figureType);
        figure = FigureFactory.getFigure(figureTypeList.get(1), widthOfSquareSide, point);
        figure.squareWidth = widthOfSquareSide;
        /*for (int i = 0; i < figure.figureMask.length; i++) {
            System.arraycopy(figure.figureMask[i], 0, net[i], 0, figure.figureMask[0].length);
        }*/
        // TODO: 28.09.2017 figure add mask copy
        invalidate();
    }

    public void moveLeft() {
        figure.moveLeft();
        moveLeftInNet();
        invalidate();
    }

    public void moveRight() {
        figure.moveRight();
        moveRightInNet();
        invalidate();
    }

    private void startMoveDown() {
        setFalseNet();
        int row, column;
        row = point.y / widthOfSquareSide;
        column = point.x / widthOfSquareSide;

        Log.d("logPoint", "" + row + " " + column);
        //for L figure
       /* net[row][column] = true;
        net[row + 1][column] = true;
        net[row + 2][column] = true;
        net[row + 2][column + 1] = true;*/
        printNet();

        new CountDownTimer(120 * 60 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished % 10 * 1000 == 0) {
                    figure.moveDown();
                    moveDownInNet();
                    invalidate();
                }
            }

            public void onFinish() {
            }
        }.start();
    }

    public void moveLeftInNet() {
        int width = figure.getWidthInSquare();
        int height = figure.getHeightInSquare();
        for (int i = figure.coordinateInPole.x; i < figure.coordinateInPole.x + width; i++) {
            swapFigureCoordinatesInNet(figure.coordinateInPole.y,
                    figure.coordinateInPole.y + height, i - 1, i);
        }
    }

    public void moveRightInNet() {
        int width = figure.getWidthInSquare();
        int height = figure.getHeightInSquare();
        for (int i = figure.coordinateInPole.x + width + 1; i > figure.coordinateInPole.x; i--) {
            swapFigureCoordinatesInNet(figure.coordinateInPole.y,
                    figure.coordinateInPole.y + height, i, i + 1);
        }
    }

    public void moveDownInNet() {
        int width = figure.getWidthInSquare();
        int height = figure.getHeightInSquare();
        for (int i = figure.coordinateInPole.y; i < figure.coordinateInPole.y + height; i++) {
            swapFigureCoordinatesInNet(i - 1, i, figure.coordinateInPole.x,
                    figure.coordinateInPole.x + width);
        }
    }

    private void swapFigureCoordinatesInNet(int from, int to, int top, int bottom) {
        for (int i = top; i < bottom; i++) {
            boolean tmp = net[from][i];
            net[from][i] = net[to][i];
            net[to][i] = tmp;
        }
    }
}
