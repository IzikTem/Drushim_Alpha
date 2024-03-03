package com.example.drushim_alpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    BroadcastReceiver broadcastReceiver = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
            startActivity(new Intent(this,PrimaryActivity.class));
        }
    }

//    public void register(View view) {
//        EditText editTextEmail = findViewById(R.id.etLoginEmail);
//        EditText editTextPassword = findViewById(R.id.etLoginPassword);
//        try {
//            mAuth.createUserWithEmailAndPassword(
//                            editTextEmail.getText().toString(), editTextPassword.getText().toString())
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()){
//                                startActivity(new Intent(LoginActivity.this , PrimaryActivity.class));
//                            }
//                            else {
//                                Toast.makeText(LoginActivity.this, "register failed", Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
//            }
//        catch (Exception e){
//            Toast.makeText(LoginActivity.this, "register failed", Toast.LENGTH_LONG).show();
//
//        }
//
//
//    }

    public void login(View view) {
        EditText editTextEmail = findViewById(R.id.etLoginEmail);
        EditText editTextPassword = findViewById(R.id.etLoginPassword);
            try {
                mAuth.signInWithEmailAndPassword(editTextEmail.getText().toString(), editTextPassword.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            startActivity(new Intent(LoginActivity.this, PrimaryActivity.class));
                                            finish();
                                        }
                                        else {
                                            Toast.makeText(LoginActivity.this, "login failed", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

            } catch (Exception e) {
                Toast.makeText(LoginActivity.this, "login failed", Toast.LENGTH_LONG).show();

            }
        }
    }
