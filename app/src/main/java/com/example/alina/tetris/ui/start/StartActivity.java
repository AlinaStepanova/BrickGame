package com.example.alina.tetris.ui.start;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.alina.tetris.R;
import com.example.alina.tetris.ui.main.MainActivity;
import com.example.alina.tetris.ui.score.ScoreActivity;
import com.example.alina.tetris.ui.settings.SettingsActivity;
import com.example.alina.tetris.utils.AnimationUtil;

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
        setTitleAnimation();
        setButtonAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitleAnimation();
    }

    private void setTitleAnimation() {
        gameTitle.startAnimation(AnimationUtil.getZoomIn(this));
    }

    private void setButtonAnimation() {
        startGameButton.startAnimation(AnimationUtil.getSlideInLeft(this));
        openScoresButton.startAnimation(AnimationUtil.getSlideInRight(this));
    }

    @OnClick(R.id.bStartGame)
    void startGame() {
        this.startActivity(new Intent(this, MainActivity.class));
    }

    @OnClick(R.id.bOpenScores)
    void openScores() {
        this.startActivity(new Intent(this, ScoreActivity.class));
    }

    @OnClick(R.id.ivSettings)
    void openSettings() {
        this.startActivity(new Intent(this, SettingsActivity.class));
    }
}
