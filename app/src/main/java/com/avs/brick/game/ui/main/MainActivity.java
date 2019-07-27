package com.avs.brick.game.ui.main;

import android.os.Bundle;
import android.widget.ImageView;

import com.avs.brick.game.R;
import com.avs.brick.game.ui.main.listeners.OnPlayingAreaClick;
import com.avs.brick.game.ui.main.listeners.OnTimerStateChangedListener;
import com.avs.brick.game.ui.main.views.ControlsView;
import com.avs.brick.game.ui.main.views.PlayingAreaView;
import com.avs.brick.game.ui.main.views.PreviewAreaView;
import com.avs.brick.game.ui.main.views.ScoreView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnPlayingAreaClick, OnTimerStateChangedListener {

    @BindView(R.id.pole)
    PlayingAreaView playingAreaView;

    @BindView(R.id.tvScore)
    ScoreView scoreView;

    @BindView(R.id.controller)
    ControlsView controlsView;

    @BindView(R.id.tvNextFigure)
    PreviewAreaView previewAreaView;

    @BindView(R.id.ivPausePlay)
    ImageView playPauseImage;

    @BindView(R.id.ivRotate)
    ImageView rotateImage;

    @BindView(R.id.ivMoveDown)
    ImageView moveDownImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        playingAreaView.setDependencies(scoreView, previewAreaView, this);
        controlsView.setOnPlayingAreaClick(this);
        playingAreaView.cleanup();
        playingAreaView.createFigureWithDelay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (playingAreaView.isTimerRunning()) {
            playingAreaView.startTimer();
            setControlsEnabled(true);
        }
    }

    @Override
    protected void onStop() {
        playingAreaView.cancelTimer();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        playingAreaView.cleanup();
        super.onDestroy();
    }

    private void setControlsEnabled(boolean isRunning) {
        rotateImage.setEnabled(isRunning);
        moveDownImage.setEnabled(isRunning);
    }

    @OnClick(R.id.ivMoveDown)
    void moveDown() {
        playingAreaView.fastMoveDown();
    }

    @OnClick(R.id.ivRotate)
    void rotate() {
        playingAreaView.rotate();
    }

    @OnClick(R.id.ivPausePlay)
    void pausePlay() {
        playingAreaView.handleTimerState();
    }

    @Override
    public void isTimerRunning(boolean isRunning) {
        playPauseImage.setImageResource(isRunning ? R.drawable.ic_pause : R.drawable.ic_resume);
        setControlsEnabled(isRunning);
    }

    @Override
    public void disableAllControls() {
        playPauseImage.setEnabled(false);
        setControlsEnabled(false);
    }

    @Override
    public void onRightButtonClick() {
        playingAreaView.moveRight();
    }

    @Override
    public void onLeftButtonClick() {
        playingAreaView.moveLeft();
    }

    @Override
    public void onRightButtonLongClick() {
        playingAreaView.moveRightFast();
    }

    @Override
    public void onLeftButtonLongClick() {
        playingAreaView.moveLeftFast();
    }
}
