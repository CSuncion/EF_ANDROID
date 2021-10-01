package com.csuncion.examen_suncion.examen_final.upn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtUbication, txtContact, txtRegister, txtUser, txtPwd;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
    }
    private void asignarReferencias() {
        txtUser = findViewById(R.id.txtUser);
        txtPwd = findViewById(R.id.txtPwd);
        txtUbication = findViewById(R.id.txtUbication);
        txtUbication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, LocationRestaurant.class);
                startActivity(intent);
            }
        });
        txtContact = findViewById(R.id.txtContact);
        txtContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, ContactService.class);
                startActivity(intent);
            }
        });
        txtRegister = findViewById(R.id.txtRegister);
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, RegisterUser.class);
                startActivity(intent);
            }
        });
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loguearse();
            }
        });

    }
    private boolean loguearse(){
        boolean valid = true;
        String user = txtUser.getText().toString();
        String pwd = txtPwd.getText().toString();
        if(user.equals("")){
            txtUser.setError("Usuario Obligatorio");
            valid = false;
        }
        if(pwd.equals("")){
            txtUser.setError("Contraseña Obligatorio");
            valid = false;
        }

        if(valid){

        }

        return valid;
    }
}