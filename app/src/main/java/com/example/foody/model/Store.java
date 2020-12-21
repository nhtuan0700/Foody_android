package com.example.foody.model;

public class Store {
    private String id;
    private String name;
    private String address;
    private String timeWork;
    private String image;
    private double pointEval;
    private String rangePrice;
    private String idCateStore;

    public Store() {
    }

    public Store(String id, String name, String address, String timeWork, String image, double pointEval, String rangePrice, String idCateStore) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.timeWork = timeWork;
        this.image = image;
        this.pointEval = pointEval;
        this.rangePrice = rangePrice;
        this.idCateStore = idCateStore;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTimeWork() {
        return timeWork;
    }

    public String getImage() {
        return image;
    }

    public double getPointEval() {
        return pointEval;
    }

    public String getRangePrice() {
        return rangePrice;
    }

    public String getIdCateStore() {
        return idCateStore;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTimeWork(String timeWork) {
        this.timeWork = timeWork;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPointEval(double pointEval) {
        this.pointEval = pointEval;
    }

    public void setRangePrice(String rangePrice) {
        this.rangePrice = rangePrice;
    }

    public void setIdCateStore(String idCateStore) {
        this.idCateStore = idCateStore;
    }

}
