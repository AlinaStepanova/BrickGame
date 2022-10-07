package com.avs.brick.game.ui.score;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.avs.brick.game.R;
import com.avs.brick.game.Values;
import com.avs.brick.game.data.SharedPreferencesManager;
import com.avs.brick.game.utils.AnimationUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;

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
        scoresLayout.startAnimation(AnimationUtil.getZoomIn(this));
        firstScore.setText(sharedPreferencesManager.getFirstValue());
        secondScore.setText(sharedPreferencesManager.getSecondValue());
        thirdScore.setText(sharedPreferencesManager.getThirdValue());
        shareScore.startAnimation(AnimationUtil.getZoomIn(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sharedPreferencesManager.isGreatFirstScore()) {
            launchReviewFlow();
        }
    }

    @OnClick(R.id.ivShare)
    public void share() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType(Values.SHARE_INTENT_TYPE);
        String shareBody = getResources().getString(R.string.share_body_part_one)
                + sharedPreferencesManager.getFirstValue()
                + " " + getResources().getString(R.string.share_body_part_second) + "\n\n"
                + Values.PLAY_MARKET_URL;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_via_text)));
    }

    private void launchReviewFlow() {
        if (getBaseContext() != null) {
            ReviewManager manager = ReviewManagerFactory.create(getBaseContext());
            Task<ReviewInfo> request = manager.requestReviewFlow();
            request.addOnCompleteListener(task -> {
                if (task.getResult() != null) {
                    Task<Void> flow = manager.launchReviewFlow(this, task.getResult());
                    flow.addOnCompleteListener(result -> {
                        Log.d("Review", "Review flow completed");
                    });
                }
            });
        }
    }
}
