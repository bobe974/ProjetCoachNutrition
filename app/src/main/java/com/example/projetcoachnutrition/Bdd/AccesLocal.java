package com.example.projetcoachnutrition.Bdd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetcoachnutrition.Modele.Aliment;
import com.example.projetcoachnutrition.Modele.Repas;
import com.example.projetcoachnutrition.Modele.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class AccesLocal {

    //propriétés
    private String nomBase = "bddCoachSante.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;


    // attribut aliment
    private ArrayList<Aliment> alimentsList;
    private String[] allColumns = {MySQLiteOpenHelper.ID_FOOD,
            MySQLiteOpenHelper.FOOD, MySQLiteOpenHelper.NB_CALORIES};

    //attribut user
    private String[] allUserColumns = {MySQLiteOpenHelper.USER_ID, MySQLiteOpenHelper.USER_NOM, MySQLiteOpenHelper.USER_AGE,
            MySQLiteOpenHelper.USER_POIDS, MySQLiteOpenHelper.USER_TAILLE, MySQLiteOpenHelper.USER_SEXE};

    //attribut repas
    private String[] allRepasColumns = {MySQLiteOpenHelper.REPAS_ID, MySQLiteOpenHelper.REPAS_DATE, MySQLiteOpenHelper.REPAS_CALORIES,};


    /**
     * constructeur
     *
     * @param contexte
     */
    public AccesLocal(Context contexte) {
        accesBD = new MySQLiteOpenHelper(contexte, this.nomBase, null, this.versionBase);
    }

    /**
     * ajoute un aliment dans la bdd
     *
     * @param unaliment
     */
    public void ajoutAliment(Aliment unaliment) {
        bd = accesBD.getWritableDatabase();
        String req = "insert into food (food, estimatedCalories) values";
        req += "(\"" + unaliment.getName() + "\",\"" + unaliment.getCalories() + "\")";

        //executer la requete
        bd.execSQL(req);

        Log.d(TAG, "ajout:**************************************** " + req);


        int lastIdFood = getLastiD("food", "idFood");
        unaliment.setId(lastIdFood);

    }

    /**
     * ajoute un profil
     *
     * @param unuser
     */
    public void ajoutUser(User unuser) {
        bd = accesBD.getWritableDatabase();
        String req = "insert into user(nom,age,poids,taille,sexe) values";
        req += "(\"" + unuser.getNom() + "\",\"" + unuser.getAge() + "\",\"" + unuser.getPoids() +
                "\",\"" + unuser.getTaille() + "\",\"" + unuser.getSexe() + "\")";
        Log.d(TAG, "ajout:**************************************** " + req);
        //executer la requete
        bd.execSQL(req);

        int lastIdUser = getLastiD("user", "idUser");
        unuser.setId(lastIdUser);
    }

    /**
     * @param unrepas
     */
    public void ajoutRepas(Repas unrepas) {
        Integer[] tab = unrepas.getAllId();
        bd = accesBD.getWritableDatabase();
        String req = "insert into repas(date, calories) values";
        req += "(\"" + unrepas.getDate() + "\",\"" + unrepas.getTotalCalories() + "\")";

        for (int i = 0; i < tab.length; i++) {

            String req2 = "insert into eatfood (idRepasEat, eatenfood)values";
            req2 += "(\"" + getLastiD("repas", "idRepas") + "\",\"" + tab[i] + "\")";
            Log.d(TAG, "ajoutEATEN:**************************************** " + req2);
            bd.execSQL(req2);
        }

        Log.d(TAG, "ajout:**************************************** " + req);
        //executer la requete
        bd.execSQL(req);

        int lastIdUser = getLastiD("repas", "idRepas");
        unrepas.setId(lastIdUser);
    }


    public void updateAliment(int id, int calories) {
        bd = accesBD.getWritableDatabase();
        String req = "update food set estimatedCalories =" + calories + " where idFood =" + id;
        bd.execSQL(req);
        Log.d(TAG, "MAJ:**************************************** " + req);
    }

    public void deleteAliment(int id) {
        bd = accesBD.getWritableDatabase();
        String req = "Delete FROM food where idFood =" + id;
        bd.execSQL(req);
        Log.d(TAG, "MAJ :**************************************** " + req);
    }


    /**
     * // Récupére dernier id d'un champ d'une table
     *
     * @param latable
     * @param champ
     * @return
     */
    public int getLastiD(String latable, String champ) {
        int lastId = 0; // Init lastIdFood à 0
        String query = "SELECT " + champ + " from " + latable + " order by " + champ + " DESC limit 1"; // Requête pour récup
        Cursor c = bd.rawQuery(query, null); // On place le curseur
        if (c != null && c.moveToFirst()) {
            lastId = c.getInt(0); //Le 0 est l'index de la colonne, nous n'avons qu'une colonne, donc l'index est 0
        }
        //Log.d(TAG, "lastid:**************************************** "+ lastId);
        return lastId;
    }

    public boolean utilisateurExistant() {
        bd = accesBD.getWritableDatabase();
        String Query = "Select * from user";
        Cursor cursor = bd.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }

        return true;
    }


    /**
     * Recupére les aliments dans la base de données
     *
     * @return
     */
    public ArrayList<Aliment> getAllAliments() {
        bd = accesBD.getWritableDatabase();
        ArrayList<Aliment> aliments = new ArrayList<Aliment>();
        Cursor cursor = bd.query(MySQLiteOpenHelper.TABLE_FOOD,
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
     * retourne la liste de tout les profils
     *
     * @return
     */
    public List<User> getAllUser() {
        bd = accesBD.getWritableDatabase();
        List<User> lesprofils = new ArrayList<User>();
        Cursor curseur = bd.query(MySQLiteOpenHelper.TABLE_USER,
                allUserColumns, null, null, null, null, null); // Plaçage du curseur afin de tout récupérer
        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {         // Ajout de tous les aliments à la liste
            User unuser = cursorToUser(curseur);
            Log.d(TAG, "RECUPPP USER *****************: " + unuser.getNom() + "id " + unuser.getId() + "imc" + unuser.getImg());
            lesprofils.add(unuser);
            curseur.moveToNext();

        }

        /********************TEST******************/
        for (User unuser : lesprofils) {
            Log.d(TAG, "TEST LISSSTE: " + unuser.getNom());
        }
        curseur.close(); // On ferme le curseur

        return lesprofils;    // Retourne la liste complète

    }

    /**
     * @return
     */
    public List<Repas> getAllRepas() {
        Aliment aliment;
        ArrayList<Aliment> lesaliment = new ArrayList<>();
        int i =0;
        Integer idEatean[] = new Integer[100];
        bd = accesBD.getWritableDatabase();
        List<Repas> lesrepas = new ArrayList<Repas>();
        Cursor curseur = bd.query(MySQLiteOpenHelper.TABLE_REPAS,
                allRepasColumns, null, null, null, null, null); // Plaçage du curseur afin de tout récupérer
        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {         // Ajout de tous les aliments à la liste
            Repas unrepas = cursorToRepas(curseur);

            //charge tout les aliment consommé pendant le repas
            Log.d("TAG", "getAllRepas: ***********REPAS ID"+unrepas.getId());
            idEatean = getIdEatenFood(unrepas.getId());
            Log.d("TAG", "getAllRepas: ***********TOUT CHARGERR");
            unrepas.setLesAliments(recupAlimParId(idEatean[i]));
            //on atribue les aliments dans la liste de l'objet par l'id
            //unrepas.setList(getAlimentParId(unrepas.getAllId()));

            Log.d(TAG, "***************RECUPPP REPAS !!!!!!!!!!!!!*****************: " + unrepas.getId() + "date " + unrepas.getDate());
            lesrepas.add(unrepas);
            curseur.moveToNext();
            i++;
        }

        /********************TEST******************/
        for (Repas repas : lesrepas) {
            Log.d(TAG, "TEST LISSSTE: " + repas.getTotalCalories());
        }
        curseur.close(); // On ferme le curseur

        return lesrepas;    // Retourne la liste complète
    }

    /**
     * retourne le dernier profil pour calcule imc
     *
     * @return
     */
    public User recupDernierUser() {
        bd = accesBD.getReadableDatabase();
        User profil = null;
        String req = "select * from user";
        Cursor curseur = bd.rawQuery(req, null);  //lit sur un select * ligne pas ligne
        curseur.moveToLast(); //pointe sur le dernier
        if (!curseur.isAfterLast()) {
            //Date date  = new Date();
            int IdUser = getLastiD("user", "idUser");
            String nom = curseur.getString(1);
            int age = curseur.getInt(2);
            int poids = curseur.getInt(3);
            int taille = curseur.getInt(4);
            int sexe = curseur.getInt(5);

            profil = new User(IdUser, nom, age, poids, taille, sexe); //créer un profil avec les données recupérés

        }
        curseur.close();
        return profil;
    }

    /**
     * //Attribution des attributs aux aliments
     *
     * @param cursor
     * @return
     */
    private Aliment cursorToAliment(Cursor cursor) {
        Aliment aliment = new Aliment(99999, "no name", 9999);
        aliment.setId(cursor.getInt(0));
        aliment.setName(cursor.getString(1));
        aliment.setCalories(cursor.getInt(2));
        return aliment;
    }

    /**
     *
     * @param curseur
     * @return
     */
    private User cursorToUser(Cursor curseur) {
        int IdUser = getLastiD("user", "idUser");
        String nom = curseur.getString(1);
        int age = curseur.getInt(2);
        int poids = curseur.getInt(3);
        int taille = curseur.getInt(4);
        int sexe = curseur.getInt(5);
        User user = new User(IdUser, nom, age, poids, taille, sexe);

        return user;
    }

    /**
     *
     * @param cursor
     * @return
     */
    private Repas cursorToRepas(Cursor cursor) {
        int id = cursor.getInt(0);
        String date = cursor.getString(1);
        float calories = cursor.getFloat(2);
        Repas repas = new Repas(id, date, calories);
        Log.d(TAG, "*************************cursorToRepas***********************: id:" + repas.getId() + "date" + repas.getSdate());
        return repas;
    }

    /**
     * retourne une liste avec les aliments correspondant au tab id en parametre
     *
     * @return
     */
    public ArrayList<Aliment> getAlimentParId(Integer[] tabid) {
        ArrayList<Aliment> alimentParId = new ArrayList<Aliment>();
        ArrayList<Aliment> aliments = new ArrayList<Aliment>();
        aliments = getAllAliments();

        for (Aliment aliment : aliments) {
            for (int i = 0; i < tabid.length; i++) {
                if (aliment.getId() == tabid[i]) {
                    alimentParId.add(new Aliment(aliment.getId(), aliment.getName(), aliment.getCalories()));
                }
            }
        }

        return alimentParId;
    }

    /**
     *retourne des aliment par  id
     * @param id
     * @return
     */
    public ArrayList<Aliment> recupAlimParId(int id){
        ArrayList<Aliment> listAlimant = new ArrayList<Aliment>();
        bd = accesBD.getReadableDatabase();
        String req = "select * from food where idFood =" + id;
        Log.d(TAG, "recupAlimParId: "+req);
        int calories = 0;
        String nom ="";

        Cursor curseur = bd.rawQuery(req, null);  //lit sur un select * ligne pas ligne
        curseur.moveToLast(); //pointe sur le dernier
        if (!curseur.isAfterLast()) {

             nom = curseur.getString(1);
            calories = curseur.getInt(2);
            listAlimant.add(new Aliment(id,nom,calories));

        }

        curseur.close();
        return listAlimant;

    }

    /**
     *
     * @param repasid
     * @return
     */
    public Integer[] getIdEatenFood(int repasid){
        Integer  tab[] = new Integer[1000];
        int i = 0;
        bd = accesBD.getReadableDatabase();
        String req = "select eatenfood FROM repas inner join eatfood  on Repas.idRepas = eatfood.idRepasEat where idRepas ="+repasid;
        Log.d("getideatenfood", "recupAlimParId: "+req);
        Cursor curseur = bd.rawQuery(req, null);  //lit sur un select * ligne pas ligne
        curseur.moveToLast(); //pointe sur le dernier

        while (!curseur.isAfterLast()) {
            Log.d(TAG, "getIdEatenFood: ********************************");
            tab[i]= curseur.getInt(0);
            Log.d(TAG, "getIdEatenFood: TAB ********************************"+tab[i] +"i"+i);
            i++;
        }
        curseur.close();
        for(Integer in : tab){
            Log.d(TAG, "le tableau contient************************************:"+tab[i]);
        }
        return tab;
    }

}


