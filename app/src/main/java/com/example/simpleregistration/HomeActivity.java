package com.example.simpleregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private TextView info_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        info_tv = findViewById(R.id.info_tv);

        Intent intent = getIntent();
        User user = intent.getExtras().getParcelable("user");

        Log.d(HomeActivity.class.getSimpleName(), user.getGender());
        String setText = "Welcome " + user.getFirstName() + " from " + user.getCountry() +
                "\nGender: " + user.getGender();
        info_tv.setText(setText);
    }
}