package com.example.drushim_alpha.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import com.example.drushim_alpha.InternetReciver;
import com.example.drushim_alpha.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BootActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    BroadcastReceiver broadcastReceiver = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
        mAuth = FirebaseAuth.getInstance();

        broadcastReceiver = new InternetReciver();
        internetStatus();
    }

    public void internetStatus()
    {
        registerReceiver(broadcastReceiver , new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(this, PrimaryActivity.class));
        }
    }

    public void GoToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void GoToRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}