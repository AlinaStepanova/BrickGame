package com.example.alina.tetris.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alina.tetris.R;
import com.example.alina.tetris.data.ScoreCounter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreActivity extends AppCompatActivity {

    @BindView(R.id.tvFirstScore)
    TextView firstScore;
    @BindView(R.id.tvSecondScore)
    TextView secondScore;
    @BindView(R.id.tvThirdScore)
    TextView thirdScore;
    @BindView(R.id.llScores)
    LinearLayout scoresLayout;

    private ScoreCounter scoreCounter;

    private void setAnimation() {
        Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        scoresLayout.startAnimation(slideDown);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreCounter = new ScoreCounter(getApplicationContext());
        ButterKnife.bind(this);
        setAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAnimation();
        firstScore.setText(scoreCounter.getFirstValue());
        secondScore.setText(scoreCounter.getSecondValue());
        thirdScore.setText(scoreCounter.getThirdValue());
    }
}
