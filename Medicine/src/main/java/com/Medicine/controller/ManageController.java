package com.Medicine.controller;
import com.Medicine.model.Category;
import com.Medicine.model.Drug;
import com.Medicine.model.HostHolder;
import com.Medicine.service.AbstractManageService;
import com.Medicine.service.CategoryManageService;
import com.Medicine.service.DrugManageService;
import com.Medicine.service.ManageService;
import com.Medicine.utils.GetModelInfoUtil;
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
    @Autowired
    HostHolder hostHolder;
    @Autowired
    ManageService manageService;
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
        try {
            if(manageService.DeleteById(Class.forName("com.Medicine.model."+type),id)){
                return JSONUtil.getStateString(1);
            }else{
                return JSONUtil.getStateString(0);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(path = {"/addInfo.{type}"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String addInfo(Model model,
                              @PathVariable("type") String type,
                              HttpServletRequest request,
                              HttpServletResponse response){
        JSONObject jsonObject  = new JSONObject();
        Object object = GetModelInfoUtil.getModelInfo(type,request);
        try {
            if(manageService.addObject(object)){
                return JSONUtil.getStateString(1);
            }else{
                return JSONUtil.getStateString(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping(path = {"/selectByPage.{type}"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String selectByPage(Model model,
                               @PathVariable("type") String type,
                               HttpServletRequest request,
                               HttpServletResponse response){
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        String likeValue = request.getParameter("likevalue");
        String result = null;
        try {
            Class typeClass = Class.forName("com.Medicine.model."+type);
            if(likeValue!=null && likeValue.trim().length()>0){
                likeValue = request.getParameter("likevalue");
                return manageService.getAllMesByValue(typeClass,likeValue,(limit-1),(page-1)*10);
            }else{
                return manageService.getAllMes(typeClass,limit,(page-1)*10);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return result;
        }
    }
    @RequestMapping(path = {"/updateInfo.{type}"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String updateInfo(Model model,
                          @PathVariable("type") String type,
                          HttpServletRequest request,
                          HttpServletResponse response){
        JSONObject jsonObject  = new JSONObject();
        Object object = GetModelInfoUtil.getModelInfo(type,request);
        if(manageService.updateInfo(object)==true){
            return JSONUtil.getStateString(1);
        }
        return JSONUtil.getStateString(0);
    }
}
