package com.Medicine.service;

import com.Medicine.dao.CategoryDAO;
import com.Medicine.dao.DrugDAO;
import com.Medicine.dao.SaleDAO;
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
    @Autowired
    SaleDAO saleDAO;
    public String getAllMes(Class type,int limit, int offset){
        try{
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            Map<String,Object> map = new HashMap<>();
            Page page = new Page("",limit,offset);
            JSONObject jsonObject = new JSONObject();
            if(type.getSimpleName().equals("Drug")){
                jsonObject.put("data",drugDAO.selectByLimitAndOffset(page));;
                jsonObject.put("count",drugDAO.selectCount());
            }else if(type.getSimpleName().equals("Category")){
                jsonObject.put("data",categoryDAO.selectByLimitAndOffset(page));;
                jsonObject.put("count",categoryDAO.selectCount());
            } else if(type.getSimpleName().equals("Sale")){
                jsonObject.put("data",saleDAO.selectByLimitAndOffset(page));;
                jsonObject.put("count",saleDAO.selectCount());
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
            else if(type.getSimpleName().equals("Category"))
                categoryDAO.deleteById(id);
            else if(type.getSimpleName().equals("Sale"))
                saleDAO.deleteById(id);
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
            }else if(type.getSimpleName().equals("Category")){
                jsonObject.put("data", categoryDAO.selectByLikeValue(page));
                jsonObject.put("count", categoryDAO.selectCountByValue(LikeValue));
            }else if(type.getSimpleName().equals("Sale")){
                jsonObject.put("data", saleDAO.selectByLikeValue(page));
                jsonObject.put("count", saleDAO.selectCountByValue(LikeValue));
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
            }else if(object.getClass().getSimpleName().equals("Drug")){
                drugDAO.addDrug(object);
            }else if(object.getClass().getSimpleName().equals("Sale")){
                saleDAO.addSale(object);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    public boolean updateInfo(Object object) {
        try{
            if(object.getClass().getSimpleName().equals("Category")){
                categoryDAO.updateInfo(object);
            }else if(object.getClass().getSimpleName().equals("Drug")){
                drugDAO.updateInfo(object);
            }else if(object.getClass().getSimpleName().equals("Sale")){
                saleDAO.updateInfo(object);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
