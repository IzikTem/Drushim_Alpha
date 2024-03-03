package com.example.drushim_alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class UserDataActivity extends AppCompatActivity {

    private EditText etUserDataActivityDate;
    private DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        etUserDataActivityDate = findViewById(R.id.etUserDataDate);
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
}