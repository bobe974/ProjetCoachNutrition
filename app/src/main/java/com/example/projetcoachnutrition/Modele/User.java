package com.example.projetcoachnutrition.Modele;

public class User {

    //constantes tranche img
    private static final Integer minFemme = 15; //maigre si en dessous
    private static final Integer maxFemme = 30; //gros si au dessus
    private static final Integer minHomme = 10; //maigre si en dessous
    private static final Integer maxHomme = 25; //gros si au dessus

    private int id;
    private String nom;
    private float poids;
    private int taille;
    private int sexe;
    private int age;
    private float img;
    private String message;


    public User(int id,String nom, int age,float poids, int taille, int sexe) {
        this.id = id;
        this.nom = nom;
        this.poids = poids;
        this.taille = taille;
        this.sexe = sexe;
        this.age = age;
        calculIMG();
        resultIMG();

    }

    /**calcule l'indice de masse graisseuse
     *
     */
    private void calculIMG(){
        //taille en metre
        float tailleM = ((float) taille/100);
        this.img = (float)((1.2 * poids / (tailleM*tailleM)) + (0.23*age) - (10.83*sexe) - 5.4);
    }

    /**
     * determine si l'imc est normal, trop eleve ou trop faible
     */
    private void resultIMG(){
        Integer min;
        Integer max;

        if(sexe == 0) {// est une femme
            min = minFemme;
            max = maxFemme;
        }else{
            min = minHomme;
            max = maxHomme;
        }
        //message correspondant
        message = "normal";

        if (img > max){
            message = "trop élevé";


        }else{
            if (img < min){
                message = "trop faible";
            }
        }
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

    public float getImg() { return img; }

    public String getMessage(){ return message; }
}