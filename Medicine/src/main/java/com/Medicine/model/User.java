package com.Medicine.model;

public class User {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public User(int id, String name, String nickname, String phone, String level) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.nickname = nickname;
        this.level = level;
    }
    public User(){

    }
    private int id;

    public User(String name, String nickname, String password, String salt, String headurl, String phone, String email, String level) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.salt = salt;
        this.headurl = headurl;
        this.phone = phone;
        this.email = email;
        this.level = level;
    }

    public User(int id, String name, String nickname, String phone, String email, String level) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.level = level;
    }

    public User(String name, String nickname, String headurl, String phone, String email) {
        this.name = name;
        this.nickname = nickname;
        this.headurl = headurl;
        this.phone = phone;
        this.email = email;
    }

    private String name;
    private String nickname;
    private String password;
    private String salt;
    private String headurl;
    private String phone;
    private String email;
    private String level;
}
