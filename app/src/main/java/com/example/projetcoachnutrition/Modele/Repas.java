package com.example.projetcoachnutrition.Modele;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Repas {

    private int id;
    private String date;
    private double totalCalories;
    private ArrayList<Aliment> lesAliments;
    private int[] enregId; //stocke les id des aliments


    public Repas(int id, Date ladate, ArrayList<Aliment> lsAliment) {
        this.id = id;
        this.date = date;
        lesAliments = new ArrayList<>();
        this.lesAliments = lsAliment;

    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    /**
     * parcours la liste et retourne la somme des calories
     * @return
     */
    public double getTotalCalories() {

        for (Aliment unAliment : lesAliments){
            totalCalories = totalCalories+ unAliment.getCalories();
        }
        return totalCalories;
    }

    /**
     * retourne les id de tous les aliments du repas
     * @return
      */
    public int[] getAllId(){
        this.enregId = new int[lesAliments.size()];
        int i = 0;

        for (Aliment unAliment : lesAliments){
            enregId[i] = unAliment.getId();
            Log.d("REPAS ID", "**********************************************: "+enregId[i]);
            i++;
        }

        return enregId;
    }
}
