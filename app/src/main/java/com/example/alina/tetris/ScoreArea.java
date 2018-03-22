package com.example.alina.tetris;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import static com.example.alina.tetris.values.Values.EXTRA_SCORE;
import static com.example.alina.tetris.values.Values.FIGURE_STOPPED_SCORE;

public class ScoreArea extends AppCompatTextView {

    public ScoreArea(Context context) {
        super(context);
    }

    public ScoreArea(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScoreArea(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getScore() {
        return Integer.parseInt(getText().toString());
    }

    private void setScore(int score) {
        this.setText(String.valueOf(score));
    }

    public void sumScoreWhenFigureStopped() {
        int scoreValue = getScore();
        scoreValue += FIGURE_STOPPED_SCORE;
        setScore(scoreValue);
    }

    public void sumScoreWhenBottomLineIsTrue() {
        int scoreValue = getScore();
        scoreValue += EXTRA_SCORE * NetManager.combo;
        setScore(scoreValue);
    }
}
