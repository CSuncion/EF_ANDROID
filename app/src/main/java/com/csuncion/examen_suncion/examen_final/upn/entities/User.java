package com.csuncion.examen_suncion.examen_final.upn.entities;

public class User {
    private int id;
    private String firstname, lastname, mail, dni, sex, password;

    public User(int id, String firstname, String lastname, String mail, String dni, String sex, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.dni = dni;
        this.sex = sex;
        this.password = password;
    }

    public User(String firstname, String lastname, String mail, String dni, String sex, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.dni = dni;
        this.sex = sex;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
