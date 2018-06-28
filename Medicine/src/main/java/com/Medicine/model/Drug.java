package com.Medicine.model;

public class Drug {


    public int getDrugid() {
        return drugid;
    }

    public void setDrugid(int drugid) {
        this.drugid = drugid;
    }

    public String getDrugnumber() {
        return drugnumber;
    }

    public void setDrugnumber(String drugnumber) {
        this.drugnumber = drugnumber;
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public double getDrugPice() {
        return drugPice;
    }

    public void setDrugPice(double drugPice) {
        this.drugPice = drugPice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductdate() {
        return productdate;
    }

    public void setProductdate(String productdate) {
        this.productdate = productdate;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Drug(Integer drugid, String drugnumber, String drugname, Double drugPice, Integer quantity, String productdate, String producer, String categoryname) {
        this.drugid = drugid;
        this.drugnumber = drugnumber;
        this.drugname = drugname;
        this.drugPice = drugPice;
        this.quantity = quantity;
        this.productdate = productdate;
        this.producer = producer;
        this.categoryname = categoryname;
    }

    private int drugid;

    public Drug(String drugnumber, String drugname, double drugPice, int quantity, String productdate, String producer, String categoryname) {
        this.drugnumber = drugnumber;
        this.drugname = drugname;
        this.drugPice = drugPice;
        this.quantity = quantity;
        this.productdate = productdate;
        this.producer = producer;
        this.categoryname = categoryname;
    }

    private String drugnumber;
    private String drugname;
    private double drugPice;
    private int quantity;
    private String productdate;
    private String producer;
    private String categoryname;
}
