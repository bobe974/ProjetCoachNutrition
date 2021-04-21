package com.example.projetcoachnutrition.Bdd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetcoachnutrition.Modele.Aliment;
import com.example.projetcoachnutrition.Modele.User;

import java.util.Date;

import static android.content.ContentValues.TAG;

public class AccesLocal {

    //propriétés
    private String nomBase = "bddCoachSante.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;


    /**constructeur
     *
     * @param contexte
     */
    public AccesLocal (Context contexte){
        accesBD = new MySQLiteOpenHelper(contexte, this.nomBase, null, this.versionBase);
    }

    /**
     * ajoute un aliment dans la bdd
     * @param unaliment
     */
    public void ajoutAliment(Aliment unaliment){
        bd = accesBD.getWritableDatabase();
        String req = "insert into food (food, estimatedCalories) values";
        req += "(\""+unaliment.getName()+"\",\""+unaliment.getCalories()+"\")";

        //executer la requete
        bd.execSQL(req);

        Log.d(TAG, "ajout:**************************************** "+ req);


        int lastIdFood = 0; // Init lastIdFood à 0
        String query = "SELECT idFood from food order by idFood DESC limit 1"; // Requête pour récup
        Cursor c = bd.rawQuery(query, null); // On place le curseur
        if (c != null && c.moveToFirst()) {
            lastIdFood = c.getInt(0); //Le 0 est l'index de la colonne, nous n'avons qu'une colonne, donc l'index est 0
        }

        Log.d(TAG, "lastidfood:**************************************** "+ lastIdFood);
        unaliment.setId(lastIdFood);


    }

    public void ajoutUser(User unuser){
        bd = accesBD.getWritableDatabase();
        String req = "insert into user(nom,age,poids,taille,sexe,minCal,maxCal) values";
        req += "(\""+unuser.getNom()+"\",\""+unuser.getAge()+"\",\""+unuser.getPoids()+
                "\",\""+unuser.getTaille()+"\",\""+unuser.getSexe()+"\",\""+unuser.getMinCal()+
                "\",\""+unuser.getMaxCal()+"\")";
        Log.d(TAG, "ajout:**************************************** "+ req);
        //executer la requete
        bd.execSQL(req);
    }

    /**
     * ajout d'un profil dans la bdd
     * @param profil
     */   /*
    public void ajout(Profil profil){
        bd = accesBD.getWritableDatabase();
        String req = "insert into profil(datemesure, poids, taille, age, sexe) values";
        req += "(\""+profil.getDateMesure()+"\",\""+profil.getPoids()+"\",\""+profil.getTaille()+"\",\""+profil.getAge()+"\",\""+profil.getSexe()+"\")";
        Log.d(TAG, "ajout:**************************************** "+ req);
        //executer la requete
        bd.execSQL(req);
    } */

    /**
     * recupere le dernier profil de la bdd
     * @return
     */ /*
    public Profil recupDernier(){
        bd = accesBD.getReadableDatabase();
        Profil profil = null;
        String req = "select * from profil";
        Cursor curseur = bd.rawQuery(req, null);  //lit sur un select * ligne pas ligne
        curseur.moveToLast(); //pointe sur le dernier
        if (!curseur.isAfterLast()){
            Date date  = new Date();
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe  = curseur.getInt(4);

            profil = new Profil(poids, age,taille, sexe, date); //créer un profil avec les données recupérés

        }
        curseur.close();
        return profil;
    }*/
}
