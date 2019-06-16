package com.example.alina.tetris.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.RemoteViews;

import com.example.alina.tetris.R;
import com.example.alina.tetris.activities.ScoreActivity;

import static com.example.alina.tetris.Values.CHANNEL_ID;
import static com.example.alina.tetris.Values.NOTIFICATION_ID;

public class NotificationUtil {

    private final Context context;
    private PendingIntent pendingIntent;
    private final int score;

    public NotificationUtil(Context context, int newScore) {
        this.context = context;
        this.score = newScore;
        initIntent(context);
    }

    private void initIntent(Context context) {
        Intent intent = new Intent(context, ScoreActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
    }

    private Notification getNotificationBuilder() {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.notification_custom_view);
        views.setTextViewText(R.id.tvAlarmReason, context.getString(R.string.congrats_sub_title)
                + " - " + score + " " + context.getString(R.string.points_notification_text));
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_star)
                .setContentTitle(context.getString(R.string.new_score_notification_title))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setContent(views)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            builder.setColor(context.getColor(R.color.colorPrimary));
        }
        return builder.build();
    }

    public void createNotification() {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID, getNotificationBuilder());
    }
}
