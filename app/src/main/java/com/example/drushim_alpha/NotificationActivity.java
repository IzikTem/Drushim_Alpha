package com.example.drushim_alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity {


    BroadcastReceiver broadcastReceiver = null;
    private MaterialTimePicker picker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private EditText etNotificationWrite;
    public static NotificationActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        broadcastReceiver = new InternetReciver();
        etNotificationWrite = findViewById(R.id.etNotificationWrite);
        internetStatus();
        instance = this;
    }


    public void internetStatus()
    {
        registerReceiver(broadcastReceiver , new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void showNotification(View view)
    {
        EditText etNotiWrite = findViewById(R.id.etNotificationWrite);
        NotificationHelper.showNotificationBtn(this,etNotiWrite.getText().toString());

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    public void selectTime(View view) {
        showTimePicker();



    }

    private void setAlarm() {
        Intent intent = new Intent(this, AlarmReciver.class);
        intent.putExtra("notification" , etNotificationWrite.getText().toString());
        pendingIntent = PendingIntent.getBroadcast(this,0,intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC,calendar.getTimeInMillis(),pendingIntent);
        Toast.makeText(this,"Notification set successfully",Toast.LENGTH_SHORT).show();
    }

    private void showTimePicker() {
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Choose Time For Notification :")
                .build();



        picker.show(getSupportFragmentManager(), "Time Picker");

        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
                calendar.set(Calendar.MINUTE,picker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
                setAlarm();


            }
        });
    }
}