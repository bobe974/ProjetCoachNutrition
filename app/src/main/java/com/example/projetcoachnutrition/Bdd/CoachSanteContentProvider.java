package com.example.projetcoachnutrition.Bdd;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CoachSanteContentProvider  extends ContentProvider {

    private static CoachSanteDbHelper databaseHelper;


    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    public static boolean isUserAlreadyDefined() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String Query = "Select * from " + CoachSanteDbHelper.getTableUser() + ";";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }

        return true;
    }

    public static User getCurrentUser() {

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String Query = "Select * from " + CoachSanteDbHelper.getTableUser() + ";";
        Cursor cursor = db.rawQuery(Query, null);

        User user = null;
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                if(!cursor.isNull(0)) {
                    user = new User(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4));
                }

            } while (cursor.moveToNext());

        }

        return user;
    }
}
