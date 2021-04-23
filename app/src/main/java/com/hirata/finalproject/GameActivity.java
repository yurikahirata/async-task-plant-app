package com.hirata.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    TextView happinessTV;
    TextView healthTV;
    TextView plantTV;
    String plantName;

    Plant mPlant;

    HealthAsyncTask mHealthAsyncTask;

    String[] compliments = {"You're looking extra green today!", "Good job growing!", "You're my favorite plant!", "You're the best plant I've ever had!", "You're such a good plant!", "I hope you grow strong!"};
    String[] insults = {"I bet you don't even grow flowers.", "You're an ugly plant.", "I should've bought a different plant.", "My other plants are better.", "You're my least favorite plant.", "You were overpriced."};

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
                   mPlant.healthDecrease(1);
                   mPlant.happinessDecrease(1);
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

    public void water (View view) {
        mPlant.addToHealth(5);
        healthTV.setText(" " + mPlant.getHealth());
    }

    public void nutrients (View view) {
        mPlant.addToHealth(7);
        healthTV.setText(" " + mPlant.getHealth());
    }

    public void compliment (View view) {
        mPlant.addToHealth(3);
        mPlant.addToHappiness(10);
        healthTV.setText(" " + mPlant.getHealth());
        happinessTV.setText(" " + mPlant.getHappiness());
    }

    public void insult (View view) {
        mPlant.healthDecrease(5);
        mPlant.happinessDecrease(12);
        healthTV.setText(" " + mPlant.getHealth());
        happinessTV.setText(" " + mPlant.getHappiness());
    }
}
