package com.csuncion.examen_suncion.examen_final.upn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    FloatingActionButton btnNewFood;
    DAORestaurant daoRestaurant  = new DAORestaurant(this);
    List<Menu> listMenu = new ArrayList<>();
    AdapterCustomized adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        daoRestaurant.openDB();
        assignReferences();
        showMenu();
    }
    private void showMenu(){
        listMenu = daoRestaurant.getMenu();
        adapter = new AdapterCustomized(this,listMenu);
        recyclerOrderList.setAdapter(adapter);
        recyclerOrderList.setLayoutManager((new LinearLayoutManager(this)));
    }
    private void assignReferences(){
        recyclerOrderList = findViewById(R.id.recyclerOrderList);
        btnNewFood = findViewById(R.id.btnNewFood);
        btnNewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderList.this,MenuFood.class);
                startActivity(intent);
            }
        });
    }
}