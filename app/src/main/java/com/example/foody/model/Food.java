package com.example.foody.model;

public class Food {
    private String id;
    private String name;
    private int price;
    private String image;
    private String idStore;

    public Food() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getIdStore() {
        return idStore;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIdStore(String idStore) {
        this.idStore = idStore;
    }

    public String getStringPrice(int price) {
        String str = String.format("%,d", price).replace('.', ',');
        return str + "đ";
    }
}
