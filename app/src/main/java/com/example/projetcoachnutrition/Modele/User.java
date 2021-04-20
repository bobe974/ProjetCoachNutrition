package com.example.projetcoachnutrition.Modele;

public class User {

    private int id;
    private String nom;
    private int poids;
    private int minCal;
    private int maxCal;
    private int age;
    private int taille;
    private int sexe;


    public User(int id, String nom, int poids, int minCal, int maxCal) {
        this.id = id;
        this.nom = nom;
        this.poids = poids;
        this.minCal = minCal;
        this.maxCal = maxCal;
    }

    public User(int id, String nom, int minCal, int maxCal) {
        this.id = id;
        this.nom = nom;
        this.poids = 0;
        this.minCal = minCal;
        this.maxCal = maxCal;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getPoids() {
        return poids;
    }

    public int getMinCal() {
        return minCal;
    }

    public int getMaxCal() {
        return maxCal;
    }
}
