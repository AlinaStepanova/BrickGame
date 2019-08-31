package com.avs.brick.game.ui.score;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.avs.brick.game.R;
import com.avs.brick.game.Values;
import com.avs.brick.game.data.SharedPreferencesManager;
import com.avs.brick.game.utils.AnimationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScoreActivity extends AppCompatActivity {

    @BindView(R.id.tvFirstScore)
    TextView firstScore;

    @BindView(R.id.tvSecondScore)
    TextView secondScore;

    @BindView(R.id.tvThirdScore)
    TextView thirdScore;

    @BindView(R.id.llScores)
    LinearLayout scoresLayout;

    @BindView(R.id.ivShare)
    ImageView shareScore;

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
        shareScore.startAnimation(AnimationUtil.getZoomIn(this));
        firstScore.setText(sharedPreferencesManager.getFirstValue());
        secondScore.setText(sharedPreferencesManager.getSecondValue());
        thirdScore.setText(sharedPreferencesManager.getThirdValue());
    }

    @OnClick(R.id.ivShare)
    public void share() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = getResources().getString(R.string.share_body_part_one)
                + sharedPreferencesManager.getFirstValue()
                + getResources().getString(R.string.share_body_part_second)
                + Values.PLAY_MARKET_URL;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_via_text)));
    }
}
