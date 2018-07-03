package com.Medicine.service;

import com.Medicine.dao.CategoryDAO;
import com.Medicine.model.Category;
import com.Medicine.model.Page;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryManageService extends AbstractManageService{
    @Autowired
    private CategoryDAO categoryDAO;
    public List<String> getCategoryTypes(){
        return categoryDAO.selectTypes();
    }

}
