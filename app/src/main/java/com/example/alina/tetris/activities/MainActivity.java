package com.example.alina.tetris.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alina.tetris.Controller;
import com.example.alina.tetris.R;
import com.example.alina.tetris.listeners.OnPlayingAreaClick;
import com.example.alina.tetris.views.PlayingArea;
import com.example.alina.tetris.views.PreviewArea;
import com.example.alina.tetris.views.ScoreArea;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnPlayingAreaClick {

    @BindView(R.id.pole)
    PlayingArea playingArea;

    @BindView(R.id.tvScore)
    ScoreArea scoreArea;

    @BindView(R.id.controller)
    Controller controller;

    @BindView(R.id.vNextFigure)
    PreviewArea previewArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        playingArea.setScoreArea(scoreArea);
        playingArea.setPreviewArea(previewArea);
        controller.setOnPlayingAreaClick(this);
        playingArea.cleanup();
        playingArea.createFigureWithDelay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playingArea.startTimer();
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

    @Override
    public void onRightButtonClick() {
        playingArea.moveRight();
    }

    @Override
    public void onLeftButtonClick() {
        playingArea.moveLeft();
    }
}
