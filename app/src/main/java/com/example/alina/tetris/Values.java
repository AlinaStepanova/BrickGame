package com.example.alina.tetris;


public class Values {

    public static final int EXTRA_ROWS                      = 4;
    public static final int SQUARE_COUNT_HORIZONTAL         = 10;
    public static final int FIGURE_STOPPED_SCORE            = 10;
    public static final int EXTRA_SCORE                     = 100;
    public static final int COUNT_DOWN_INTERVAL             = 750;
    public static final int MILLIS_IN_FUTURE                = 1000;
    public static final long DELAY_IN_MILLIS                = 1500;
    public static final long GAME_OVER_DELAY_IN_MILLIS      = 4000;
    public static final float LINE_WIDTH                    = 1f;
    public static final String GAME_OVER_TEXT               = "GAME OVER";

    /*PREFERENCES*/
    public static final String PREFERENCES_KEY      = "com.example.alina.tetris.PREFERENCE_KEY";
    public static final String FIRST_VALUE_KEY      = "first_value";
    public static final String SECOND_VALUE_KEY     = "second_value";
    public static final String THIRD_VALUE_KEY      = "third_value";
    public static final int DEFAULT_VALUE           = 0;
    public static final String FIGURE_COLOR_KEY     = "default_color";
    public static final int DEFAULT_COLOR           = R.color.zFigure;

    /*NOTIFICATIONS*/
    public static final int NOTIFICATION_ID         = 123;
    public static final String CHANNEL_ID           = "score_notification_channel";
}
