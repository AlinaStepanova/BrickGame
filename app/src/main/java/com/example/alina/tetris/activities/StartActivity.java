package com.example.alina.tetris.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alina.tetris.R;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initButtons();
    }

    private void initButtons() {
        Button openMainActivityButton = (Button) findViewById(R.id.bStartGame);
        openMainActivityButton.setOnClickListener(this);
        Button openScoreActivityButton = (Button) findViewById(R.id.bOpenScores);
        openScoreActivityButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bStartGame:
                this.startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.bOpenScores:
                this.startActivity(new Intent(this, ScoreActivity.class));
                break;
            default:
                break;
        }
    }
}
