package com.example.alina.tetris;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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
        createFigure();
    }

    private void createFigure() {
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        playingArea.addFigure(figureCreator.selectFigure());
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
