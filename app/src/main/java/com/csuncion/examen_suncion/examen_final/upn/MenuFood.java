package com.csuncion.examen_suncion.examen_final.upn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

public class MenuFood extends AppCompatActivity {
    Spinner E1, E2, E3, E4, F1, F2, F3, F4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_food);
        asignarReferencias ();
    }

    private void asignarReferencias() {
        E1 = findViewById(R.id.E1);
        E1.setOnItemSelectedListener(this);
        E2 = findViewById(R.id.E2);
        E3 = findViewById(R.id.E3);
        E4 = findViewById(R.id.E4);
        F1 = findViewById(R.id.F1);
        F2 = findViewById(R.id.F2);
        F3 = findViewById(R.id.F3);
        F4 = findViewById(R.id.F4);
    }
}