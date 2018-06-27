package com.Medicine.service;

import com.Medicine.dao.DrugDAO;
import com.Medicine.model.Category;
import com.Medicine.model.Drug;
import com.Medicine.model.Page;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DrugManageService implements IManageService{
    @Autowired
    private DrugDAO drugDAO;
    @Override
    public String getAllMes(int limit, int offset){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        Page page = new Page("",limit,offset);
        List<Drug> drugs =  drugDAO.selectByLimitAndOffset(page);
        for(Drug drug : drugs){
            System.out.println(drug.getProductdate());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("data",drugs);
        jsonObject.put("count",drugDAO.selectCount());
        return jsonObject.toJSONString();
    }

    @Override
    public boolean DeleteById(int id) {
        try{
            drugDAO.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //@Override
    public String getAllMesByValue(String LikeValue,int limit, int offset){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        Page page = new Page(LikeValue,limit,offset);
        List<Drug> drugs =  drugDAO.selectByLikeValue(page);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("data",drugs);
        jsonObject.put("count",drugDAO.selectCountByValue(LikeValue));
        return jsonObject.toJSONString();
    }
}
