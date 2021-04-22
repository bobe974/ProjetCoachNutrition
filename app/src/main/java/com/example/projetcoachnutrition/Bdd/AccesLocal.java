package com.example.projetcoachnutrition.Bdd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetcoachnutrition.Modele.Aliment;
import com.example.projetcoachnutrition.Modele.User;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class AccesLocal {

    //propriétés
    private String nomBase = "bddCoachSante.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;


    // Gestion Aliment
    private ArrayList<Aliment> alimentsList;
    private String[] allColumns = { CoachSanteDbHelper.ID_FOOD,
            CoachSanteDbHelper.FOOD,CoachSanteDbHelper.ESTIMATED_CALORIES_FOR_A_PORTION };



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


        int lastIdFood = getLastiD("food","idFood");
        unaliment.setId(lastIdFood);

    }

    public void updateAliment(int id,int calories){
        bd = accesBD.getWritableDatabase();
        String req = "update food set estimatedCalories ="+calories+" where idFood =" +id;
        bd.execSQL(req);
        Log.d(TAG, "MAJ:**************************************** "+ req);
    }

    public void deleteAliment(int id){
        bd = accesBD.getWritableDatabase();
        String req = "Delete FROM food where idFood =" +id;
        bd.execSQL(req);
        Log.d(TAG, "MAJ :**************************************** "+ req);
    }


    /**
     *
     * @param unuser
     */
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
     * // Récupére dernier id d'un champ d'une table
     * @param latable
     * @param champ
     * @return
     */
    public int getLastiD(String latable , String champ){
        int lastId = 0; // Init lastIdFood à 0
        String query = "SELECT "+champ+" from "+latable+" order by "+champ+" DESC limit 1"; // Requête pour récup
        Cursor c = bd.rawQuery(query, null); // On place le curseur
        if (c != null && c.moveToFirst()) {
            lastId = c.getInt(0); //Le 0 est l'index de la colonne, nous n'avons qu'une colonne, donc l'index est 0
        }
        Log.d(TAG, "lastid:**************************************** "+ lastId);
        return lastId;
    }


    /**
     * Recupére les aliments dans la base de données
     * @return
     */
    public List<Aliment> getAllAliments() {
        bd = accesBD.getWritableDatabase();
        List<Aliment> aliments = new ArrayList<Aliment>();
        String query = "SELECT * from food"; // Requête pour récup

        Cursor cursor = bd.query(CoachSanteDbHelper.TABLE_FOOD,
                allColumns, null, null, null, null, null); // Plaçage du curseur afin de tout récupérer

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {         // Ajout de tous les aliments à la liste
            Aliment aliment = cursorToAliment(cursor);
            aliments.add(aliment);
            cursor.moveToNext();
        }
        cursor.close(); // On ferme le curseur

        return aliments;    // Retourne la liste complète
    }

    /**
     * //Attribution des attributs aux aliments
     * @param cursor
     * @return
     */
    private Aliment cursorToAliment(Cursor cursor) {
        Aliment aliment = new Aliment(99999,"no name",9999);
        aliment.setId(cursor.getInt(0));
        aliment.setName(cursor.getString(1));
        aliment.setCalories(cursor.getInt(2));
        return aliment;
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