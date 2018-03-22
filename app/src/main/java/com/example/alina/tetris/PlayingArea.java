package com.example.alina.tetris;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.alina.tetris.data.ScoreCounter;
import com.example.alina.tetris.enums.FigureState;
import com.example.alina.tetris.enums.FigureType;
import com.example.alina.tetris.figures.Figure;
import com.example.alina.tetris.figures.factory.FigureCreator;
import com.example.alina.tetris.figures.factory.FigureFactory;
import com.example.alina.tetris.listeners.OnNetChangedListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.alina.tetris.values.Values.COUNT_DOWN_INTERVAL;
import static com.example.alina.tetris.values.Values.ENUM_LENGTH;
import static com.example.alina.tetris.values.Values.GAME_OVER_TEXT;
import static com.example.alina.tetris.values.Values.INITIAL_FIGURE_TYPE_LIST_LENGTH;
import static com.example.alina.tetris.values.Values.LINE_WIDTH;
import static com.example.alina.tetris.values.Values.MILLIS_IN_FUTURE;
import static com.example.alina.tetris.values.Values.SQUARE_COUNT_HORIZONTAL;

/**
 * Created by Alina on 18.03.2017.
 */

public class PlayingArea extends View implements OnNetChangedListener {

    private int widthOfSquareSide;

    private int verticalSquareCount;

    private int screenHeight;

    private int screenWidth;

    private int scale;

    private final List<FigureType> figureTypeList = new ArrayList<>();

    private final List<Figure> figureList = new ArrayList<>();

    private Paint paint;

    private NetManager netManager;

    private FigureCreator figureCreator;

    private ScoreArea scoreArea;

    private ScoreCounter scoreCounter;

    private Context context;

    public PlayingArea(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PlayingArea(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayingArea(@NonNull Context context, @Nullable AttributeSet attrs,
                       @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        figureCreator = new FigureCreator();
        scoreCounter = new ScoreCounter(getContext());
        this.context = context;
    }

    public void setScoreArea(ScoreArea scoreArea) {
        this.scoreArea = scoreArea;
    }

    private void drawHorizontalLines(Canvas canvas) {
        for (int i = 1; i <= verticalSquareCount; i++) {
            canvas.drawLine(0, screenHeight - widthOfSquareSide * i, screenWidth,
                    screenHeight - widthOfSquareSide * i, paint);
        }
    }

    private void drawVerticalLines(Canvas canvas) {
        for (int i = 1; i <= SQUARE_COUNT_HORIZONTAL; i++) {
            canvas.drawLine(i * widthOfSquareSide, 0, i * widthOfSquareSide,
                    screenHeight, paint);
        }
    }

    private void startMoveDown() {
        netManager.printNet();
        CountDownTimer timer =
                new CountDownTimer(MILLIS_IN_FUTURE + figureList.size() * 1500,
                COUNT_DOWN_INTERVAL + figureList.size() * 1000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                if(figureList.get(figureList.size() - 1).getState() == FigureState.MOVING) {
                    figureList.get(figureList.size() - 1).moveDown();
                    netManager.moveDownInNet();
                    invalidate();
                }
            }
        };
        if (!netManager.isNetFreeToMoveDown()) {
            netManager.changeFigureState();
            timer.cancel();
        } else {
            timer.start();
        }
    }

    private void resetFiguresScale(int count) {
        for(int i = 0; i < figureList.size() - 1; i++) {
            figureList.get(i).increaseScale(count * widthOfSquareSide);
        }
    }

    private void createFigure() {
        if(figureTypeList.size() < INITIAL_FIGURE_TYPE_LIST_LENGTH) {
            figureTypeList.add(figureCreator.selectFigure(ENUM_LENGTH));
        } else {
            figureTypeList.add(figureCreator.selectFigure());
        }
        Figure figure = FigureFactory.getFigure(figureTypeList.get(figureTypeList.size() - 1),
                widthOfSquareSide, scale, getContext());
        Log.d("pref", figureTypeList.get(figureTypeList.size() - 1).toString());
        figureList.add(figure);
        if (figure != null) {
            figure.initFigureMask();
        }
        if (netManager == null) {
            netManager = new NetManager();
            netManager.initNet(verticalSquareCount, SQUARE_COUNT_HORIZONTAL);
        }
        if (!netManager.isVerticalLineTrue()) {
            netManager.setOnNetChangedListener(this);
            resetFiguresScale(netManager.checkBottomLine());
            netManager.initFigure(figure);
            netManager.printNet();
            invalidate();
        }
    }

    public void moveLeft() {
        if (netManager.isNetFreeToMoveLeft()) {
            figureList.get(figureList.size() - 1).moveLeft();
            netManager.moveLeftInNet();
            netManager.printNet();
            invalidate();
        }
    }

    public void moveRight() {
        if (netManager.isNetFreeToMoveRight()) {
            figureList.get(figureList.size() - 1).moveRight();
            netManager.moveRightInNet();
            netManager.printNet();
            invalidate();
        }
    }

    public void createFigureWithDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                createFigure();
            }
        }, 1500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(LINE_WIDTH);
        drawVerticalLines(canvas);
        drawHorizontalLines(canvas);
        for(Figure figure: figureList) {
            if (figure != null) {
                Path path = figure.getPath();
                paint.setColor(figure.getColor());
                canvas.drawPath(path, paint);
                if(figure.getState() == FigureState.MOVING) {
                    startMoveDown();
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthOfSquareSide = MeasureSpec.getSize(widthMeasureSpec) / SQUARE_COUNT_HORIZONTAL;
        verticalSquareCount = MeasureSpec.getSize(heightMeasureSpec) / widthOfSquareSide;
        scale = widthOfSquareSide - (MeasureSpec.getSize(heightMeasureSpec) % widthOfSquareSide);
        screenHeight = MeasureSpec.getSize(heightMeasureSpec);
        screenWidth = MeasureSpec.getSize(widthMeasureSpec);
    }

    @Override
    public void onFigureStoppedMove() {
        if (!netManager.isVerticalLineTrue()) {
            scoreArea.sumScoreWhenFigureStopped();
            createFigure();
        }
    }

    @Override
    public void onBottomLineIsTrue() {
        scoreArea.sumScoreWhenBottomLineIsTrue();
    }

    @Override
    public void onTopLineHasTrue() {
        scoreCounter.putNewScore(scoreArea.getScore());
        Toast.makeText(context, GAME_OVER_TEXT, Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((Activity)context).finish();
            }
        }, 4000);
    }
}
