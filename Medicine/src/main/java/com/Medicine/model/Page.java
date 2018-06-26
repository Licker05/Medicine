package com.Medicine.model;

public class Page {

    public String getLikeValue() {
        return likeValue;
    }

    public void setLikeValue(String likeValue) {
        this.likeValue = likeValue;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Page(String likeValue, int limit, int offset) {
        this.likeValue = likeValue;
        this.limit = limit;
        this.offset = offset;
    }

    private String likeValue;
    private int limit;
    private int offset;
}
