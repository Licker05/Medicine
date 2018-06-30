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
}
