package com.example.drushim_alpha;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class InternetReciver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent) {
        String status = CheckInternet.getNetworkInfo(context);
        if (status == "connected")
        {
            Toast.makeText(context.getApplicationContext() , "You are connected" , Toast.LENGTH_LONG).show();
        }

        else
        {
            Toast.makeText(context.getApplicationContext() , "You have lost connection to the internet" , Toast.LENGTH_LONG).show();
        }
    }
}
