package com.example.projetcoachnutrition.Bdd;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    // liste des tables
    public static final String TABLE_FOOD = "food";
    public static  final String TABLE_PROFIL ="profil";

    //les Aliments
    private static final String ID_FOOD = "idFood";
    private static final String FOOD = "food";
    private static final String NB_CALORIES = "estimatedCalories";

    //Table Food

    private static final String CREATE_TABLE_FOOD = "CREATE TABLE "
            + TABLE_FOOD + "("
            + ID_FOOD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + FOOD + " TEXT,"
            + NB_CALORIES + " INTEGER" + ")";

    //Table profil
    private String CREATE_TABLE_PROFIL = "create table profil ("
            + "datemesure TEXT PRIMARY KEY,"
            + "poids INTEGER NOT NULL,"
            + "taille INTEGER NOT NULL,"
            + "age INTEGER NOT NULL,"
            + "sexe INTEGER NOT NULL);";

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
        sqLiteDatabase.execSQL(CREATE_TABLE_PROFIL);
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
