package com.csuncion.examen_suncion.examen_final.upn.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.csuncion.examen_suncion.examen_final.upn.entities.User;
import com.csuncion.examen_suncion.examen_final.upn.util.Constant;
import com.csuncion.examen_suncion.examen_final.upn.util.RestaurantDB;

public class DAORestaurant {
    RestaurantDB restaurantDB;
    SQLiteDatabase db;
    private Context context;

    public DAORestaurant(Context context){
        this.restaurantDB = new RestaurantDB(context);
        this.context = context;
    }

    public void openDB(){db = this.restaurantDB.getWritableDatabase();}

    public String registerUser(User user){
        String answer = "";
        try {
            ContentValues values = new ContentValues();
            values.put("firstname", user.getFirstname());
            values.put("lastname", user.getLastname());
            values.put("mail", user.getMail());
            values.put("dni", user.getDni());
            values.put("sex", user.getSex());
            values.put("password", user.getPassword());
            long ans  = db.insert(Constant.NAME_TABLE_USER,null,values);
            if(ans == -1){
                answer = "Error al insertar";
            }else{
                answer = "Se registró correctamente";
            }

        }catch (Exception e){
            answer = e.getMessage();
        }
        return answer;
    }

    public String getUser(User user){
        String exists = "";
        try{
            String query = "SELECT * FROM " + Constant.NAME_TABLE_USER + " WHERE mail = '" + user.getMail() + "' AND  password  = '" + user.getPassword() + "'";
            Cursor c = db.rawQuery(query,null);
            if(c.getCount()==0) {
                exists = "Debe registrarse";
            }else{
                while(c.moveToNext()) {
                    exists = "Bienvenido " + c.getString(1);
                }
            }

        }catch (Exception e){
            exists =  e.getMessage();
        }
        return exists;
    }

    public String getExistUser(User user){
        String exists = "";
        try{
            String query = "SELECT * FROM " + Constant.NAME_TABLE_USER + " WHERE mail = '" + user.getMail() + "'";
            Cursor c = db.rawQuery(query,null);

            if (c.getCount() > 0){
                exists =  "Ya existe el usuario " + user.getFirstname();
            }
        }catch (Exception e){
            exists =  e.getMessage();
        }
        return exists;
    }
}
