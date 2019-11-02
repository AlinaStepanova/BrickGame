package com.avs.brick.game.ui.settings;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.avs.brick.game.R;
import com.avs.brick.game.Values;
import com.avs.brick.game.data.SharedPreferencesManager;
import com.avs.brick.game.utils.Utils;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity implements SettingsView {

    @BindViews({R.id.tvVeryFast, R.id.tvFast, R.id.tvDefault, R.id.tvSlow, R.id.tvVerySlow})
    List<TextView> speedItems;

    @BindView(R.id.clColorPicker)
    ConstraintLayout colorPicker;

    @BindView(R.id.squaresCountNumberPicker)
    NumberPicker squaresNumberPicker;

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
    public void setSpeedTitle(int newItemId) {
        for (TextView item: speedItems) {
            item.getBackground().setTint(getResources().getColor(R.color.white));
            item.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
        TextView newItem = findViewById(newItemId);
        newItem.getBackground().setTint(getResources().getColor(R.color.colorPrimary));
        newItem.setTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public void setVerticalHintsChecked(boolean hintsEnabled) {
        enableHintsSwitch.setChecked(hintsEnabled);
    }

    @Override
    public void setSquaresCountInRow(int squaresCountInRow) {
        if (squaresNumberPicker != null) squaresNumberPicker.setValue(squaresCountInRow);
    }

    @OnClick(R.id.flPrivacyPolicy)
    void openPrivacyPolicy() {
        startActivity(new Intent(this, PrivacyPolicyActivity.class));
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
            Toast.makeText(this, getResources().getString(R.string.cannot_send_email_error_text), Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.flRate)
    void rateApp() {
        try {
            startActivity(Utils.openMarket(this));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, getResources().getString(R.string.cannot_open_market_error_text), Toast.LENGTH_LONG).show();
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

    @OnClick(R.id.tvVerySlow)
    void chooseVerySlowSpeed() {
        settingsPresenter.getEvent(R.id.tvVerySlow);
    }

    @OnClick(R.id.tvSlow)
    void chooseSlowSpeed() {
        settingsPresenter.getEvent(R.id.tvSlow);
    }

    @OnClick(R.id.tvDefault)
    void chooseDefaultSpeed() {
        settingsPresenter.getEvent(R.id.tvDefault);
    }

    @OnClick(R.id.tvFast)
    void chooseFastSpeed() {
        settingsPresenter.getEvent(R.id.tvFast);
    }

    @OnClick(R.id.tvVeryFast)
    void chooseVeryFastSpeed() {
        settingsPresenter.getEvent(R.id.tvVeryFast);
    }

}
