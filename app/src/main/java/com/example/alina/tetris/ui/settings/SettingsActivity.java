package com.example.alina.tetris.ui.settings;

import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alina.tetris.R;
import com.example.alina.tetris.Values;
import com.example.alina.tetris.data.SharedPreferencesManager;
import com.example.alina.tetris.utils.Utils;
import com.shawnlin.numberpicker.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity implements SettingsView {

    @BindView(R.id.clColorPicker)
    ConstraintLayout colorPicker;

    @BindView(R.id.speedNumberPicker)
    NumberPicker speedNumberPicker;

    @BindView(R.id.squaresCountNumberPicker)
    NumberPicker squaresNumberPicker;

    @BindView(R.id.tvSpeedTitle)
    TextView speedTitle;

    @BindView(R.id.sEnableHints)
    Switch enableHintsSwitch;

    private SettingsPresenter settingsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        settingsPresenter = new SettingsPresenter(this,
                new SharedPreferencesManager(getApplicationContext()));
        speedNumberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> settingsPresenter.setFigureSpeed(newVal));
        squaresNumberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> settingsPresenter.setSquareCountInRow(newVal));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (settingsPresenter != null) settingsPresenter.setValues();
    }

    @Override
    public void markChosenColor(int oldColor, int newItemId) {
        ImageView oldImageView = findViewById(Utils.getViewIdByColor(oldColor));
        oldImageView.setImageDrawable(null);
        ImageView newImageView = findViewById(newItemId);
        newImageView.setImageDrawable(getDrawable(R.drawable.ic_ok));
    }

    @Override
    public void setSpeed(int newValue) {
        if (speedNumberPicker != null) speedNumberPicker.setValue(newValue);
    }

    @Override
    public void setSpeedTitle(String figureSpeedTitle) {
        speedTitle.setText(figureSpeedTitle);
    }

    @Override
    public void setVerticalHintsChecked(boolean hintsEnabled) {
        enableHintsSwitch.setChecked(hintsEnabled);
    }

    @Override
    public void setSquaresCountInRow(int squaresCountInRow) {
        if (squaresNumberPicker != null) squaresNumberPicker.setValue(squaresCountInRow);
    }

    @OnClick(R.id.sEnableHints)
    void enableHints() {
        settingsPresenter.getEvent(R.id.sEnableHints);
    }

    @OnClick(R.id.flFeedback)
    void sendFeedback() {
        try {
            startActivity(Utils.openGmail(this, Values.RECIPIENTS, getResources().getString(R.string.app_name)));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, getResources().getString(R.string.canot_send_email_error_text), Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.vLFigureColor)
    void chooseColorFirst() {
        settingsPresenter.getEvent(R.id.vLFigureColor);
    }

    @OnClick(R.id.vSquareFigureColor)
    void chooseColorSecond() {
        settingsPresenter.getEvent(R.id.vSquareFigureColor);
    }

    @OnClick(R.id.vLongFigureColor)
    void chooseColorThird() {
        settingsPresenter.getEvent(R.id.vLongFigureColor);
    }

    @OnClick(R.id.vZFigureColor)
    void chooseColorFourth() {
        settingsPresenter.getEvent(R.id.vZFigureColor);
    }

    @OnClick(R.id.vTFigureColor)
    void chooseColorFifth() {
        settingsPresenter.getEvent(R.id.vTFigureColor);
    }

    @OnClick(R.id.vJFigureColor)
    void chooseColorSixth() {
        settingsPresenter.getEvent(R.id.vJFigureColor);
    }

}
