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


    /**
     *
     * @param id
     * @param nom
     * @param poids
     * @param minCal
     * @param maxCal
     * @param taille
     * @param sexe
     * @param age
     */
    public User(int id, String nom, float poids, int minCal, int maxCal, int taille, int sexe, int age) {
        this.id = id;
        this.nom = nom;
        this.poids = poids;
        this.minCal = minCal;
        this.maxCal = maxCal;
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
