package com.hirata.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    TextView happinessTV;
    TextView sunTV;
    TextView waterTV;
    TextView plantTV;
    String plantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        happinessTV = (TextView) findViewById(R.id.happinessHealth);
        sunTV = (TextView) findViewById(R.id.sunHealth);
        waterTV = (TextView) findViewById(R.id.waterHealth);
        plantTV = (TextView) findViewById(R.id.nameText);

        Intent intent = getIntent();
        plantName = intent.getStringExtra("plant");
        plantTV.setText(plantName);
    }
}
