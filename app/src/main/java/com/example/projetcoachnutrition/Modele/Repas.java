package com.example.projetcoachnutrition.Modele;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Repas {

    private int id;
    private Date date;
    private String Sdate;
    private double totalCalories = 0;
    private ArrayList<Aliment> lesAliments;
    private Integer[] enregId; //stocke les id des aliments
    private int selectQte;


    public Repas(int id, Date ladate, ArrayList<Aliment> lsAliment, int selectqte) {
        this.id = id;
        this.date = ladate;
        lesAliments = new ArrayList<>();
        this.lesAliments = lsAliment;
        this.selectQte = selectqte;

    }

    public Repas(int id, String ladate,float colories){
        this.id = id;
        this.Sdate = ladate;
        this.totalCalories = colories;
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
        /****************test*******************/
        for (Aliment a : lesAliments){
            Log.d("AFFICHE TOUTES LA LISTE", "******************ID"+a.getId()+"CALORIES"+a.getCalories());
        }

        int premierId = lesAliments.get(0).getId();
        for (Aliment unAliment : lesAliments){
            Log.d("TAG***************", "PREMIER ID "+ premierId + "IDactuelle"+unAliment.getId());
            if(premierId == unAliment.getId()){
                totalCalories = unAliment.getCalories()*selectQte;
                Log.d("PREMIERAJOUT","CALORIES"+unAliment.getCalories() + "qte"+selectQte+ "TOTAL"+totalCalories);
            }else{
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
    public Integer[] getAllId(){
        this.enregId = new Integer[lesAliments.size()];
        int i = 0;

        for (Aliment unAliment : lesAliments){
            enregId[i] = unAliment.getId();
            Log.d("REPAS ID", "**********************************************: "+enregId[i]);
            i++;
        }
        supprimer_doublon(enregId);
        return enregId;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setList(ArrayList<Aliment> lesaliment){
        this.lesAliments = lesaliment;
    }

    public String getSdate(){
        return  this.Sdate;
    }

    // Définition de la fonction de suppression des doublons
    public static Object[] supprimer_doublon(Object[] args)
    {
        List list = Arrays.asList(args);
        Set set = new HashSet(list);
        Object[] result = new Object[set.size()];
        set.toArray(result);
        return result;
    }

    public void setLesAliments(ArrayList<Aliment> lesAliments){

        this.lesAliments = lesAliments;
        Log.d("TAG", "*********setLesAliments:******** ");
    }

    public double getCalories(){
        return this.totalCalories;
    }
}
