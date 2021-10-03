package com.csuncion.examen_suncion.examen_final.upn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.csuncion.examen_suncion.examen_final.upn.entities.Menu;
import com.csuncion.examen_suncion.examen_final.upn.models.DAORestaurant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class OrderList extends AppCompatActivity {

    RecyclerView recyclerOrderList;
    FloatingActionButton btnEditFood, btnDeleteFood;
    DAORestaurant daoRestaurant  = new DAORestaurant(this);
    List<Menu> listMenu = new ArrayList<>();
    AdapterCustomized adapter;
    String usr =  "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        daoRestaurant.openDB();
        assignReferences();
        receivedData();
        showMenu();
    }

    private void showMenu(){
        listMenu = new ArrayList<>();
        listMenu = daoRestaurant.getMenu(usr);
        adapter = new AdapterCustomized(this,listMenu);
        recyclerOrderList.setAdapter(adapter);
        recyclerOrderList.setLayoutManager((new LinearLayoutManager(this)));
    }
    private void assignReferences(){
        recyclerOrderList = findViewById(R.id.recyclerOrderList);
        btnEditFood = findViewById(R.id.btnEditFood);
        btnEditFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderList.this, MenuFood.class);
                intent.putExtra("id", listMenu.get(0).getId() + "");
                intent.putExtra("codMenu", listMenu.get(0).getCodMenu() + "");
                intent.putExtra("mailOrder", listMenu.get(0).getMail() + "");
                startActivity(intent);
            }
        });
        btnDeleteFood = findViewById(R.id.btnDeleteFood);
        btnDeleteFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAORestaurant daoRestaurant = new DAORestaurant(OrderList.this);
                daoRestaurant.openDB();
                AlertDialog.Builder window = new AlertDialog.Builder(OrderList.this);
                window.setTitle("Mensaje Informativo");
                window.setMessage("¿Desea eliminar el pedido?");
                window.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String answer = daoRestaurant.deleteOrder(listMenu.get(0).getMail());
                        AlertDialog.Builder window1 = new AlertDialog.Builder(OrderList.this);
                        window1.setTitle("Mensaje informativo");
                        window1.setMessage(answer);
                        window1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(OrderList.this, MenuFood.class);
                                startActivity(intent);
                            }
                        });
                        window1.create().show();
                    }
                });
                window.setNegativeButton("NO",null);
                window.create().show();
            }
        });
    }
    private void receivedData(){
        if(getIntent().hasExtra("mail")) {
            usr = getIntent().getStringExtra("mail");
        }
    }
}