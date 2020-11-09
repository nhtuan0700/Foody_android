package com.example.foody.model;

public class Store {
    private String name;
    private int thumbnail;
    private String description;
    private String address;

    public Store(String name, int thumbnail, String description, String address) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.description = description;
        this.address = address;
    }

    public Store(String name, int thumbnail, String description) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
