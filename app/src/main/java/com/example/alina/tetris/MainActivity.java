package com.example.alina.tetris;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Pole pole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pole = new Pole(this);
        pole.setBackgroundColor(Color.WHITE);
        setContentView(R.layout.activity_main);
    }
}
