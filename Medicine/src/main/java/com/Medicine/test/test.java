package com.Medicine.test;

import com.Medicine.dao.BaseDAO;
import com.Medicine.dao.DrugDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class test {
    @Autowired
    DrugDAO drugDAO;

    public static void main(String[] args) {

        try {
//            baseDAO.equals("hello");
            Class type = Class.forName("com.Medicine.dao.DrugDAO");
//            Object object = (Object)drugDAO;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
