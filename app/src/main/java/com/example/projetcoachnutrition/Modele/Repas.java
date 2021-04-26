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
        lesAliments = new ArrayList<>();
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
        /**test**/
        for (Aliment a : lesAliments){
            Log.d("AFFICHE TOUTES LA LISTE", "**ID"+a.getId()+"CALORIES"+a.getCalories());
        }

        int premierId = lesAliments.get(0).getId();
        for (Aliment unAliment : lesAliments){
            Log.d("TAG**", "PREMIER ID "+ premierId + "IDactuelle"+unAliment.getId());
            if(premierId == unAliment.getId()){
                totalCalories = unAliment.getCalories()*selectQte;
                Log.d("PREMIERAJOUT","CALORIES"+unAliment.getCalories() + "qte"+selectQte+ "TOTAL"+totalCalories);
            }else{
                totalCalories = totalCalories + (unAliment.getCalories() * selectQte);
                Log.d("BOUCLE TOTALCALORIES", "**");
                Log.d("CALCUL calories", "**CALORIE**" + "caloriealiment:" + unAliment.getCalories() +
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
            //Log.d("REPAS ID", "**********************************************: "+enregId[i]);
            i++;
        }

        return enregId;
    }

    public void setId(int id){
        this.id = id;
    }
}
