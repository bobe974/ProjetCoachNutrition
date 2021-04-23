package com.example.projetcoachnutrition.Modele;

public class User {

    private int id;
    private String nom;
    private float poids;
    private int taille;
    private int sexe;
    private int age;


    public User(int id,String nom, int age,float poids, int taille, int sexe) {
        this.id = id;
        this.nom = nom;
        this.poids = poids;
        this.taille = taille;
        this.sexe = sexe;
        this.age = age;

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

    public int getAge() { return age; }

    public int getTaille() { return taille; }

    public int getSexe(){return sexe;}
}