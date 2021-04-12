package com.hirata.finalproject;

public class Plant {
    public String name;

    private int sun;
    private int water;
    private int happiness;

    public static final int MAX = 30;

    public Plant (String n) {
        name = n;
        sun = MAX;
        water = MAX;
        happiness = MAX;
    }
}
