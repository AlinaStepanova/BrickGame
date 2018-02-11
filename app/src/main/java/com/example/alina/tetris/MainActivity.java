package com.example.alina.tetris;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.alina.tetris.listeners.OnControllerListener;

public class MainActivity extends AppCompatActivity implements OnControllerListener {

    private PlayingArea playingArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playingArea = (PlayingArea) findViewById(R.id.pole);
        ScoreArea scoreArea = (ScoreArea) findViewById(R.id.tvScore);
        playingArea.setScoreArea(scoreArea);
        Controller controller = (Controller) findViewById(R.id.controller);
        controller.setOnControllerListener(this);
        createFigure();
    }

    private void createFigure() {
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        playingArea.createFigure();
                    }
                }, 2000);
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
