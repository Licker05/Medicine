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
public class CategoryManageService implements IManageService{
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public String getAllMes(int limit, int offset){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        Page page = new Page("",limit,offset);
        List<Category> categorys =  categoryDAO.selectByLimitAndOffset(page);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("data",categorys);
        jsonObject.put("count",categoryDAO.selectCount());
        return jsonObject.toJSONString();
    }
    @Override
    public String getAllMesByValue(String LikeValue,int limit, int offset){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        Page page = new Page(LikeValue,limit,offset);
        List<Category> categorys =  categoryDAO.selectByLikeValue(page);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("data",categorys);
        jsonObject.put("count",categoryDAO.selectCountByValue(LikeValue));
        return jsonObject.toJSONString();
    }
    public boolean addCategory(Category category) {
        try{
            categoryDAO.addCategory(category);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateNameAndDesc(Category category) {
        try{
            categoryDAO.updateNameAndDesc(category);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean DeleteById(int id) {
        try{
            categoryDAO.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
