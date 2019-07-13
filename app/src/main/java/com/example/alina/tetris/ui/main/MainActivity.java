package com.example.alina.tetris.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.alina.tetris.R;
import com.example.alina.tetris.ui.main.listeners.OnPlayingAreaClick;
import com.example.alina.tetris.ui.main.listeners.OnTimerStateChangedListener;
import com.example.alina.tetris.ui.main.views.ControlsView;
import com.example.alina.tetris.ui.main.views.PlayingAreaView;
import com.example.alina.tetris.ui.main.views.PreviewAreaView;
import com.example.alina.tetris.ui.main.views.ScoreView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnPlayingAreaClick, OnTimerStateChangedListener {

    @BindView(R.id.pole)
    PlayingAreaView playingAreaView;

    @BindView(R.id.tvScore)
    ScoreView scoreView;

    @BindView(R.id.controller)
    ControlsView controlsView;

    @BindView(R.id.tvNextFigure)
    PreviewAreaView previewAreaView;

    @BindView(R.id.ivPausePlay)
    ImageView playPauseImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        playingAreaView.setScoreView(scoreView);
        playingAreaView.setPreviewAreaView(previewAreaView);
        playingAreaView.setOnTimerStateChanged(this);
        controlsView.setOnPlayingAreaClick(this);
        playingAreaView.cleanup();
        playingAreaView.createFigureWithDelay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (playingAreaView.getTimerState()) {
            playingAreaView.startTimer();
        }
    }

    @Override
    protected void onStop() {
        playingAreaView.cancelTimer();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        playingAreaView.cleanup();
        super.onDestroy();
    }

    @OnClick(R.id.ivMoveDown)
    void moveDown() {
        playingAreaView.fastMoveDown();
    }

    @OnClick(R.id.ivRotate)
    void rotate() {
        playingAreaView.rotate();
    }

    @OnClick(R.id.ivPausePlay)
    void pausePlay() {
        playingAreaView.handleTimerState();
    }

    @Override
    public void isTimerRunning(boolean isRunning) {
        playPauseImage.setImageResource(isRunning ? R.drawable.ic_pause : R.drawable.ic_resume);
    }

    @Override
    public void onRightButtonClick() {
        playingAreaView.moveRight();
    }

    @Override
    public void onLeftButtonClick() {
        playingAreaView.moveLeft();
    }

    @Override
    public void onRightButtonLongClick() {
        playingAreaView.moveRightFast();
    }

    @Override
    public void onLeftButtonLongClick() {
        playingAreaView.moveLeftFast();
    }
}
