package com.example.alina.tetris.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.alina.tetris.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.tvGameTitle)
    TextView gameTitle;
    @BindView(R.id.bStartGame)
    TextView startGameButton;
    @BindView(R.id.bOpenScores)
    TextView openScoresButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        setTextAnimation();
        setButtonAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTextAnimation();
    }

    private void setTextAnimation() {
        Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        gameTitle.startAnimation(slideDown);
    }

    private void setButtonAnimation() {
        Animation slideLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        startGameButton.startAnimation(slideLeft);
        Animation slideRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        openScoresButton.startAnimation(slideRight);
    }

    @OnClick (R.id.bStartGame)
    void startGame() {
        this.startActivity(new Intent(this, MainActivity.class));
    }

    @OnClick (R.id.bOpenScores)
    void openScores() {
        this.startActivity(new Intent(this, ScoreActivity.class));
    }
}
