package com.csuncion.examen_suncion.examen_final.upn.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.csuncion.examen_suncion.examen_final.upn.entities.Contact;
import com.csuncion.examen_suncion.examen_final.upn.entities.Menu;
import com.csuncion.examen_suncion.examen_final.upn.entities.User;
import com.csuncion.examen_suncion.examen_final.upn.util.Constant;
import com.csuncion.examen_suncion.examen_final.upn.util.RestaurantDB;

import java.util.ArrayList;
import java.util.List;

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


    public String registerContact(Contact contact){
        String answer = "";
        try {
            ContentValues values = new ContentValues();
            values.put("fullName", contact.getFullName());
            values.put("phone", contact.getPhone());
            values.put("subject", contact.getSubject());
            values.put("message", contact.getMessage());
            long ans  = db.insert(Constant.NAME_TABLE_CONTACT,null,values);
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

    public List<Menu> getMenu(String mail){
        List<Menu> listMenu = new ArrayList<>();
        try{
            String query = "SELECT * FROM " + Constant.NAME_TABLE_MENU + " Where mail = '" + mail + "'";
            Cursor c = db.rawQuery(query,null);

            while (c.moveToNext()){
                listMenu.add(new Menu(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3),c.getString(4),c.getString(5), c.getDouble(6),c.getDouble(7),c.getDouble(8),c.getInt(9), c.getInt(10),c.getInt(11)));
            }
        }catch (Exception e){
            Log.d("==>", e.getMessage());
        }
        return listMenu;
    }

    public int getCodMenu(){
        int codMenu = 0;
        try{
            String query = "SELECT MAX(codMenu) + 1 FROM " + Constant.NAME_TABLE_MENU;
            Cursor c = db.rawQuery(query,null);

            codMenu = c.getInt(0);

        }catch (Exception e){
            Log.d("==>", e.getMessage());
        }
        return codMenu;
    }

    public String registerMenu(Menu menu){
        String answer = "";
        try {
            ContentValues values = new ContentValues();
            values.put("codMenu", menu.getCodMenu());
            values.put("codFood", menu.getCodFood());
            values.put("food", menu.getFood());
            values.put("input", menu.getInput());
            values.put("mail", menu.getMail());
            values.put("priceFood", menu.getPriceFood());
            values.put("priceInput", menu.getPriceInput());
            values.put("priceTotal", menu.getPriceTotal());
            values.put("countFood", menu.getCountFood());
            values.put("countInput", menu.getCountInput());
            values.put("countTotal", menu.getCountTotal());
            long ans  = db.insert(Constant.NAME_TABLE_MENU,null,values);
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

    public String updateOrder(Menu menu)
    {
        String answer = "";
        try{
            ContentValues values = new ContentValues();
            values.put("priceFood", menu.getPriceFood());
            values.put("priceInput", menu.getPriceInput());
            values.put("priceTotal", menu.getPriceTotal());
            values.put("countFood", menu.getCountFood());
            values.put("countInput", menu.getCountInput());
            values.put("countTotal", menu.getCountTotal());
            long ans = db.update(Constant.NAME_TABLE_MENU, values,"codMenu = " + menu.getCodMenu() + " and codFood = " + menu.getCodFood() + " and mail = '" + menu.getMail() + "'",null);
            if(ans == -1){
                answer = "Error al insertar";
            }else{
                answer = "Se actualizó correctamente";
            }
        }catch (Exception e){
            answer = e.getMessage();
        }
        return  answer;
    }


    public String deleteOrder(String mail){
        String answer = "";
        try{
            long ans = db.delete(Constant.NAME_TABLE_MENU,"mail = '" + mail + "'", null);
            if(ans == -1){
                answer = "Error al insertar";
            }else{
                answer = "Se eliminó correctamente";
            }
        }catch (Exception e){
            answer = e.getMessage();
        }
        return  answer;
    }
}
