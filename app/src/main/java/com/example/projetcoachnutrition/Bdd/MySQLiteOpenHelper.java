package com.example.projetcoachnutrition.Bdd;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    // liste des tables
    public static final String TABLE_FOOD = "food";
    public static  final String TABLE_USER ="user";
    public static final String TABLE_REPAS = "repas";
    public static final String TABLE_EATEN_FOOD = "eaten";
    /*CONSTANTES DES ATTRIBUT DES TABLES*/

    //FOOD
    public  static final String ID_FOOD = "idFood";
    public static final String FOOD = "food";
    public static final String NB_CALORIES = "estimatedCalories";

    //USER
    public static final String USER_ID = "idUser";
    public static final String USER_NOM = "nom";
    public static final String USER_AGE = "age";
    public static final String USER_POIDS = "poids";
    public static final String USER_TAILLE = "taille";
    public static final String USER_SEXE = "sexe";

    //REPAS
    public static final String REPAS_ID = "idRepas";
    public static final String REPAS_DATE = "date";
    public static final String REPAS_CALORIES = "calories";

    //Eaten Food Attributes
    private static final String ID_EATEN = "idEaten";
    private static final String ID_EATEN_FOOD = "idEatenFood";
    private static final String ID_MEAL_CONCERNED = "idMealConcerned";
    private static final String QUANTITY_EATEN = "quantityEaten";

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

    //table repas
    private static final String CREATE_TABLE_REPAS = "CREATE TABLE "
            + TABLE_REPAS + "("
            + REPAS_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + REPAS_DATE + " TEXT,"
            + REPAS_CALORIES + " INTEGER"
            + ")";

    //Table Eaten Food create statement
    private static final String CREATE_TABLE_EATEN_FOOD = "CREATE TABLE "
            + TABLE_EATEN_FOOD  + " ( "
            + ID_EATEN + " INTEGER PRIMARY KEY NOT NULL, "
            + ID_EATEN_FOOD + " INTEGER,"
            + ID_MEAL_CONCERNED + " INTEGER,"
            + QUANTITY_EATEN + " REAL,"
            + " FOREIGN KEY ( "+ID_EATEN_FOOD+" ) REFERENCES "+TABLE_FOOD+" ( "+ID_FOOD+" ), "
            + " FOREIGN KEY ( "+ID_MEAL_CONCERNED+" ) REFERENCES "+TABLE_REPAS+" ( "+ID_MEAL_CONCERNED+" ) );";

    private static final String DELETE_TABLE_EATEN_FOOD = "DROP TABLE IF EXISTS " + TABLE_FOOD;


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
        sqLiteDatabase.execSQL(CREATE_TABLE_REPAS);
        Log.d("REQ", "onCreateFood: "+CREATE_TABLE_USER);
        Log.d("REQ", "onCreateFood: "+CREATE_TABLE_FOOD);
        Log.d("REQ", "onCreateFood: "+CREATE_TABLE_REPAS);

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


    public static String getDateTimeColumn(){
        return REPAS_DATE;
    }

    public static String getTotalCaloriesMealColumn() {
        return REPAS_CALORIES;
    }

    public static String getIdEatenFoodColumn() {
        return ID_EATEN_FOOD;
    }

    public static String getIdMealConcernedColumn() {
        return ID_MEAL_CONCERNED;
    }

    public static String getQuantityEatenColumn() {
        return QUANTITY_EATEN;
    }
}
