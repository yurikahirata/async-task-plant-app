package com.hirata.finalproject;

public class Plant {
    public String name;
    public boolean gameEnded;

    private int health;
    public int happiness;

    public Plant (String n) {
        name = n;
        health = 100;
        happiness = 100;
        gameEnded = false;
    }

    public int getHealth() {
        return health;
    }

    public void healthDecrease() {
        health--;
        if (health <= 0) {
            gameEnded = true;
        }
    }

}
