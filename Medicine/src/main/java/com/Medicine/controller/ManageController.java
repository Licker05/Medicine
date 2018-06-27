package com.Medicine.controller;
import com.Medicine.model.Category;
import com.Medicine.model.HostHolder;
import com.Medicine.service.CategoryManageService;
import com.Medicine.service.DrugManageService;
import com.Medicine.utils.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ManageController {
    @Autowired
    CategoryManageService categoryManageService;
    @Autowired
    DrugManageService drugManageService;
    //查看页面
    @RequestMapping(path = {"/{name}_selectByPage"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String Category_selectByPage(@PathVariable(value = "name")String name){
        return name+"_selectByPage";
    }
    //增加页面
    @RequestMapping(path = {"/{name}_toAdd"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String Category_toAdd(@PathVariable(value = "name")String name){
        return name+"_toAdd";
    }

    @RequestMapping(path = {"/deleteInfo.{type}"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String DeleteById(Model model,
                             @PathVariable(value = "type")String type,
                             @RequestParam("id") int id,
                             HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        if(type.equals("category")){
            if(categoryManageService.DeleteById(Category.class,id)){
                return JSONUtil.getStateString(1);
            }else{
                return JSONUtil.getStateString(0);
            }
        }else if(type.equals("drug")){
            if(drugManageService.DeleteById(Category.class,id)){
                return JSONUtil.getStateString(1);
            }else{
                return JSONUtil.getStateString(0);
            }
        }
        return null;
    }
}
