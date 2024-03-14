package com.example.drushim_alpha.activities;

import static com.example.drushim_alpha.FBref.refUsers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drushim_alpha.InternetReciver;
import com.example.drushim_alpha.R;
import com.example.drushim_alpha.User;
import com.example.drushim_alpha.activities.BootActivity;
import com.example.drushim_alpha.activities.NotificationActivity;
import com.example.drushim_alpha.activities.PrimaryActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class UserActivity extends AppCompatActivity {


    BroadcastReceiver broadcastReceiver = null;
    BottomNavigationView BNV;
    TextView tvUserName;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mAuth = FirebaseAuth.getInstance();



        BNV = findViewById(R.id.BottomNavigationPrimary);
        tvUserName = findViewById(R.id.tvUserUsername);
        broadcastReceiver = new InternetReciver();



        internetStatus();
        buildMenu();

        // Read from the database

        FirebaseUser currentUser = mAuth.getCurrentUser();
        String uid = currentUser.getUid();
        refUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren())
                {
                    User user = userSnapshot.getValue(User.class);
                    if (user.getUid().equals(uid))
                    {
                        tvUserName.setText(user.getName().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserActivity.this, "Failed to read value.", Toast.LENGTH_LONG).show();

            }
        });

    }
    public void internetStatus()
    {
        registerReceiver(broadcastReceiver , new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, BootActivity.class));
        finish();
    }




    public void buildMenu()
    {
        BNV.setOnNavigationItemSelectedListener(item -> {
            int itemName = item.getItemId();
            if (itemName == R.id.primary)
            {
                startActivity(new Intent(this, PrimaryActivity.class));
            }
            if (itemName == R.id.user)
            {
                Toast.makeText(this, "Current Screen", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(this,UserActivity.class));
            }
            if (itemName == R.id.notification)
            {
                startActivity(new Intent(this, NotificationActivity.class));
            }

            return super.onOptionsItemSelected(item);
        });

    }
}