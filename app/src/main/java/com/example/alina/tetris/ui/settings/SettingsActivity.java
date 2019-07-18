package com.example.alina.tetris.ui.settings;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alina.tetris.R;
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

    @BindView(R.id.numberPicker)
    NumberPicker numberPicker;

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
        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> settingsPresenter.setFigureSpeed(newVal));
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
        if (numberPicker != null) numberPicker.setValue(newValue);
    }

    @Override
    public void setSpeedTitle(String figureSpeedTitle) {
        speedTitle.setText(figureSpeedTitle);
    }

    @Override
    public void setVerticalHintsChecked(boolean hintsEnabled) {
        enableHintsSwitch.setChecked(hintsEnabled);
    }

    @OnClick(R.id.sEnableHints)
    void enableHints() {
        settingsPresenter.getEvent(R.id.sEnableHints);
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
