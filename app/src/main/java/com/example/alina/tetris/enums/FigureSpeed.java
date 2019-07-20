package com.example.alina.tetris.enums;

import com.example.alina.tetris.R;

public enum FigureSpeed {

    VERY_FAST(R.id.tvVeryFast, 500),
    FAST(R.id.tvFast, 750),
    DEFAULT(R.id.tvDefault, 1000),
    SLOW(R.id.tvSlow, 1250),
    VERY_SLOW(R.id.tvVerySlow, 1500);

    private int speedItemId;
    private long figureSpeedInMillis;

    FigureSpeed(int speedItemId, long speedInMillis) {
        this.speedItemId = speedItemId;
        this.figureSpeedInMillis = speedInMillis;
    }

    public int getSpeedItemId() {
        return speedItemId;
    }

    public long getFigureSpeedInMillis() {
        return figureSpeedInMillis;
    }

}
