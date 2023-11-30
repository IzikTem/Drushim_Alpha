package com.example.drushim_alpha;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class AlarmReciver extends BroadcastReceiver  {


    @Override
    public void onReceive(Context context, Intent intent) {
        String etNotiWrite = intent.getStringExtra("notification");
        Log.d("checkself", etNotiWrite);
        NotificationHelper.showNotificationBtn(context,etNotiWrite);
        Toast.makeText(context,"Notification pushed successfully",Toast.LENGTH_SHORT).show();

    }
}
