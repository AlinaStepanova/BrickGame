package com.avs.brick.game;

import android.app.Application;
import android.app.NotificationManager;
import android.os.Build;

import com.avs.brick.game.utils.NotificationUtil;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;
import static com.avs.brick.game.Values.CHANNEL_NAME;
import static com.avs.brick.game.Values.SCORE_CHANNEL;

public class BrickGameApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {
            final NotificationManager nm = (NotificationManager) getSystemService( NOTIFICATION_SERVICE );
            if( nm != null ) {
                NotificationUtil.createChannel(nm, SCORE_CHANNEL, CHANNEL_NAME, IMPORTANCE_DEFAULT);
            }
        }
    }
}
