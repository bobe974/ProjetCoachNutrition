package com.example.projetcoachnutrition.Modele;

public class Aliment {

    private int id;
    private String nom;
    private int calories;

    /**
     *
     * @param id
     * @param name
     * @param calories
     */
    public Aliment(int id, String name, int calories) {
        this.id = id;
        this.nom = name;
        this.calories = calories;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return nom;
    }

    public void setName(String nom) {
        this.nom = nom;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

}

