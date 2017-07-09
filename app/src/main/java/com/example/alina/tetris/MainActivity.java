package com.example.alina.tetris;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alina.tetris.figures.factory.FigureType;

public class MainActivity extends AppCompatActivity {

    private Pole pole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pole = (Pole) findViewById(R.id.pole);
    }

    @Override protected void onResume() {
        super.onResume();
        pole.addFigure(FigureType.LFIGUER);
    }
}
