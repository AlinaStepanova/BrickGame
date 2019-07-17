package com.example.alina.tetris.ui.settings;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.alina.tetris.R;
import com.example.alina.tetris.data.SharedPreferencesManager;
import com.example.alina.tetris.utils.Utils;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity implements SettingsView {

    @BindViews({R.id.vLFigureColor, R.id.vSquareFigureColor, R.id.vLongFigureColor,
            R.id.vZFigureColor, R.id.vTFigureColor, R.id.vJFigureColor})
    List<ImageView> colors;

    @BindView(R.id.clColorPicker)
    ConstraintLayout colorPicker;

    @BindView(R.id.number_picker)
    NumberPicker numberPicker;

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
