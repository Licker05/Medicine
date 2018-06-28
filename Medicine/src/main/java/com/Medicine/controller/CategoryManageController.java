package com.Medicine.controller;

import com.Medicine.model.Category;
import com.Medicine.model.HostHolder;
import com.Medicine.service.CategoryManageService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CategoryManageController {
    @Autowired
    CategoryManageService categoryManageService;
    @Autowired
    HostHolder hostHolder;

    @RequestMapping(path = {"/getCategoryTypes"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String  getCategoryTypes(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("data",categoryManageService.getCategoryTypes());
        return jsonObject.toJSONString();
    }
    @RequestMapping(path = {"/editCategory.category"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String EditById(Model model,
                           @RequestParam("cname") String cname,
                           @RequestParam("description") String description,
                           @RequestParam("id") int id,
                           HttpServletResponse response){
        Category category = new Category(id,cname,"none",description);
        JSONObject jsonObject = new JSONObject();
        if(categoryManageService.updateInfo(category)){
            jsonObject.put("state",1);
            return jsonObject.toJSONString();
        }else{
            jsonObject.put("state",0);
            return jsonObject.toJSONString();
        }

    }
}
