package com.csuncion.examen_suncion.examen_final.upn.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantDB extends SQLiteOpenHelper {
    public RestaurantDB(Context context){super(context, Constant.NAME_DB,null,Constant.VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryUser =
                "CREATE TABLE " + Constant.NAME_TABLE_USER +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "firstname TEXT NOT NULL, " +
                        "lastname TEXT NOT NULL, " +
                        "mail TEXT NOT NULL, " +
                        "dni TEXT NOT NULL, " +
                        "sex TEXT NOT NULL, " +
                        "password TEXT NOT NULL); ";
        db.execSQL(queryUser);

        String queryContact =
                "CREATE TABLE " + Constant.NAME_TABLE_CONTACT +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "fullName TEXT NOT NULL," +
                        "phone TEXT NOT NULL," +
                        "subject TEXT NOT NULL," +
                        "message TEXT NOT NULL);";
        db.execSQL(queryContact);

        String queryMenu =
                "CREATE TABLE " + Constant.NAME_TABLE_MENU +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "food TEXT NOT NULL," +
                        "description TEXT NOT NULL," +
                        "detail TEXT NOT NULL," +
                        "category TEXT NOT NULL," +
                        "price FLOAT NOT NULL," +
                        "count INTEGER NOT NULL," +
                        "status INTEGER NOT NULL);";
        db.execSQL(queryMenu);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
