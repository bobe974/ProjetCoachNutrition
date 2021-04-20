package com.example.projetcoachnutrition.Bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CoachSanteDbHelper {

    /* Info DB*/
    // db Version
    private static final int DATABASE_VERSION = 7;
    // nom dbb
    private static final String DATABASE_NAME = "CoachSanteDb";


    /*----------------------*/
    /* Table FOOD*/
    private static final String TABLE_FOOD = "food";
    // Colonne Table FOOD
    private static final String ID_FOOD = "f_idFood";
    private static final String FOOD = "f_food";
    private static final String ESTIMATED_CALORIES_FOR_A_PORTION = "f_estimatedCalories";
    /*----------------------*/

    /*----------------------*/
    /* Table MEAl*/
    private static final String TABLE_MEAL = "meal";
    // Colonne Table meal
    private static final String MEAL_NAME = "m_meal";
    private static final String MEAL_ID = "m_idMeal";
    private static final String MEAL_DATE = "m_dateMeal";
    private static final String TOTAL_CALORIES = "m_totalCalories";
    /*----------------------*/


    /*----------------------*/
    /* Table USER*/
    private static final String TABLE_USER = "user";
    // Colonne Table user
    private static final String USER_ID = "u_idUser";
    private static final String USER_NAME = "u_username";
    private static final String USER_WEIGHT = "u_weight";
    private static final String USER_MIN_CALORIES = "u_minCal";
    private static final String USER_MAX_CALORIES = "u_maxCal";
    /*----------------------*/


    /*----------------------*/
    /* Table EATEN*/
    private static final String TABLE_EATEN_FOOD = "eaten";
    // Colonne Table eaten
    private static final String ID_EATEN = "e_idEaten";
    private static final String ID_EATEN_FOOD = "e_idEatenFood";
    private static final String ID_MEAL_CONCERNED = "e_idMealConcerned";
    private static final String QUANTITY_EATEN = "e_quantityEaten";
    /*----------------------*/


    /*-----------------------------CREATION DES TABLES---------------------*/

    // --------- Table User
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + USER_NAME + " TEXT,"
            + USER_WEIGHT + " INTEGER,"
            + USER_MIN_CALORIES + " INTEGER,"
            + USER_MAX_CALORIES + " INTEGER" + ")";

    private static final String DELETE_TABLE_USER = "DROP TABLE IF EXISTS " + TABLE_USER;


    // --------- Table Food

    private static final String CREATE_TABLE_FOOD = "CREATE TABLE "
            + TABLE_FOOD + "("
            + ID_FOOD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + FOOD + " TEXT,"
            + ESTIMATED_CALORIES_FOR_A_PORTION + " INTEGER" + ")";

    private static final String DELETE_TABLE_FOOD = "DROP TABLE IF EXISTS " + TABLE_FOOD;

    // --------- Table Meal

    private static final String CREATE_TABLE_MEAL = "CREATE TABLE "
            + TABLE_MEAL + "("
            + MEAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + MEAL_DATE + " DATETIME,"
            + TOTAL_CALORIES + " REAL,"
            + MEAL_NAME + " TEXT" + ")";

    private static final String DELETE_TABLE_MEAL = "DROP TABLE IF EXISTS " + TABLE_MEAL;


    // --------- Table Eaten Food
    private static final String CREATE_TABLE_EATEN_FOOD = "CREATE TABLE "
            + TABLE_EATEN_FOOD  + " ( "
            + ID_EATEN + " INTEGER PRIMARY KEY NOT NULL, "
            + ID_EATEN_FOOD + " INTEGER,"
            + ID_MEAL_CONCERNED + " INTEGER,"
            + QUANTITY_EATEN + " REAL,"
            + " FOREIGN KEY ( "+ID_EATEN_FOOD+" ) REFERENCES "+TABLE_FOOD+" ( "+ID_FOOD+" ), "
            + " FOREIGN KEY ( "+ID_MEAL_CONCERNED+" ) REFERENCES "+TABLE_MEAL+" ( "+ID_MEAL_CONCERNED+" ) );";

    private static final String DELETE_TABLE_EATEN_FOOD = "DROP TABLE IF EXISTS " + TABLE_FOOD;

    /*-----------------------------FIN TABLES---------------------*/

    /*CLASSE*/





    /**/


    private SQLiteDatabase mDB;

    private SQLiteOpenHelper mOpenHelper; // MyOpenHelper : voir plus bas

    public CoachSanteDbHelper(Context context) {
        mOpenHelper = new MyOpenHelper(context, DATABASE_NAME,
                null, DATABASE_VERSION);
    }

    public void open(){
        mDB = MyOpenHelper.getWritableDataBase;
    }

    public void close(){
        mDB.close();
    }



    //Common attributes
    private static final String ID = "id";

    public static String getTableFood() {
        return TABLE_FOOD;
    }

    public static String getTableMeal() {
        return TABLE_MEAL;
    }

    public static String getTableUser() {
        return TABLE_USER;
    }

    public static String getTableEatenFood() {
        return TABLE_EATEN_FOOD;
    }



    public static String getFoodColumn() {
        return FOOD;
    }

    public static String getEstimatedCaloriesForAPortion() {
        return ESTIMATED_CALORIES_FOR_A_PORTION;
    }

    public static String getIdMealColumn() {
        return MEAL_ID;
    }

    public static String getDateTimeColumn(){
        return MEAL_DATE;
    }

    public static String getIdEatenFoodColumn() {
        return ID_EATEN_FOOD;
    }

    public static String getIdMealConcernedColumn() {
        return ID_MEAL_CONCERNED;
    }

    public static String getTotalCaloriesMealColumn() {
        return TOTAL_CALORIES;
    }

    public static String getEstimatedCaloriesForAPortionColumn() {
        return ESTIMATED_CALORIES_FOR_A_PORTION;
    }

    public static String getQuantityEatenColumn() {
        return QUANTITY_EATEN;
    }

    public static String getUserIdColumn(){
        return USER_ID;
    }

    public static String getUserNameColumn() {
        return USER_NAME;
    }

    public static String getUserWeightColumn() {
        return USER_WEIGHT;
    }

    public static String getUserMinCalColumn() {
        return USER_MIN_CALORIES;
    }

    public static String getUserMaxCalColumn() {
        return USER_MAX_CALORIES;
    }


    /*Classe MyOpenHelper*/

    private static class MyOpenHelper extends SQLiteOpenHelper {

        public static SQLiteDatabase getWritableDataBase;

        public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version) {
            super(context, name, cursorFactory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_FOOD);
            db.execSQL(CREATE_TABLE_USER);
            db.execSQL(CREATE_TABLE_MEAL);
            db.execSQL(CREATE_TABLE_EATEN_FOOD);
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            Log.w("TaskDBAdapter", "Upgrading from version " +oldVersion + " to " +newVersion + ", which will destroy all old data");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MEAL);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EATEN_FOOD);
            onCreate(sqLiteDatabase);
        }
        public SQLiteDatabase getWritableDataBase(){
            return null;
        }
        public SQLiteDatabase getReadableDatabase(){
            return null;
        }
    }
}
