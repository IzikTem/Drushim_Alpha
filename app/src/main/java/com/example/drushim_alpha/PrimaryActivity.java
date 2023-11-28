package com.example.drushim_alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PrimaryActivity extends AppCompatActivity {

    private EditText etPrimaryRTDB;
    BroadcastReceiver broadcastReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);
        etPrimaryRTDB = findViewById(R.id.etPrimaryRTDB);

        broadcastReceiver = new InternetReciver();
        internetStatus();

    }


    public void internetStatus()
    {
        registerReceiver(broadcastReceiver , new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }


    public void saveRTDB(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://drushim-alpha-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("text");

        myRef.setValue(etPrimaryRTDB.getText().toString());
    }

    public void goToNotification(View view) {
        startActivity(new Intent(this,NotificationActivity.class));
    }
}