package com.example.foody.model;

public class Cart {
    private String idUser;
    private Food food;
    private int qty;
    private int ammount;

    public Cart() {};

    public Cart(String idUser, Food food, int qty, int ammount) {
        this.idUser = idUser;
        this.food = food;
        this.qty = qty;
        this.ammount = ammount;
    }

    public String getIdUser() {
        return idUser;
    }

    public Food getFood() {
        return food;
    }

    public int getQty() {
        return qty;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }
}
