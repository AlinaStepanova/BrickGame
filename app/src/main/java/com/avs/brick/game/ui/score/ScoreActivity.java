package com.avs.brick.game.ui.score;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avs.brick.game.R;
import com.avs.brick.game.data.SharedPreferencesManager;
import com.avs.brick.game.utils.AnimationUtil;

import androidx.appcompat.app.AppCompatActivity;
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

    private SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        sharedPreferencesManager = new SharedPreferencesManager(getApplicationContext());
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        scoresLayout.startAnimation(AnimationUtil.getZoomIn(this));
        firstScore.setText(sharedPreferencesManager.getFirstValue());
        secondScore.setText(sharedPreferencesManager.getSecondValue());
        thirdScore.setText(sharedPreferencesManager.getThirdValue());
    }
}
