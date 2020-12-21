package com.example.foody.model;

public class CollectionItem {
    private int id;
    private String title;
    private String image;

    public CollectionItem() {
    }

    public CollectionItem(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public CollectionItem(int id, String image,String title) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
