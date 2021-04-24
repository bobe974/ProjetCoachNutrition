package com.example.projetcoachnutrition.Bdd;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    // liste des tables
    public static final String TABLE_FOOD = "food";
    public static  final String TABLE_USER ="user";

    /*CONSTANTES DES ATTRIBUT DES TABLES*/

    //FOOD
    public  static final String ID_FOOD = "idFood";
    public static final String FOOD = "food";
    public static final String NB_CALORIES = "estimatedCalories";

    //USER
    private static final String USER_ID = "idUser";
    private static final String USER_NOM = "nom";
    private static final String USER_AGE = "age";
    private static final String USER_POIDS = "poids";
    private static final String USER_TAILLE = "taille";
    private static final String USER_SEXE = "sexe";

    /*TABLE DE LA BASE*/

    //Table des aliments
    private static final String CREATE_TABLE_FOOD = "CREATE TABLE "
            + TABLE_FOOD + "("
            + ID_FOOD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + FOOD + " TEXT,"
            + NB_CALORIES + " INTEGER" + ")";

    // Table  user
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + USER_NOM + " TEXT,"
            + USER_AGE + " INTEGER,"
            + USER_POIDS + " INTEGER,"
            + USER_TAILLE + " INTEGER,"
            + USER_SEXE + " INTEGER"
             + ")";


    /**constructeur
     *
     * @param context
     * @param name
     * @param factory
     * @param version
     */

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /** redefini les methode de sqllite
     * si changement de bdd
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("BASE", "init: **************************CREATION BDD ");
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_FOOD);
        Log.d("REQ", "onCreateFood: "+CREATE_TABLE_FOOD);

    }


    /**si changement de version
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
