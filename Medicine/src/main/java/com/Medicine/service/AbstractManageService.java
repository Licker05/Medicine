package com.Medicine.service;

import com.Medicine.dao.CategoryDAO;
import com.Medicine.dao.DrugDAO;
import com.Medicine.model.Category;
import com.Medicine.model.Drug;
import com.Medicine.model.Page;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractManageService {
    @Autowired
    CategoryDAO categoryDAO;
    @Autowired
    DrugDAO drugDAO;
    public String getAllMes(Class type,int limit, int offset){
        try{
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            Map<String,Object> map = new HashMap<>();
            Page page = new Page("",limit,offset);
            JSONObject jsonObject = new JSONObject();
            if(type.getSimpleName().equals("Drug")){
                jsonObject.put("data",drugDAO.selectByLimitAndOffset(page));;
               jsonObject.put("count",drugDAO.selectCount());
            }else{
                jsonObject.put("data",categoryDAO.selectByLimitAndOffset(page));;
                jsonObject.put("count",categoryDAO.selectCount());
            }
            jsonObject.put("code",0);
            jsonObject.put("msg","");
            return jsonObject.toJSONString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public boolean DeleteById(Class type,int id){
        try{
            if(type.getSimpleName().equals("Drug"))
                drugDAO.deleteById(id);
            else
                categoryDAO.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String getAllMesByValue(Class type,String LikeValue, int limit, int offset) {
        try{
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            Map<String,Object> map = new HashMap<>();
            Page page = new Page(LikeValue,limit,offset);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",0);
            jsonObject.put("msg","");
            if(type.getSimpleName().equals("Drug")) {
                jsonObject.put("data",drugDAO.selectByLikeValue(page));
                jsonObject.put("count",drugDAO.selectCountByValue(LikeValue));
            }else {
                jsonObject.put("data", categoryDAO.selectByLikeValue(page));
                jsonObject.put("count", categoryDAO.selectCountByValue(LikeValue));
            }
            return jsonObject.toJSONString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public boolean addObject(Object object){
        try{
            if(object.getClass().getSimpleName().equals("Category")){
                categoryDAO.addCategory(object);
            }else{

            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
