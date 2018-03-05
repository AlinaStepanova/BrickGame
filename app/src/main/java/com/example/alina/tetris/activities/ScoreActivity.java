package com.example.alina.tetris.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.alina.tetris.R;
import com.example.alina.tetris.ScoreCounter;

public class ScoreActivity extends AppCompatActivity {

    private TextView firstScore;
    private TextView secondScore;
    private TextView thirdScore;
    private ScoreCounter scoreCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreCounter = new ScoreCounter(getApplicationContext());
        initDefaultValues();
    }

    @Override
    protected void onResume() {
        super.onResume();
        firstScore.setText(scoreCounter.getFirstValue());
        secondScore.setText(scoreCounter.getSecondValue());
        thirdScore.setText(scoreCounter.getThirdValue());
    }

    private void initDefaultValues() {
        firstScore = (TextView) findViewById(R.id.tvFirstScore);
        secondScore = (TextView) findViewById(R.id.tvSecondScore);
        thirdScore = (TextView) findViewById(R.id.tvThirdScore);
    }
}
