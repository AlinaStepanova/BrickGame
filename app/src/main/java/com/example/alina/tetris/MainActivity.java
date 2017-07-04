package com.example.alina.tetris;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.alina.tetris.figures.LFigure;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Pole pole = (Pole) findViewById(R.id.pole);
        LFigure lFigure = new LFigure(this);
        pole.addView(lFigure);
    }
}
