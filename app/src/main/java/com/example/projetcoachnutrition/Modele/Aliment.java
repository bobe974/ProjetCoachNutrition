package com.example.projetcoachnutrition.Modele;

public class Aliment {

    private int id;
    private String nom;
    private int calories;
    private int qte;

    /**
     *
     * @param id
     * @param name
     * @param calories
     */
    public Aliment(int id, String name, int calories,int qte) {
        this.id = id;
        this.nom = name;
        this.calories = calories;
        this.qte = qte;

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

    public int getQte(){ return qte;}

    public void setQte(int qte){ this.qte = qte;}
}

