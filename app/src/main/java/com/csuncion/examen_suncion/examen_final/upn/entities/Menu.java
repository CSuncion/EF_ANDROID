package com.csuncion.examen_suncion.examen_final.upn.entities;

public class Menu {
    private int id, codMenu, codFood;
    private String food, description,detail,category;
    private Float price;
    private int count, status;

    public Menu(int id, int codMenu, int codFood, String food, String description, String detail, String category, Float price, int count, int status) {
        this.id = id;
        this.codMenu = codMenu;
        this.codFood = codFood;
        this.food = food;
        this.description = description;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.count = count;
        this.status = status;
    }

    public Menu(int codMenu, int codFood, String food, String description, String detail, String category, Float price, int count, int status) {
        this.codMenu = codMenu;
        this.codFood = codFood;
        this.food = food;
        this.description = description;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.count = count;
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
