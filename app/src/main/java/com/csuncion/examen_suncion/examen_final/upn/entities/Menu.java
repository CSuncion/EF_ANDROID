package com.csuncion.examen_suncion.examen_final.upn.entities;

public class Menu {
    private int id, codMenu, codFood;
    private String food, input, mail;
    private Double priceFood, priceInput, priceTotal;
    private int countFood, countInput, countTotal;

    public Menu(int id, int codMenu, int codFood, String food, String input, String mail, Double priceFood, Double priceInput, Double priceTotal, int countFood, int countInput, int countTotal) {
        this.id = id;
        this.codMenu = codMenu;
        this.codFood = codFood;
        this.food = food;
        this.input = input;
        this.mail = mail;
        this.priceFood = priceFood;
        this.priceInput = priceInput;
        this.priceTotal = priceTotal;
        this.countFood = countFood;
        this.countInput = countInput;
        this.countTotal = countTotal;
    }

    public Menu(int codMenu, int codFood, String food, String input, String mail, Double priceFood, Double priceInput, Double priceTotal, int countFood, int countInput, int countTotal) {
        this.codMenu = codMenu;
        this.codFood = codFood;
        this.food = food;
        this.input = input;
        this.mail = mail;
        this.priceFood = priceFood;
        this.priceInput = priceInput;
        this.priceTotal = priceTotal;
        this.countFood = countFood;
        this.countInput = countInput;
        this.countTotal = countTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodMenu() {
        return codMenu;
    }

    public void setCodMenu(int codMenu) {
        this.codMenu = codMenu;
    }

    public int getCodFood() {
        return codFood;
    }

    public void setCodFood(int codFood) {
        this.codFood = codFood;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Double getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(Double priceFood) {
        this.priceFood = priceFood;
    }

    public Double getPriceInput() {
        return priceInput;
    }

    public void setPriceInput(Double priceInput) {
        this.priceInput = priceInput;
    }

    public Double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public int getCountFood() {
        return countFood;
    }

    public void setCountFood(int countFood) {
        this.countFood = countFood;
    }

    public int getCountInput() {
        return countInput;
    }

    public void setCountInput(int countInput) {
        this.countInput = countInput;
    }

    public int getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(int countTotal) {
        this.countTotal = countTotal;
    }
}
