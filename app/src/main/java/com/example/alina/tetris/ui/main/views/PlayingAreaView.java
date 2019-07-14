package com.example.alina.tetris.ui.main.views;

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
import android.view.View;
import android.widget.Toast;

import com.example.alina.tetris.Values;
import com.example.alina.tetris.data.SharedPreferencesManager;
import com.example.alina.tetris.enums.FigureState;
import com.example.alina.tetris.enums.FigureType;
import com.example.alina.tetris.figures.Figure;
import com.example.alina.tetris.figures.factory.FigureCreator;
import com.example.alina.tetris.figures.factory.FigureFactory;
import com.example.alina.tetris.ui.main.NetManager;
import com.example.alina.tetris.ui.main.listeners.OnNetChangedListener;
import com.example.alina.tetris.ui.main.listeners.OnTimerStateChangedListener;
import com.example.alina.tetris.utils.CustomArrayList;

import static com.example.alina.tetris.Values.COUNT_DOWN_INTERVAL;
import static com.example.alina.tetris.Values.GAME_OVER_DELAY_IN_MILLIS;
import static com.example.alina.tetris.Values.GAME_OVER_TEXT;
import static com.example.alina.tetris.Values.LINE_WIDTH;
import static com.example.alina.tetris.Values.MILLIS_IN_FUTURE;
import static com.example.alina.tetris.Values.SQUARE_COUNT_HORIZONTAL;

/**
 * Created by Alina on 18.03.2017.
 */

public class PlayingAreaView extends View implements OnNetChangedListener {

    private int squareWidth, verticalSquareCount;
    private int screenHeight, screenWidth;
    private int scale;
    private boolean isTimerRunning;

    //todo remove this, there is no need to keep lists
    private final CustomArrayList<FigureType> figureTypeList = new CustomArrayList<>();
    private final CustomArrayList<Figure> figureList = new CustomArrayList<>();

    private NetManager netManager;
    private FigureCreator figureCreator;
    private ScoreView scoreView;
    private PreviewAreaView previewAreaView;
    private SharedPreferencesManager sharedPreferencesManager;

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

    public PlayingAreaView(@NonNull Context context, @Nullable AttributeSet attrs,
                           @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        figureCreator = new FigureCreator();
        sharedPreferencesManager = new SharedPreferencesManager(getContext());
        this.context = context;
        this.isTimerRunning = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(LINE_WIDTH);
        drawVerticalLines(canvas);
        drawHorizontalLines(canvas);
        for (Figure figure : figureList) {
            if (figure != null && figure.getState() == FigureState.MOVING && isTimerRunning)
                startMoveDown();
        }
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
        squareWidth = MeasureSpec.getSize(widthMeasureSpec) / SQUARE_COUNT_HORIZONTAL;
        verticalSquareCount = MeasureSpec.getSize(heightMeasureSpec) / squareWidth;
        scale = squareWidth - (MeasureSpec.getSize(heightMeasureSpec) % squareWidth);
        screenHeight = MeasureSpec.getSize(heightMeasureSpec);
        screenWidth = MeasureSpec.getSize(widthMeasureSpec);
    }

    public void cleanup() {
        sharedPreferencesManager.putNewScore(scoreView.getScore());
        cancelTimer();
        scoreView.setStartValue();
        netManager = null;
        figureList.clear();
        figureTypeList.clear();
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
        for (int i = 1; i <= SQUARE_COUNT_HORIZONTAL; i++) {
            canvas.drawLine(i * squareWidth, 0, i * squareWidth,
                    screenHeight, paint);
        }
    }

    public boolean isTimerRunning() {
        return isTimerRunning;
    }

    public void handleTimerState() {
        if (isTimerRunning) {
            cancelTimer();
        } else {
            startTimer();
        }
        isTimerRunning = !isTimerRunning;
        onTimerStateChangedListener.isTimerRunning(isTimerRunning);
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
        netManager.printNet();
        cancelTimer();
        timer = new CountDownTimer(MILLIS_IN_FUTURE, COUNT_DOWN_INTERVAL) {
            public void onTick(long millisUntilFinished) {
                isTimerRunning = true;
            }

            public void onFinish() {
                if (figureList.getLast().getState() == FigureState.MOVING) {
                    figureList.getLast().moveDown();
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
        if (figureList.size() > 0 && isTimerRunning) {
            cancelTimer();
            while (figureList.getLast().getState() == FigureState.MOVING) {
                if (!netManager.isNetFreeToMoveDown()) {
                    netManager.changeFigureState();
                    break;
                }
                figureList.getLast().moveDown();
                netManager.moveDownInNet();
            }
            invalidate();
        }
    }

    public void moveRightFast() {
        if (figureList.size() > 0 && isTimerRunning) {
            cancelTimer();
            while (netManager.isNetFreeToMoveRight()) {
                netManager.resetMaskBeforeMoveWithFalse();
                figureList.getLast().moveRight();
                netManager.moveRightInNet();
            }
            invalidate();
        }
    }

    public void moveLeftFast() {
        if (figureList.size() > 0 && isTimerRunning) {
            cancelTimer();
            while (netManager.isNetFreeToMoveLeft()) {
                netManager.resetMaskBeforeMoveWithFalse();
                figureList.getLast().moveLeft();
                netManager.moveLeftInNet();
            }
            invalidate();
        }
    }

    //todo think about the restrictions
    public void rotate() {
        if (figureList.size() > 0 && figureList.getLast().getState() == FigureState.MOVING
                && figureList.getLast().getRotatedFigure() != null && isTimerRunning) {
            Figure figure = FigureFactory.getFigure(figureList.getLast().getRotatedFigure(),
                    squareWidth, scale, context, figureList.getLast().pointOnScreen);
            if (figure != null) {
                figure.initFigureMask();
            }
            if (netManager.canRotate(figure)) {
                figureList.set(figureList.size() - 1, figure);
                netManager.checkBottomLine();
                netManager.initRotatedFigure(figure);
            }
        }
    }

    private void createFigure() {
        figureTypeList.add(figureCreator.getCurrentFigureType());
        Figure figure = FigureFactory.getFigure(figureTypeList.getLast(), squareWidth, scale, context);
        figureList.add(figure);
        if (figure != null) {
            figure.initFigureMask();
            if (netManager == null) {
                netManager = new NetManager(this);
                netManager.initNet(verticalSquareCount, SQUARE_COUNT_HORIZONTAL, squareWidth, scale);
            }
            if (!netManager.isVerticalLineTrue()) {
                netManager.checkBottomLine();
                netManager.initFigure(figure);
                netManager.printNet();
                invalidate();
            }
        }
    }

    public void moveLeft() {
        if (netManager != null && netManager.isNetFreeToMoveLeft() && isTimerRunning) {
            netManager.resetMaskBeforeMoveWithFalse();
            figureList.getLast().moveLeft();
            netManager.moveLeftInNet();
            netManager.printNet();
            invalidate();
        }
    }

    public void moveRight() {
        if (netManager != null && netManager.isNetFreeToMoveRight() && isTimerRunning) {
            netManager.resetMaskBeforeMoveWithFalse();
            figureList.getLast().moveRight();
            netManager.moveRightInNet();
            netManager.printNet();
            invalidate();
        }
    }

    public void createFigureWithDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                previewAreaView.drawNextFigure(FigureFactory.getFigure(figureCreator.getNextFigureType(),
                        squareWidth, context));
                createFigure();
            }
        }, Values.DELAY_IN_MILLIS);
    }

    @Override
    public void onFigureStoppedMove() {
        if (!netManager.isVerticalLineTrue()) {
            scoreView.sumScoreWhenFigureStopped();
            previewAreaView.drawNextFigure(FigureFactory.getFigure(figureCreator.createNextFigure(),
                    squareWidth, context));
            createFigure();
        }
    }

    @Override
    public void onBottomLineIsTrue() {
        scoreView.sumScoreWhenBottomLineIsTrue();
    }

    @Override
    public void onTopLineHasTrue() {
        Toast.makeText(context, GAME_OVER_TEXT, Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((Activity) context).finish();
            }
        }, GAME_OVER_DELAY_IN_MILLIS);
    }
}
