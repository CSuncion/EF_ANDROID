package com.csuncion.examen_suncion.examen_final.upn.entities;

public class Contact {
    private int id;
    private String fullName, phone, subject, message;

    public Contact(int id, String fullName, String phone, String subject, String message) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.subject = subject;
        this.message = message;
    }

    public Contact(String fullName, String phone, String subject, String message) {
        this.fullName = fullName;
        this.phone = phone;
        this.subject = subject;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
