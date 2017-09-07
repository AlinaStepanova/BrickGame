package com.example.alina.tetris;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

    @Override protected void onResume() {
        super.onResume();
        pole.addFigure(FigureType.JFIGURE);
        pole.addFigure(FigureType.LFIGURE);
        pole.addFigure(FigureType.LONG_FIGURE);
        pole.addFigure(FigureType.SQUARE_FIGURE);
        pole.addFigure(FigureType.SFIGURE);
        pole.addFigure(FigureType.ZFIGURE);
        pole.addFigure(FigureType.TFIGURE);
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
