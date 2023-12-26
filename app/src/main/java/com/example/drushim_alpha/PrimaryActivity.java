package com.example.drushim_alpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PrimaryActivity extends AppCompatActivity {

    private EditText etPrimaryRTDB;
    BroadcastReceiver broadcastReceiver = null;
    BottomNavigationView BNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

        etPrimaryRTDB = findViewById(R.id.etPrimaryRTDB);
        BNV = findViewById(R.id.BottomNavigationPrimary);
        broadcastReceiver = new InternetReciver();

        internetStatus();
        BNV.setOnNavigationItemSelectedListener(item -> {
            int itemName = item.getItemId();
            if (itemName == R.id.primary)
            {
                startActivity(new Intent(this,PrimaryActivity.class));
            }
            if (itemName == R.id.user)
            {
                Toast.makeText(this, "Not Available", Toast.LENGTH_LONG).show();
            }
            if (itemName == R.id.notification)
            {
                startActivity(new Intent(this,NotificationActivity.class));
            }

            return super.onOptionsItemSelected(item);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_menu,menu);
        return super.onCreateOptionsMenu(menu);
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