package com.example.drushim_alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserActivity extends AppCompatActivity {


    BroadcastReceiver broadcastReceiver = null;
    BottomNavigationView BNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        BNV = findViewById(R.id.BottomNavigationPrimary);
        broadcastReceiver = new InternetReciver();

        internetStatus();
        buildMenu();
    }
    public void internetStatus()
    {
        registerReceiver(broadcastReceiver , new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


    public void buildMenu()
    {
        BNV.setOnNavigationItemSelectedListener(item -> {
            int itemName = item.getItemId();
            if (itemName == R.id.primary)
            {
                startActivity(new Intent(this,PrimaryActivity.class));
            }
            if (itemName == R.id.user)
            {
//                Toast.makeText(this, "Not Available", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,UserActivity.class));
            }
            if (itemName == R.id.notification)
            {
                startActivity(new Intent(this,NotificationActivity.class));
            }

            return super.onOptionsItemSelected(item);
        });

    }
}