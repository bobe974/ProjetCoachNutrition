package com.example.projetcoachnutrition.Bdd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetcoachnutrition.Modele.Aliment;

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
        String req = "insert into food (idFood, food, estimatedCalories) values";
        req += "(\""+unaliment.getId()+"\",\""+unaliment.getName()+"\",\""+unaliment.getCalories()+"\")";
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
