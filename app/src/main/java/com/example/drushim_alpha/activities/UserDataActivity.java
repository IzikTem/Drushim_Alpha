package com.example.drushim_alpha.activities;

import static com.example.drushim_alpha.FBref.refUsers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drushim_alpha.R;
import com.example.drushim_alpha.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class UserDataActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    private EditText etUserDataActivityDate;
    private EditText etUserDataActivityName;
    private EditText etUserDataActivityPhone;
    private EditText etUserDataActivityExperience;


    private DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        mAuth = FirebaseAuth.getInstance();

        etUserDataActivityDate = findViewById(R.id.etUserDataDate);
        etUserDataActivityName = findViewById(R.id.etUserDataName);
        etUserDataActivityPhone = findViewById(R.id.etUserDataPhone);
        etUserDataActivityExperience = findViewById(R.id.etUserDataExp);

    }

    public void click(View view)
    {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        picker = new DatePickerDialog(UserDataActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                etUserDataActivityDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year , month , day);

        picker.show();
    }

    public void register(View view)
    {
        try {


            FirebaseUser currentUser = mAuth.getCurrentUser();
            String date = etUserDataActivityDate.getText().toString();
            String name = etUserDataActivityName.getText().toString();
            String experience = etUserDataActivityExperience.getText().toString();
            String phone = etUserDataActivityPhone.getText().toString();

            User user = new User(currentUser.getUid(), name, currentUser.getEmail(), phone, experience, date);
            refUsers.child(currentUser.getUid()).setValue(user);

            startActivity(new Intent(UserDataActivity.this , PrimaryActivity.class));

        }

        catch (Exception e)
        {
            Toast.makeText(UserDataActivity.this, "login failed", Toast.LENGTH_LONG).show();

        }
    }
}