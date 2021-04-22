package com.example.projetcoachnutrition.Modele;

public class User {

    private int id;
    private String nom;
    private float poids;
    private int minCal;
    private int maxCal;
    private int taille;
    private int sexe;
    private int age;


    public User(int id,String nom, int age,float poids, int taille, int sexe, int minCal, int maxCal) {
        this.id = id;
        this.nom = nom;
        this.poids = poids;
        this.taille = taille;
        this.sexe = sexe;
        this.age = age;
        this.minCal = minCal;
        this.maxCal = maxCal;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public float getPoids() {
        return poids;
    }

    public int getMinCal() {
        return minCal;
    }

    public int getMaxCal() {
        return maxCal;
    }

    public int getAge() { return age; }

    public int getTaille() { return taille; }

    public int getSexe(){return sexe;}
}