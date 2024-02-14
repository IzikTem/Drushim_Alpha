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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    BroadcastReceiver broadcastReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        broadcastReceiver = new InternetReciver();
        internetStatus();


    }


    public void internetStatus()
    {
        registerReceiver(broadcastReceiver , new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


    public void register(View view) {
        EditText editTextEmail = findViewById(R.id.etRegisterEmail);
        EditText editTextPassword = findViewById(R.id.etRegisterPassword);
        EditText editTextPasswordVer = findViewById(R.id.etRegisterPasswordVer);

        if (!editTextPasswordVer.getText().toString().equals(editTextPassword.getText().toString()))
        {
            Toast.makeText(RegisterActivity.this, "register failed, the passwords are not identical.", Toast.LENGTH_LONG).show();
        }


        else
        {
            try {
                mAuth.createUserWithEmailAndPassword(
                                editTextEmail.getText().toString(), editTextPassword.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    startActivity(new Intent(RegisterActivity.this , PrimaryActivity.class));
                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, "register failed", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
            catch (Exception e){
                Toast.makeText(RegisterActivity.this, "register failed", Toast.LENGTH_LONG).show();

            }
        }


    }


}