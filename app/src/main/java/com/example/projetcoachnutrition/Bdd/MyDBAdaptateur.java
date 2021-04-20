package com.example.projetcoachnutrition.Bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.projetcoachnutrition.Modele.Aliment;

import static android.content.ContentValues.TAG;

public class MyDBAdaptateur {

    //propriété bdd
    public static final int DB_VERSION = 1;
    private static final String DATABASE_NAME = "CoachSante.sqlite";
    private MyOpenHelper accesBD;
    private SQLiteDatabase myDB;


    // liste des tables
    private static final String TABLE_FOOD = "food";

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

    private static final String DELETE_TABLE_FOOD = "DROP TABLE IF EXISTS " + TABLE_FOOD;


    /**
     * constructeur
     * @param context
     */
    public MyDBAdaptateur(Context context) {
        accesBD = new MyOpenHelper(context, DATABASE_NAME, null, DB_VERSION);

    }


    public void open() {
        myDB = accesBD.getWritableDatabase();
    }
    public void close(){myDB.close(); }

     /* *
     *
     * @param name
     * @param nbCalories
     * @return
     */
    public void insertAliment(Aliment unaliment) {
        /*Log.i("DataBase", "********INSERT ALIMENT");
        ContentValues values = new ContentValues();
        values.put(FOOD, unaliment.getName());
        Log.i("nom", "nom"+unaliment.getName());
        values.put( NB_CALORIES, unaliment.getCalories());
        return myDB.insert(TABLE_FOOD, null, values); */
        open();
        String req = "insert into food (idFood,food, estimatedCalories) values";
        req += "(\""+unaliment.getId()+"\",\""+unaliment.getName()+"\",\""+unaliment.getCalories()+"\")";
        Log.d(TAG, "ajout:**************************************** " + req);
        //executer la requete
        myDB.execSQL(req);

    }

    /**
     *
     * @param id
     * @param name
     * @param nbcalories
     * @return
     */

    public int updateAliment(long id, String name, int nbcalories) {
        ContentValues values = new ContentValues();

        values.put(FOOD, name);
        values.put(NB_CALORIES, nbcalories);
        return myDB.update(TABLE_FOOD, values, ID_FOOD + "=" + id, null);
    }

    /**
     *
     * @param id
     * @return
     */
    public int deleteAliment(long id) {
        return myDB.delete(TABLE_FOOD, ID_FOOD + " = " + id, null);
    }

    private class MyOpenHelper extends SQLiteOpenHelper {
        /**
         *
         * constructeur
         * @param context
         * @param name
         * @param cursorFactory
         * @param version
         */
        public MyOpenHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory cursorFactory, int version) {
                            super(context, name, cursorFactory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("ONCREATE", "*****************CA MARCHE");
            db.execSQL(CREATE_TABLE_FOOD);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {
            db.execSQL("drop table " + TABLE_FOOD + ";");
            onCreate(db);
        }
    }

}









