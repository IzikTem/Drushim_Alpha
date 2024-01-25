package com.example.drushim_alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
    }

    public void GoToLogin(View view) {
        startActivity(new Intent(this,LoginActivity.class));
    }

    public void GoToRegister(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }
}