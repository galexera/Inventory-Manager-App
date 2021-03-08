package com.example.inventorymanager.model;

import java.util.ArrayList;

public class registry {
    private int id1;
    private String customerName;
    private ArrayList<stock> lists;
    private int tprice;
    private String datatime;
    private String status;

    public registry(int id1, String customerName, ArrayList<stock> lists, int tprice, String datatime, String status) {
        this.id1=id1;
        this.customerName=customerName;
        this.lists=lists;
        this.tprice=tprice;
        this.datatime=datatime;
        this.status=status;
    }

    public registry(String customerName, ArrayList<stock> lists, int tprice, String datatime, String status) {
        this.customerName=customerName;
        this.lists=lists;
        this.tprice=tprice;
        this.datatime=datatime;
        this.status=status;
    }

    public registry() {
    }

    public int getId1() {
        return id1;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<stock> getLists() {
        return lists;
    }

    public int getTprice() {
        return tprice;
    }

    public String getDatatime() {
        return datatime;
    }

    public String getStatus() {
        return status;
    }

    public void setId1(int id1) {
        this.id1=id1;
    }

    public void setCustomerName(String customerName) {
        this.customerName=customerName;
    }

    public void setLists(ArrayList<stock> lists) {
        this.lists=lists;
    }

    public void setTprice(int tprice) {
        this.tprice=tprice;
    }

    public void setDatatime(String datatime) {
        this.datatime=datatime;
    }

    public void setStatus(String status) {
        this.status=status;
    }
}

