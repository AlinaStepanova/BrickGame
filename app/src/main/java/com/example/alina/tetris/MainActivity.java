package com.example.alina.tetris;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.alina.tetris.figures.factory.FigureType;
import com.example.alina.tetris.listeners.OnControllerListener;

public class MainActivity extends AppCompatActivity implements OnControllerListener {

    private PlayingArea playingArea;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playingArea = (PlayingArea) findViewById(R.id.pole);
        controller = (Controller) findViewById(R.id.controller);
        controller.setOnControllerListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        playingArea.addFigure(FigureType.L_FIGURE);
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
