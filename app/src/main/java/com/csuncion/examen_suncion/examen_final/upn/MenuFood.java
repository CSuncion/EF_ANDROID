package com.csuncion.examen_suncion.examen_final.upn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.csuncion.examen_suncion.examen_final.upn.entities.Menu;
import com.csuncion.examen_suncion.examen_final.upn.entities.User;
import com.csuncion.examen_suncion.examen_final.upn.models.DAORestaurant;

import java.util.ArrayList;
import java.util.List;

public class MenuFood extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner E1, E2, E3, E4, F1, F2, F3, F4;
    TextView txtUsr, txtPrctgInput, txtPrctgSecond, txtHuancaina, txtEnsalada, txtSopa, txtCeviche, txtLomo, txtArroz, txtEstofado, txtSeco;
    boolean existsUsr = true;
    User user;
    double prctgInput, prctgSecond;
    Button btnAddOrder;
    DAORestaurant daoRestaurant = new DAORestaurant(this);
    Menu menu;
    List<Menu> listMenu = new ArrayList<>();
    ImageButton btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_food);
        daoRestaurant.openDB();
        asignarReferencias ();
        receiveData();
    }

    private void asignarReferencias() {
        chargeSpinner();
        txtUsr = findViewById(R.id.txtUsr);
        txtPrctgInput = findViewById(R.id.txtPrctgInput);
        txtPrctgSecond = findViewById(R.id.txtPrctgSecond);
        txtHuancaina = findViewById(R.id.txtHuancina);
        txtEnsalada = findViewById(R.id.txtEnsalada);
        txtSopa = findViewById(R.id.txtSopa);
        txtCeviche = findViewById(R.id.txtCeviche);
        txtLomo = findViewById(R.id.txtLomo);
        txtArroz = findViewById(R.id.txtArroz);
        txtEstofado = findViewById(R.id.txtEstofado);
        txtSeco = findViewById(R.id.txtSeco);
        btnAddOrder = findViewById(R.id.btnAddOrder);
        btnAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureData();
                Intent intent;
                String message = "";
                for (int i = 0; i < listMenu.size(); i++) {
                    message = daoRestaurant.registerMenu(listMenu.get(i));
                }
                Toast.makeText(MenuFood.this, message, Toast.LENGTH_SHORT).show();
                intent = new Intent(MenuFood.this, OrderList.class);
                intent.putExtra("mail", txtUsr.getText().toString() + "");
                startActivity(intent);
            }
        });
        btnOrder = findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuFood.this, OrderList.class);
                intent.putExtra("mail", txtUsr.getText().toString() + "");
                startActivity(intent);
            }
        });
    }

    private void captureData(){
        String food = "", input = "", description = "";
        String countFood = "0", countInput = "0";
        Double priceTotal = 0.0;
        Integer countTotal = 0, countSuperTotal = 0;
        Integer codMenu = 0;
        codMenu = daoRestaurant.getCodMenu();
        if(codMenu == 0){
            codMenu = 1;
        }
        for (int i = 1; i<5; i++){
            Integer codFood = i;
            switch (i) {
                case 1:
                    food = txtLomo.getText().toString();
                    countFood = F1.getSelectedItem().toString();
                    input = txtHuancaina.getText().toString();
                    countInput = E1.getSelectedItem().toString();

                    break;
                case 2:
                    food = txtArroz.getText().toString();
                    countFood = F2.getSelectedItem().toString();
                    input = txtEnsalada.getText().toString();
                    countInput = E2.getSelectedItem().toString();
                    break;
                case 3:
                    food  = txtEstofado.getText().toString();
                    countFood = F3.getSelectedItem().toString();
                    input = txtSopa.getText().toString();
                    countInput = E3.getSelectedItem().toString();
                    break;
                case 4:
                    food  = txtSeco.getText().toString();
                    countFood = F4.getSelectedItem().toString();
                    input = txtCeviche.getText().toString();
                    countInput = E4.getSelectedItem().toString();
                    break;
            }
            String usr = txtUsr.getText().toString();
            Double priceFood = 0.0, priceFoodFixed = 10.0;
            Double priceInput = 0.0, priceInputFixed = 5.0;


            if(txtUsr.getText().equals("Sin Usuario")){
                priceFood = priceFoodFixed * Integer.parseInt(countFood);
                priceInput = priceInputFixed * Integer.parseInt(countInput);
                priceTotal = (priceFood)+(priceInput);
            }else{
                priceFood = priceFoodFixed * Integer.parseInt(countFood);
                priceFood = priceFood - (priceFood * 0.05);
                priceInput = priceInputFixed * Integer.parseInt(countInput);
                priceInput = priceInput - (priceInput * 0.03);
                priceTotal = priceFood + priceInput;
            }
            countTotal =  (Integer.parseInt(countFood) + Integer.parseInt(countInput));
            countSuperTotal = countSuperTotal + countTotal;

            menu = new Menu(codMenu,codFood,food,input,usr,priceFood,priceInput,priceTotal,Integer.parseInt(countFood),Integer.parseInt(countInput),countTotal);
            listMenu.add(menu);
        }
    }

    private void receiveData(){
        if(getIntent().hasExtra("mail")){
         txtUsr.setText(getIntent().getStringExtra("mail"));
         if(!txtUsr.getText().equals("Sin Usuario")){
            txtPrctgInput.setText("3%");
            prctgInput = 0.03;
            txtPrctgSecond.setText("5%");
            prctgSecond = 0.05;
         }else{
             txtPrctgInput.setText("0%");
             prctgInput = 0;
             txtPrctgSecond.setText("0%");
             prctgSecond = 0;
         }
        }
    }

    private void chargeSpinner(){
        E1 = findViewById(R.id.E1);
        E2 = findViewById(R.id.E2);
        E3 = findViewById(R.id.E3);
        E4 = findViewById(R.id.E4);
        F1 = findViewById(R.id.F1);
        F2 = findViewById(R.id.F2);
        F3 = findViewById(R.id.F3);
        F4 = findViewById(R.id.F4);

        E1.setOnItemSelectedListener(this);
        E2.setOnItemSelectedListener(this);
        E3.setOnItemSelectedListener(this);
        E4.setOnItemSelectedListener(this);
        F1.setOnItemSelectedListener(this);
        F2.setOnItemSelectedListener(this);
        F3.setOnItemSelectedListener(this);
        F4.setOnItemSelectedListener(this);

        List<String> countFood = new ArrayList<String>();

        for (int i = 0; i<6;i++){
            countFood.add("" + i + "");
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countFood);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        E1.setAdapter(dataAdapter);
        E2.setAdapter(dataAdapter);
        E3.setAdapter(dataAdapter);
        E4.setAdapter(dataAdapter);
        F1.setAdapter(dataAdapter);
        F2.setAdapter(dataAdapter);
        F3.setAdapter(dataAdapter);
        F4.setAdapter(dataAdapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}