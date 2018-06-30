package com.Medicine.model;

import java.util.Date;

public class Sale {

    public int getSellrecordnumber() {
        return sellrecordnumber;
    }

    public void setSellrecordnumber(int sellrecordnumber) {
        this.sellrecordnumber = sellrecordnumber;
    }

    public String getSelldate() {
        return selldate;
    }

    public void setSelldate(String selldate) {
        this.selldate = selldate;
    }

    public String getDrugnumber() {
        return drugnumber;
    }

    public void setDrugnumber(String drugnumber) {
        this.drugnumber = drugnumber;
    }

    public int getSellquantity() {
        return sellquantity;
    }

    public void setSellquantity(int sellquantity) {
        this.sellquantity = sellquantity;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Sale(Integer sellrecordnumber, String selldate, String drugnumber, Integer sellquantity, Double sellPrice, Integer userid) {
        this.sellrecordnumber = sellrecordnumber;
        this.selldate = selldate;
        this.drugnumber = drugnumber;
        this.sellquantity = sellquantity;
        this.sellPrice = sellPrice;
        this.userid = userid;
    }

    public Sale(String selldate, String drugnumber, Integer sellquantity, Double sellPrice, Integer userid) {
        this.selldate = selldate;
        this.drugnumber = drugnumber;
        this.sellquantity = sellquantity;
        this.sellPrice = sellPrice;
        this.userid = userid;
    }

    private int sellrecordnumber;
    private String selldate;
    private String drugnumber;
    private int sellquantity;
    private double sellPrice;
    private int userid;

}
