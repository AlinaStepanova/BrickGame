package com.avs.brick.game.ui.main.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.avs.brick.game.BuildConfig;
import com.avs.brick.game.R;
import com.avs.brick.game.Values;
import com.avs.brick.game.data.SharedPreferencesManager;
import com.avs.brick.game.enums.FigureState;
import com.avs.brick.game.figures.Figure;
import com.avs.brick.game.figures.factory.FigureCreator;
import com.avs.brick.game.figures.factory.FigureFactory;
import com.avs.brick.game.ui.main.NetManager;
import com.avs.brick.game.ui.main.listeners.OnNetChangedListener;
import com.avs.brick.game.ui.main.listeners.OnPlayingAreaTouch;
import com.avs.brick.game.ui.main.listeners.OnViewTouchListener;
import com.avs.brick.game.ui.main.listeners.OnTimerStateChangedListener;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.avs.brick.game.Values.COUNT_DOWN_INTERVAL;
import static com.avs.brick.game.Values.GAME_OVER_DELAY_IN_MILLIS;
import static com.avs.brick.game.Values.LINE_WIDTH;

/**
 * Created by Alina on 18.03.2017.
 */

public class PlayingAreaView extends View implements OnNetChangedListener, OnPlayingAreaTouch {

    private int squareWidth, verticalSquareCount;
    private int screenHeight, screenWidth;
    private int scale;
    private int squaresInRowCount;
    private boolean isTimerRunning, isGameOver;

    private Figure currentFigure;

    private NetManager netManager;
    private FigureCreator figureCreator;
    private ScoreView scoreView;
    private PreviewAreaView previewAreaView;
    private SharedPreferencesManager sharedPreferencesManager;
    private OnViewTouchListener onViewTouchListener;

    private Paint paint;

    private CountDownTimer timer;

    private Context context;
    private OnTimerStateChangedListener onTimerStateChangedListener;

    public PlayingAreaView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PlayingAreaView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayingAreaView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        figureCreator = new FigureCreator();
        sharedPreferencesManager = new SharedPreferencesManager(getContext());
        onViewTouchListener = new OnViewTouchListener(context, this);
        setOnTouchListener(onViewTouchListener);
        this.squaresInRowCount = sharedPreferencesManager.getSquaresCountInRow();
        this.context = context;
        this.isTimerRunning = true;
        this.isGameOver = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(LINE_WIDTH);
        drawHorizontalLines(canvas);
        drawVerticalLines(canvas);
        if (currentFigure != null && currentFigure.getState() == FigureState.MOVING && isTimerRunning)
            startMoveDown();
        if (netManager != null && netManager.getStoppedFiguresPaths() != null) {
            for (Path squarePath : netManager.getStoppedFiguresPaths()) {
                paint.setColor(getResources().getColor(sharedPreferencesManager.getFiguresColor()));
                canvas.drawPath(squarePath, paint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        squareWidth = MeasureSpec.getSize(widthMeasureSpec) / squaresInRowCount;
        verticalSquareCount = MeasureSpec.getSize(heightMeasureSpec) / squareWidth;
        scale = squareWidth - (MeasureSpec.getSize(heightMeasureSpec) % squareWidth);
        screenHeight = MeasureSpec.getSize(heightMeasureSpec);
        screenWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (onViewTouchListener != null) onViewTouchListener.setScreenWidth(screenWidth);
    }

    public void cleanup() {
        sharedPreferencesManager.putNewScore(scoreView.getScore());
        cancelTimer();
        scoreView.setStartValue();
        netManager = null;
        currentFigure = null;
    }

    public void setDependencies(ScoreView scoreView, PreviewAreaView previewAreaView,
                                OnTimerStateChangedListener onTimerStateChangedListener) {
        this.scoreView = scoreView;
        this.previewAreaView = previewAreaView;
        this.onTimerStateChangedListener = onTimerStateChangedListener;
    }

    private void drawHorizontalLines(Canvas canvas) {
        for (int i = 1; i <= verticalSquareCount; i++) {
            canvas.drawLine(0, screenHeight - squareWidth * i, screenWidth,
                    screenHeight - squareWidth * i, paint);
        }
    }

    private void drawVerticalLines(Canvas canvas) {
        for (int i = 1; i <= squaresInRowCount; i++) {
            if (sharedPreferencesManager.isHintsEnabled()) drawVerticalHints(i);
            canvas.drawLine(i * squareWidth, 0, i * squareWidth, screenHeight, paint);
        }
    }

    private void drawVerticalHints(int line) {
        if (currentFigure != null) {
            if (line == currentFigure.getCurrentX() || line == currentFigure.getCurrentX() + currentFigure.getWidthInSquare()) {
                paint.setColor(getResources().getColor(R.color.colorPrimaryTransparent));
                paint.setStrokeWidth(LINE_WIDTH * 4);
            } else {
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(LINE_WIDTH);
            }
        }
    }

    public boolean isTimerRunning() {
        return isTimerRunning && !isGameOver;
    }

    public void handleTimerState() {
        if (isTimerRunning) {
            cancelTimer();
        } else {
            startTimer();
        }
        isTimerRunning = !isTimerRunning;
        if (!isGameOver) onTimerStateChangedListener.isTimerRunning(isTimerRunning);
    }

    public void startTimer() {
        if (timer != null) {
            timer.start();
        }
    }

    public void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private void startMoveDown() {
        if (BuildConfig.DEBUG) netManager.printNet();
        cancelTimer();
        timer = new CountDownTimer(sharedPreferencesManager.getFiguresSpeed(), COUNT_DOWN_INTERVAL) {
            public void onTick(long millisUntilFinished) {
                isTimerRunning = true;
            }

            public void onFinish() {
                if (currentFigure.getState() == FigureState.MOVING) {
                    currentFigure.moveDown();
                    netManager.moveDownInNet();
                    invalidate();
                }
            }
        };
        if (!netManager.isNetFreeToMoveDown()) {
            netManager.changeFigureState();
            cancelTimer();
        } else {
            startTimer();
        }
    }

    public void fastMoveDown() {
        if (currentFigure != null && isTimerRunning) {
            cancelTimer();
            while (currentFigure.getState() == FigureState.MOVING) {
                if (!netManager.isNetFreeToMoveDown()) {
                    netManager.changeFigureState();
                    break;
                }
                currentFigure.moveDown();
                netManager.moveDownInNet();
            }
            invalidate();
        }
    }

    public void moveRightFast() {
        if (currentFigure != null && isTimerRunning) {
            cancelTimer();
            while (netManager.isNetFreeToMoveRight()) {
                netManager.resetMaskBeforeMoveWithFalse();
                currentFigure.moveRight();
                netManager.moveRightInNet();
            }
            invalidate();
        }
    }

    public void moveLeftFast() {
        if (currentFigure != null && isTimerRunning) {
            cancelTimer();
            while (netManager.isNetFreeToMoveLeft()) {
                netManager.resetMaskBeforeMoveWithFalse();
                currentFigure.moveLeft();
                netManager.moveLeftInNet();
            }
            invalidate();
        }
    }

    //todo think about the restrictions
    public void rotate() {
        if (currentFigure != null && currentFigure.getState() == FigureState.MOVING
                && currentFigure.getRotatedFigure() != null && isTimerRunning) {
            Figure figure = FigureFactory.getFigure(currentFigure.getRotatedFigure(),
                    squareWidth, scale, context, currentFigure.pointOnScreen);
            if (figure != null) {
                figure.initFigureMask();
                if (netManager.canRotate(figure)) {
                    currentFigure = figure;
                    netManager.checkBottomLine();
                    netManager.initRotatedFigure(figure);
                }
            }
        }
    }

    private void createFigure() {
        Figure figure = FigureFactory.getFigure(figureCreator.getCurrentFigureType(),
                squareWidth, scale, squaresInRowCount, context);
        if (figure != null) {
            currentFigure = figure;
            if (netManager == null) {
                netManager = new NetManager(this, verticalSquareCount,
                        squaresInRowCount, squareWidth, scale);
            }
            if (netManager.isVerticalLineComplete()) {
                netManager.checkBottomLine();
                netManager.initFigure(currentFigure);
                if (BuildConfig.DEBUG) netManager.printNet();
                invalidate();
            }
        }
    }

    public void moveLeft() {
        if (netManager != null && netManager.isNetFreeToMoveLeft() && isTimerRunning) {
            netManager.resetMaskBeforeMoveWithFalse();
            currentFigure.moveLeft();
            netManager.moveLeftInNet();
            if (BuildConfig.DEBUG) netManager.printNet();
            invalidate();
        }
    }

    public void moveRight() {
        if (netManager != null && netManager.isNetFreeToMoveRight() && isTimerRunning) {
            netManager.resetMaskBeforeMoveWithFalse();
            currentFigure.moveRight();
            netManager.moveRightInNet();
            if (BuildConfig.DEBUG) netManager.printNet();
            invalidate();
        }
    }

    public void createFigureWithDelay() {
        new Handler().postDelayed(() -> {
            previewAreaView.drawNextFigure(FigureFactory.getFigure(figureCreator.getNextFigureType(),
                    (squareWidth * squaresInRowCount) / Values.SQUARES_COUNT_IN_ROW, context));
            createFigure();
        }, Values.DELAY_IN_MILLIS);
    }

    @Override
    public void onFigureStoppedMove() {
        if (netManager.isVerticalLineComplete()) {
            scoreView.sumScoreWhenFigureStopped();
            previewAreaView.drawNextFigure(FigureFactory.getFigure(figureCreator.createNextFigure(),
                    (squareWidth * squaresInRowCount) / Values.SQUARES_COUNT_IN_ROW, context));
            createFigure();
        }
    }

    @Override
    public void onBottomLineIsTrue() {
        scoreView.sumScoreWhenBottomLineIsTrue(squaresInRowCount);
    }

    @Override
    public void onTopLineHasTrue() {
        isGameOver = true;
        cancelTimer();
        onTimerStateChangedListener.disableAllControls();
        Toast.makeText(context, context.getString(R.string.game_over_text), Toast.LENGTH_LONG).show();
        new Handler().postDelayed(() -> ((Activity) context).finish(), GAME_OVER_DELAY_IN_MILLIS);
    }

    @Override
    public void onRightMove() {
        moveRight();
    }

    @Override
    public void onLeftMove() {
        moveLeft();
    }

    @Override
    public void onLongLeftClick() {
        moveLeftFast();
    }

    @Override
    public void onLongRightClick() {
        moveRightFast();
    }
}
