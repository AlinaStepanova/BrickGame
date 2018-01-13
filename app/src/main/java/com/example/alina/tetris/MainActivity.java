package com.example.alina.tetris;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.alina.tetris.figures.factory.FigureType;
import com.example.alina.tetris.listeners.OnControllerListener;

public class MainActivity extends AppCompatActivity implements OnControllerListener {

    private PlayingArea playingArea;
    private FigureCreator figureCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playingArea = (PlayingArea) findViewById(R.id.pole);
        Controller controller = (Controller) findViewById(R.id.controller);
        controller.setOnControllerListener(this);
        figureCreator = new FigureCreator();
    }

    private void createFigureWithInterval() {
        new CountDownTimer(5000, 2000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                playingArea.addFigure(figureCreator.selectFigure());
            }
        }.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        createFigureWithInterval();
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
