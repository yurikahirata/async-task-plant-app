package com.hirata.finalproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    TextView happinessTV;
    TextView healthTV;
    TextView plantTV;
    String plantName;

    Plant mPlant;

    private HealthAsyncTask mHealthAsyncTask;

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
       mHealthAsyncTask.execute();

    }

    private class HealthAsyncTask extends AsyncTask<ArrayList <Integer>, ArrayList <Integer>, ArrayList <Integer>> {
        protected ArrayList <Integer> doInBackground(ArrayList <Integer>... values) {
            ArrayList <Integer> result = null;
            try {
               while (!mPlant.gameEnded) {
                   Thread.sleep(1000);
                   mPlant.healthDecrease();
                   mPlant.happinessDecrease();
                   result = new ArrayList<Integer>();
                   result.add(mPlant.getHealth());
                   result.add(mPlant.getHappiness());
                   publishProgress(result);
               }
            } catch (InterruptedException e) {
            }
            return result;
        }

        protected void onProgressUpdate (ArrayList <Integer>... values) {
            super.onProgressUpdate(values);
            ArrayList<Integer> passing = values[0];
            healthTV.setText(" " + passing.get(0));
            happinessTV.setText(" " + passing.get(1));

        }
    }


}
