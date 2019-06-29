package com.example.alina.tetris.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.alina.tetris.Controller;
import com.example.alina.tetris.R;
import com.example.alina.tetris.listeners.OnPlayingAreaClick;
import com.example.alina.tetris.listeners.OnTimerStateChangedListener;
import com.example.alina.tetris.views.PlayingArea;
import com.example.alina.tetris.views.PreviewArea;
import com.example.alina.tetris.views.ScoreArea;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnPlayingAreaClick, OnTimerStateChangedListener {

    @BindView(R.id.pole)
    PlayingArea playingArea;

    @BindView(R.id.tvScore)
    ScoreArea scoreArea;

    @BindView(R.id.controller)
    Controller controller;

    @BindView(R.id.tvNextFigure)
    PreviewArea previewArea;

    @BindView(R.id.ivPausePlay)
    ImageView playPauseImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        playingArea.setScoreArea(scoreArea);
        playingArea.setPreviewArea(previewArea);
        playingArea.setOnTimerStateChanged(this);
        controller.setOnPlayingAreaClick(this);
        playingArea.cleanup();
        playingArea.createFigureWithDelay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (playingArea.getTimerState()) {
            playingArea.startTimer();
        }
    }

    @Override
    protected void onStop() {
        playingArea.cancelTimer();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        playingArea.cleanup();
        super.onDestroy();
    }

    @OnClick(R.id.ivMoveDown)
    void moveDown() {
        playingArea.fastMoveDown();
    }

    @OnClick(R.id.ivRotate)
    void rotate() {
        playingArea.rotate();
    }

    @OnClick(R.id.ivPausePlay)
    void pausePlay() {
        playingArea.handleTimerState();
    }

    @Override
    public void isTimerRunning(boolean isRunning) {
        playPauseImage.setImageResource(isRunning ? R.drawable.ic_pause : R.drawable.ic_resume);
    }

    @Override
    public void onRightButtonClick() {
        playingArea.moveRight();
    }

    @Override
    public void onLeftButtonClick() {
        playingArea.moveLeft();
    }

    @Override
    public void onRightButtonLongClick() {
        playingArea.moveRightFast();
    }

    @Override
    public void onLeftButtonLongClick() {
        playingArea.moveLeftFast();
    }
}
