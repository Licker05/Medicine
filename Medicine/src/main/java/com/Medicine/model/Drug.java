package com.Medicine.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

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



    public Drug(String drugnumber, String drugname, double drugPice, int quantity, String productdate, String producer, String categoryname) {
        this.drugnumber = drugnumber;
        this.drugname = drugname;
        this.drugPice = drugPice;
        this.quantity = quantity;
        this.productdate = productdate;
        this.producer = producer;
        this.categoryname = categoryname;
    }

    //药品ID
    private int drugid;
    //药品编号
    private String drugnumber;
    //药品名称
    private String drugname;
    //药品价格
//    @DecimalMin(value = "0",message="商品价格错误")
//    @DecimalMax(value = "0x7fffffff",message="商品价格错误")
    private double drugPice;
    //药品库存
//    @Min(value = 0,message="商品数量错误")
//    @Max(value = Integer.MAX_VALUE,message="商品数量错误")
    private int quantity;
    //生产日期
    private String productdate;
    //产商
    private String producer;
    //类别名称
    private String categoryname;
}
