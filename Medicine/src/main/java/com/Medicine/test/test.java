package com.Medicine.test;

import com.Medicine.dao.BaseDAO;
import com.Medicine.dao.DrugDAO;
import com.Medicine.utils.JedisAdapter;
import org.springframework.beans.factory.annotation.Autowired;

public class test {
    @Autowired
    DrugDAO drugDAO;
//    @Autowired
//    private JedisAdapter jedisAdapter;
//    public static void main(String[] args) {
//        jedisAdapter.set("test","test");
//    }
}
