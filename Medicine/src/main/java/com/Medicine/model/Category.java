package com.Medicine.model;

public class Category {


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category(Integer id, String cname, String createtime, String description) {
        this.id = id;
        this.cname = cname;
        this.createtime = createtime;
        this.description = description;
    }



    public Category(String cname, String createtime, String description) {
        this.cname = cname;
        this.createtime = createtime;
        this.description = description;
    }
    private Integer id;
    private String cname;
    private String createtime;
    private String description;
}
