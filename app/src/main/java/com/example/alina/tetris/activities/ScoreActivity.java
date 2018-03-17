package com.example.alina.tetris.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.alina.tetris.PlayingArea;
import com.example.alina.tetris.R;
import com.example.alina.tetris.ScoreCounter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreActivity extends AppCompatActivity {

    @BindView(R.id.tvFirstScore)
    TextView firstScore;
    @BindView(R.id.tvSecondScore)
    TextView secondScore;
    @BindView(R.id.tvThirdScore)
    TextView thirdScore;
    private ScoreCounter scoreCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreCounter = new ScoreCounter(getApplicationContext());
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        firstScore.setText(scoreCounter.getFirstValue());
        secondScore.setText(scoreCounter.getSecondValue());
        thirdScore.setText(scoreCounter.getThirdValue());
    }
}
