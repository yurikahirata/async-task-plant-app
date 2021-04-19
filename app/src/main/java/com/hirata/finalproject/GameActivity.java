package com.hirata.finalproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    TextView happinessTV;
    TextView healthTV;
    TextView plantTV;
    String plantName;

    Plant mPlant;

    private HealthAsyncTask mHealthAsyncTask;
    //private HappinessAsyncTask mHappyAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        happinessTV = (TextView) findViewById(R.id.happinessHealth);
        healthTV = (TextView) findViewById(R.id.health);
        plantTV = (TextView) findViewById(R.id.nameText);

        Intent intent = getIntent();
        plantName = intent.getStringExtra("plant");
        plantTV.setText(plantName);
        mPlant = new Plant(plantName);

        mHealthAsyncTask = new HealthAsyncTask();
        mHealthAsyncTask.execute(mPlant.getHealth());

    }

    private class HealthAsyncTask extends AsyncTask<Integer, Integer, Integer> {
        protected Integer doInBackground(Integer... values) {
            try {
               while (!mPlant.gameEnded) {
                   Thread.sleep(1000);
                   mPlant.healthDecrease();
                   publishProgress(mPlant.getHealth());
               }
            } catch (InterruptedException e) {}
            return mPlant.getHealth();
        }

        protected void onProgressUpdate (Integer... values) {
            super.onProgressUpdate(values);
            healthTV.setText(" " + values[0]);
        }
    }


}
