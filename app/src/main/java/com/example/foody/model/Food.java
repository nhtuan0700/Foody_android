package com.example.foody.model;

import java.util.ArrayList;

public class Food {
    private String name;
    private int price;
    private int image;
    private int idStore;

    public Food(String name, int price, int image, int idStore) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.idStore = idStore;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public String getAddress() {
        ListStore stores = new ListStore();
        for (Store store : stores.getListStore()){
            if(store.getId() == idStore) {
                return store.getAddress();
            }
        }
        return "";
    }
}
