package com.example.foody.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Store implements Parcelable {
    private int id;
    private String name;
    private int image;
    private String description;
    private String address;

    public Store(int id, String name, int image, String description, String address) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.address = address;
    }

    protected Store(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readInt();
        description = in.readString();
        address = in.readString();
    }

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(image);
        dest.writeString(description);
        dest.writeString(address);
    }
}
