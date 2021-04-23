package com.hirata.finalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView happinessTV;
    TextView healthTV;
    TextView plantTV;
    String plantName;
    TextView textTV;
    Button nutrientBtn;
    Button waterBtn;
    Button insultBtn;
    Button complimentBtn;

    Plant mPlant;

    HealthAsyncTask mHealthAsyncTask;

    String[] compliments = {"You're looking extra green today!", "Good job growing!", "You're my favorite plant!", "You're the best plant I've ever had!", "You're such a good plant!", "I hope you grow strong!", "No other plant can compare to you!"};
    String[] insults = {"I bet you don't even grow flowers.", "You're an ugly plant.", "I should've bought a different plant.", "My other plants are better.", "You're my least favorite plant.", "You were overpriced.", "I don't like you."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        happinessTV = (TextView) findViewById(R.id.happinessHealth);
        healthTV = (TextView) findViewById(R.id.health);
        plantTV = (TextView) findViewById(R.id.nameText);
        textTV = (TextView) findViewById(R.id.textView2);

        nutrientBtn = (Button) findViewById(R.id.nutrientButton);
        waterBtn = (Button) findViewById(R.id.waterButton);
        insultBtn = (Button) findViewById(R.id.insultButton);
        complimentBtn = (Button) findViewById(R.id.complimentButton);

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
               while (!mPlant.gameEnded) { // If game is not over, decreasing health and happiness
                   Thread.sleep(1000);
                   mPlant.decrease();
                   result = new ArrayList<Integer>();
                   result.add(mPlant.getHealth());
                   result.add(mPlant.getHappiness());
                   publishProgress(result);
               }
                if (mPlant.gameEnded) {
                    endGame();
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

    public void action (View view) {
        int increaseHealth;
        int increaseHappiness;
        int rnd = new Random().nextInt(insults.length);
        int color;
        String finalText;
        String actionTag = view.getTag().toString();
        switch (actionTag) { // Assigning variables based on tag
            case "insult":
                increaseHealth = -7;
                increaseHappiness = -12;
                finalText = insults[rnd] + "\n-12 Happiness   -7 Health";
                color = Color.parseColor("#F44336"); break;
            case "compliment":
                increaseHealth = 5;
                increaseHappiness = 9;
                finalText  = compliments[rnd] + "\n+9 Happiness   +5 Health";
                color = Color.parseColor("#8BC34A");break;
            case "nutrients":
                increaseHealth = 7;
                increaseHappiness = 0;
                finalText = "+7 Health";
                color = Color.parseColor("#8BC34A");break;
            default:
                increaseHealth = 5;
                increaseHappiness = 0;
                finalText = "+5 Health";
                color = Color.parseColor("#8BC34A"); break;
        }

        mPlant.addToHappiness(increaseHappiness);
        mPlant.addToHealth(increaseHealth);

        new CountDownTimer(2000, 1000) { // Timer for text to appear
            public void onTick(long millisUntilFinished) {
                textTV.setTextColor(color);
                textTV.setText(finalText);
                insultBtn.setEnabled(false); // Disable all buttons while one is clicked
                complimentBtn.setEnabled(false);
                nutrientBtn.setEnabled(false);
                waterBtn.setEnabled(false);
            }

            public void onFinish() {
                textTV.setText(" ");
                insultBtn.setEnabled(true); // Re-enable buttons
                complimentBtn.setEnabled(true);
                nutrientBtn.setEnabled(true);
                waterBtn.setEnabled(true);
            }
        }.start();

    }

    public void endGame() { // If game over return to start page
        Intent launchReport = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(launchReport);
    }
}

