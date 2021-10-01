package com.csuncion.examen_suncion.examen_final.upn.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantDB extends SQLiteOpenHelper {
    public RestaurantDB(Context context){super(context, Constant.NAME_DB,null,Constant.VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + Constant.NAME_TABLE_USER +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "firstname TEXT NOT NULL, " +
                        "lastname TEXT NOT NULL, " +
                        "mail TEXT NOT NULL, " +
                        "dni TEXT NOT NULL, " +
                        "sex TEXT NOT NULL, " +
                        "password TEXT NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
