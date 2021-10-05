package com.csuncion.examen_suncion.examen_final.upn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class RegisterUser extends AppCompatActivity {

    EditText txtFirstName, txtLastName, txtDni, txtMail, txtPass;
    RadioButton rboM,rboF;
    Button btnSave;
    User user;
    //DAORestaurant daoRestaurant = new DAORestaurant(this);
    FirebaseDatabase firebaseDb;
    DatabaseReference dbReferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        initFirebase();
        //daoRestaurant.openDB();
        asignarReferencias();
    }

    private void initFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDb = FirebaseDatabase.getInstance();
        dbReferences = firebaseDb.getReference();
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
                    dbReferences.child("/user").orderByChild("mail").equalTo(txtMail.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                Toast.makeText(RegisterUser.this, "Usuario ya existe", Toast.LENGTH_SHORT).show();
                            }else{
                                dbReferences.child("user").child(user.getId()).setValue(user);
                                Toast.makeText(RegisterUser.this, "Se registró correctamente", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterUser.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
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
            user  = new User();
            user.setId(UUID.randomUUID().toString());
            user.setFirstname(firstName);
            user.setLastname(lastName);
            user.setMail(mail);
            user.setDni(dni);
            user.setSex(sex);
            user.setPassword(pwd);
            //user  = new User(firstName,lastName,mail,dni,sex,pwd);
        }
        return valid;
    }
}