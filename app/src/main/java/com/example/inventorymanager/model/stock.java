package com.example.inventorymanager.model;

import android.os.Parcel;
import android.os.Parcelable;

public class stock implements Parcelable {
    private int id;
    private String name;
    private int quantity;
    private int price;

    public stock() {

    }
    public stock(int id, String name, int quantity, int price) {
        this.id=id;
        this.name=name;
        this.quantity=quantity;
        this.price=price;
    }


    public stock(String name, int quantity, int price) {
        this.name=name;
        this.quantity=quantity;
        this.price=price;
    }

    public stock(String name, int price) {
        this.name=name;
        this.price=price;
    }

    protected stock(Parcel in) {
        id=in.readInt();
        name=in.readString();
        quantity=in.readInt();
        price=in.readInt();
    }

    public static final Creator<stock> CREATOR=new Creator<stock>() {
        @Override
        public stock createFromParcel(Parcel in) {
            return new stock(in);
        }

        @Override
        public stock[] newArray(int size) {
            return new stock[size];
        }
    };

    public int getId() {
        return id;
    }


    public  String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id=id;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setQuantity(int quantity) {
        this.quantity=quantity;
    }

    public void setPrice(int price) {
        this.price=price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(quantity);
        parcel.writeInt(price);
    }
}

