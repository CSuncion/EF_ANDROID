package com.csuncion.examen_suncion.examen_final.upn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.csuncion.examen_suncion.examen_final.upn.entities.User;
import com.csuncion.examen_suncion.examen_final.upn.models.DAORestaurant;
import com.csuncion.examen_suncion.examen_final.upn.util.Constant;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText txtUser, txtPwd;
    TextView txtUbication, txtContact, txtRegister, txtNoUser;
    Button btnLogin;
    User user;
    DAORestaurant daoRestaurant = new DAORestaurant(this);
    FirebaseDatabase firebaseDb;
    DatabaseReference dbReferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daoRestaurant.openDB();
        initFirebase();
        asignarReferencias();
    }

    private void initFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDb = FirebaseDatabase.getInstance();
        dbReferences = firebaseDb.getReference();
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
                if(loguearse()){
                    //String exists = daoRestaurant.getUser(user);
                    dbReferences.child("/user").orderByChild("mail").equalTo(txtUser.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                for (DataSnapshot item:snapshot.getChildren()){
                                    User u = item.getValue(User.class);
                                    Toast.makeText(MainActivity.this, "Bienvenido " + u.getFirstname(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, MenuFood.class);
                                    intent.putExtra("mail", txtUser.getText().toString() + "");
                                    Constant.NAME_USER = txtUser.getText().toString();
                                    startActivity(intent);
                                }
                            }else{
                                Toast.makeText(MainActivity.this, "Debe registrarse", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        txtNoUser = findViewById(R.id.txtNoUser);
        txtNoUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, MenuFood.class);
                intent.putExtra("mail", txtNoUser.getText().toString() + "");
                Constant.NAME_USER = txtNoUser.getText().toString();
                startActivity(intent);
            }
        });
    }
    private boolean loguearse(){
        boolean valid = true;
        String usr = txtUser.getText().toString();
        String pwd = txtPwd.getText().toString();
        if(usr.equals("")){
            txtUser.setError("Usuario Obligatorio");
            valid = false;
        }
        if(pwd.equals("")){
            txtPwd.setError("Contraseña Obligatorio");
            valid = false;
        }

        if(valid){
            //user = new User("","",usr,"","",pwd);
        }

        return valid;
    }
}