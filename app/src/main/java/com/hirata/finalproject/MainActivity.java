package com.hirata.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText nameET;
    String plantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);

        nameET = (EditText) findViewById(R.id.editTextName);
    }

    public void startApp (View view) {
        plantName = nameET.getText().toString();

        Intent launchReport = new Intent(this, GameActivity.class); // Creating intent to display Game Activity
        launchReport.putExtra("plant", plantName); // Adding extra data to GameActivity

        startActivity(launchReport);

    }
}