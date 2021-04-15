package com.example.projetcoachnutrition.Bdd;

public class Repas {

    private int id;
    private String date;
    private double totalCalories;


    public Repas(int id, String date, double calories) {
        this.id = id;
        this.date = date;
        this.totalCalories = calories;

    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public double getTotalCalories() {
        return totalCalories;
    }
}
