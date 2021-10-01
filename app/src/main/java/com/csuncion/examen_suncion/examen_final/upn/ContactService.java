package com.csuncion.examen_suncion.examen_final.upn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.csuncion.examen_suncion.examen_final.upn.entities.Contact;
import com.csuncion.examen_suncion.examen_final.upn.models.DAORestaurant;

public class ContactService extends AppCompatActivity {

    EditText txtFullName, txtPhone, txtSubject, txtMessage;
    Button btnSend;
    Contact contact;
    DAORestaurant daoRestaurant = new DAORestaurant(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_service);
        daoRestaurant.openDB();
        asignarReferencia();
    }
    private void asignarReferencia() {
        txtFullName = findViewById(R.id.txtFullName);
        txtPhone = findViewById(R.id.txtPhone);
        txtSubject = findViewById(R.id.txtSubject);
        txtMessage = findViewById(R.id.txtMessage);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(capturaDatos()){
                    String message = daoRestaurant.registerContact(contact);
                    Toast.makeText(ContactService.this,message,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ContactService.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    private boolean capturaDatos(){
        boolean valid = true;

        String fullname = txtFullName.getText().toString();
        String phone = txtPhone.getText().toString();
        String subject = txtSubject.getText().toString();
        String message = txtMessage.getText().toString();

        if (fullname.equals("")){
            txtFullName.setError("Nombres Obligatorio");
            valid = false;
        }
        if (phone.equals("")){
            txtPhone.setError("Nombres Obligatorio");
            valid = false;
        }
        if (subject.equals("")){
            txtSubject.setError("Nombres Obligatorio");
            valid = false;
        }
        if (message.equals("")){
            txtMessage.setError("Nombres Obligatorio");
            valid = false;
        }
        if (valid) {
            contact = new Contact(fullname, phone, subject, message);
        }
        return valid;
    }
}