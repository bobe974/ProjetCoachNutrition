package com.example.projetcoachnutrition.Modele;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

public class Repas {

    private int id;
    private Date date;
    private double totalCalories = 0;
    private LinkedHashSet<Aliment> lesAliments;
    private int[] enregId; //stocke les id des aliments
    private int selectQte;


    public Repas(int id, Date ladate, LinkedHashSet lsAliment, int selectqte) {
        this.id = id;
        this.date = ladate;
        lesAliments = new LinkedHashSet<>();
        this.lesAliments = lsAliment;
        this.selectQte = selectqte;

    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    /**
     * parcours la liste et retourne la somme des calories
     * @return
     */
    public double getTotalCalories() {

        for (Aliment unAliment : lesAliments){
            String id = unAliment.getName();
            if(!lesAliments.contains(id)){
                totalCalories = totalCalories + (unAliment.getCalories() * selectQte);
                Log.d("BOUCLE TOTALCALORIES", "*********************");
                Log.d("CALCUL calories", "*****************CALORIE******" + "caloriealiment:" + unAliment.getCalories() +
                        "quantité" + selectQte + "total" + totalCalories
                );
            }
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

    public void setId(int id){
        this.id = id;
    }
}
