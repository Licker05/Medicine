package com.Medicine.test;

public class test {
    public static void main(String[] args) {
        try {
            Class type = Class.forName("com.Medicine.model.Drug");
            System.out.println(type.getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
