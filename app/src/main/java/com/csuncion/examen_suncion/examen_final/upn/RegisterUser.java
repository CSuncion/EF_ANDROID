package com.csuncion.examen_suncion.examen_final.upn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.csuncion.examen_suncion.examen_final.upn.entities.User;
import com.csuncion.examen_suncion.examen_final.upn.models.DAORestaurant;

public class RegisterUser extends AppCompatActivity {

    EditText txtFirstName, txtLastName, txtDni, txtMail, txtPass;
    RadioButton rboM,rboF;
    Button btnSave;
    User user;
    DAORestaurant daoRestaurant = new DAORestaurant(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        daoRestaurant.openDB();
        asignarReferencias();
    }

    private void asignarReferencias(){
        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        txtDni = findViewById(R.id.txtDni);
        rboM = findViewById(R.id.rboM);
        rboF = findViewById(R.id.rboF);
        txtMail = findViewById(R.id.txtMail);
        txtPass = findViewById(R.id.txtPass);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(captureData()){
                    String exists = daoRestaurant.getExistUser(user);
                    if(exists.equals("")) {
                        String message = daoRestaurant.registerUser(user);
                        Toast.makeText(RegisterUser.this, message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterUser.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(RegisterUser.this, exists, Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
    private boolean captureData(){
        boolean valid = true;

        String firstName = txtFirstName.getText().toString();
        String lastName = txtLastName.getText().toString();
        String dni = txtDni.getText().toString();
        String sex = "";
        if(rboM.isChecked()){
            sex = "Masculino";
        }else{
            sex = "Femenino";
        }
        String mail = txtMail.getText().toString();
        String pwd = txtPass.getText().toString();

        if (firstName.equals("")){
            txtFirstName.setError("Nombres Obligatorio");
            valid = false;
        }
        if (lastName.equals("")){
            txtLastName.setError("Apellidos Obligatorio");
            valid = false;
        }
        if (dni.equals("")){
            txtDni.setError("DNI Obligatorio");
            valid = false;
        }
        if (sex.equals("")){
            rboM.setError("Sexo Obligatorio");
            valid = false;
        }
        if (mail.equals("")){
            txtMail.setError("E-Mail Obligatorio");
            valid = false;
        }
        if (pwd.equals("")){
            txtPass.setError("E-Mail Obligatorio");
            valid = false;
        }
        if (valid){
            user  = new User(firstName,lastName,mail,dni,sex,pwd);
        }
        return valid;
    }
}