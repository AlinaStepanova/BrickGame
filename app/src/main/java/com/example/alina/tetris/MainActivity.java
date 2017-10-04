package com.example.alina.tetris;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.alina.tetris.figures.factory.FigureType;

public class MainActivity extends AppCompatActivity implements OnControllerListener {

    private Pole pole;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pole = (Pole) findViewById(R.id.pole);
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
                        pole.addFigure(FigureType.SQUARE_FIGURE);
                    }
                }, 2000);
//        pole.addFigure(FigureType.LFIGURE);
//        pole.addFigure(FigureType.LONG_FIGURE);

//        pole.addFigure(FigureType.SFIGURE);
//        pole.addFigure(FigureType.ZFIGURE);
//        pole.addFigure(FigureType.TFIGURE);
    }

    @Override
    public void onRightButtonClick() {
        pole.moveRight();
    }

    @Override
    public void onLeftButtonClick() {
        pole.moveLeft();
    }
}
