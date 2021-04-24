package com.example.projetcoachnutrition.Modele;

public class User {



    private int id;
    private String nom;
    private float poids;
    private int taille;
    private int sexe;
    private int age;
    private float img;


    public User(int id,String nom, int age,float poids, int taille, int sexe) {
        this.id = id;
        this.nom = nom;
        this.poids = poids;
        this.taille = taille;
        this.sexe = sexe;
        this.age = age;
        calculIMG();

    }

    /**calcule l'indice de masse graisseuse
     *
     */
    private void calculIMG(){
        //taille en metre
        float tailleM = ((float) taille/100);
        this.img = (float)((1.2 * poids / (tailleM*tailleM)) + (0.23*age) - (10.83*sexe) - 5.4);
    }


    public void setId(int unid){ this.id = unid;}

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

    public float getImg() {
        return img;
    }
}