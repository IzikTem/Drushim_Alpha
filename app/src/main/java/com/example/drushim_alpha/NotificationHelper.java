package com.example.drushim_alpha;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper {


    private static final String CHANNEL_ID = "Your_Channel_ID";
    private static final String CHANNEL_NAME = "Your_Channel_Name";
    private static final int NOTIFICATION_ID = 1;


    public static void showNotificationBtn(Context context, String text) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        Intent intent = new Intent(context, NotificationActivity.class);
        intent.putExtra("notification_clicked", true);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Drushim")
                .setContentText(text)
                .setPriority(notificationManager.IMPORTANCE_MAX)

                .addAction(android.R.drawable.ic_menu_close_clear_cancel,
                        "OK", pendingIntent)
                .setAutoCancel(true);
        notificationManager.notify(NOTIFICATION_ID, builder.build());


    }
}

