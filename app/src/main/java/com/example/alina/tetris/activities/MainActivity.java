package com.example.alina.tetris.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alina.tetris.Controller;
import com.example.alina.tetris.PlayingArea;
import com.example.alina.tetris.R;
import com.example.alina.tetris.ScoreArea;
import com.example.alina.tetris.listeners.OnControllerListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements OnControllerListener {

    @BindView(R.id.pole)
    PlayingArea playingArea;
    @BindView(R.id.tvScore)
    ScoreArea scoreArea;
    @BindView(R.id.controller)
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        playingArea.setScoreArea(scoreArea);
        controller.setOnControllerListener(this);
        playingArea.createFigureWithDelay();
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
