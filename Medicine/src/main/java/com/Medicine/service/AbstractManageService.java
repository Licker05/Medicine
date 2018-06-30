package com.Medicine.service;

import com.Medicine.dao.CategoryDAO;
import com.Medicine.dao.DrugDAO;
import com.Medicine.dao.SaleDAO;
import com.Medicine.dao.UserDAO;
import com.Medicine.model.*;
import com.Medicine.utils.JSONUtil;
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
    @Autowired
    UserDAO userDAO;
    @Autowired
    HostHolder hostHolder;
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
            }else if(type.getSimpleName().equals("User")){
                jsonObject.put("data",userDAO.selectByLimitAndOffset(page));;
                jsonObject.put("count",userDAO.selectCount());
            }
            jsonObject.put("code",0);
            jsonObject.put("msg","");
            return jsonObject.toJSONString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public String DeleteById(Class type,int id){
        try{
            if(type.getSimpleName().equals("Drug"))
                drugDAO.deleteById(id);
            else if(type.getSimpleName().equals("Category"))
                categoryDAO.deleteById(id);
            else if(type.getSimpleName().equals("Sale"))
                saleDAO.deleteById(id);
            else if(type.getSimpleName().equals("User")){

                if(hostHolder.getUser().getId()==id){
                    return JSONUtil.getObjectJSONString(0,"msg","不可以删除当前登录用户");
                }else{
                    User user = userDAO.selectById(id);
                    if(user.getLevel().equals("最高权限")){
                        return JSONUtil.getObjectJSONString(0,"msg","不可以删除最高权限用户");
                    }
                    if(user.getLevel().equals("管理员") && hostHolder.getUser().getLevel().equals("管理员")){
                        return JSONUtil.getObjectJSONString(0,"msg","权限不足,请联系最高权限");
                    }
                    userDAO.deleteById(id);
                }
            }
            return JSONUtil.getObjectJSONString(1,"msg","成功");
        }catch (Exception e){
            e.printStackTrace();
            return JSONUtil.getObjectJSONString(0,"msg","服务器错误");
        }
    }
    public Object selectById(Class type,int id){
        try{
            if(type.getSimpleName().equals("Drug"))
                return drugDAO.selectById(id);
            else if(type.getSimpleName().equals("Category"))
                return categoryDAO.selectById(id);
            else if(type.getSimpleName().equals("Sale"))
                return saleDAO.selectById(id);
            else if(type.getSimpleName().equals("User"))
                return userDAO.selectById(id);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
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
            }else if(type.getSimpleName().equals("User")){
                jsonObject.put("data", userDAO.selectByLikeValue(page));
                jsonObject.put("count", userDAO.selectCountByValue(LikeValue));
            }
            return jsonObject.toJSONString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public String addObject(Object object){
        try{
            if(object.getClass().getSimpleName().equals("Category")){
                if((categoryDAO.selectByCname(((Category)object).getCname())!=null))
                    return JSONUtil.getObjectJSONString(0,"msg","你所增加的药品类别已经存在");
                else
                    categoryDAO.addCategory(object);
            }else if(object.getClass().getSimpleName().equals("Drug")){
                if((drugDAO.selectByDrugName(((Drug)object).getDrugname())!=null))
                    return JSONUtil.getObjectJSONString(0,"msg","你所增加的药品已经存在");
                else
                    drugDAO.addDrug(object);
            }else if(object.getClass().getSimpleName().equals("Sale")){
                saleDAO.addSale(object);
            }else if(object.getClass().getSimpleName().equals("User")){
                if(((User)object).getLevel().equals("管理员") && hostHolder.getUser().getLevel().equals("管理员")){
                    return JSONUtil.getObjectJSONString(0,"msg","你的权限不足,无法新增管理员");
                }else{
                    if(userDAO.selectByName(((User)object).getName())!=null){
                        return JSONUtil.getObjectJSONString(0,"msg","对不起,该用户名已经存在");
                    }else
                        userDAO.addUser(object);
                }
            }
            return JSONUtil.getObjectJSONString(1,"msg","新增成功");
        }catch (Exception e){
            e.printStackTrace();
            return JSONUtil.getObjectJSONString(0,"msg","新增失败");
        }
    }
    public String updateInfo(Object object) {
        try{
            if(object.getClass().getSimpleName().equals("Category")){
                categoryDAO.updateInfo(object);
            }else if(object.getClass().getSimpleName().equals("Drug")){
                drugDAO.updateInfo(object);
            }else if(object.getClass().getSimpleName().equals("Sale")){
                saleDAO.updateInfo(object);
            }else if(object.getClass().getSimpleName().equals("User")){
                if(((User)object).getName()==null){
                    if(((User)object).getLevel().equals("管理员") && hostHolder.getUser().getLevel().equals("管理员"))
                        return JSONUtil.getObjectJSONString(0,"msg","你的权限不足,无法重置管理员密码");
                    String newPass = ((User)object).getPassword();
                    User user = userDAO.selectById(((User)object).getId());
                    user.setPassword(JSONUtil.MD5(newPass+user.getSalt()));
                    userDAO.updatePassword(user);
                }else{
                    if(((User)object).getLevel().equals("管理员") && hostHolder.getUser().getLevel().equals("管理员"))
                        return JSONUtil.getObjectJSONString(0,"msg","你的权限不足,无法新增管理员");
                    else
                        userDAO.updateInfo(object);
                }
            }
            return JSONUtil.getObjectJSONString(1,"msg","成功");
        }catch (Exception e){
            e.printStackTrace();
            return JSONUtil.getObjectJSONString(0,"msg","服务器错误");
        }
    }
}
