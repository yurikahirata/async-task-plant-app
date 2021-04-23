package com.hirata.finalproject;

public class Plant {
    public String name;
    public boolean gameEnded;

    private int health;
    private int happiness;

    public Plant (String n) {
        name = n;
        health = 50;
        happiness = 50;
        gameEnded = false;
    }

    public int getHealth() {
        return health;
    }

    public void decrease() {
        health --;
        happiness--;
        if (health <=0 || happiness <= 0 ) {
            gameEnded = true;
        }
    }

    public int getHappiness() {
        return happiness;
    }

    public void addToHealth(int n) {
        if ((health+n) >= 50) {
            health = 50;
        } else if ((health+n) <= 0) {
            gameEnded = true;
            health = 0;
        }
        else {
            health = health + n;
        }
    }

    public void addToHappiness(int n) {
        if ((happiness+n) >= 50) {
            happiness = 50;
        } else if ((happiness+n) <= 0) {
            gameEnded = true;
            happiness = 0;
        }else {
            happiness = happiness + n;
        }
    }
}
