package com.example.alina.tetris.ui.main.views;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

import com.example.alina.tetris.ui.main.NetManager;

import static com.example.alina.tetris.Values.DEFAULT_VALUE;
import static com.example.alina.tetris.Values.EXTRA_SCORE;
import static com.example.alina.tetris.Values.FIGURE_STOPPED_SCORE;

public class ScoreView extends AppCompatTextView {

    public ScoreView(Context context) {
        super(context);
    }

    public ScoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getScore() {
        return Integer.parseInt(getText().toString());
    }

    private void setScore(int score) {
        this.setText(String.valueOf(score));
    }

    public void setStartValue() {
        setScore(DEFAULT_VALUE);
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
