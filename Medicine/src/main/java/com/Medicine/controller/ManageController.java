package com.Medicine.controller;
import com.Medicine.model.Category;
import com.Medicine.model.Drug;
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
    @Autowired
    HostHolder hostHolder;
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
            if(drugManageService.DeleteById(Drug.class,id)){
                return JSONUtil.getStateString(1);
            }else{
                return JSONUtil.getStateString(0);
            }
        }
        return null;
    }

    @RequestMapping(path = {"/addInfo.{type}"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String addCategory(Model model,
                              @PathVariable("type") String type,
                              HttpServletRequest request,
                              HttpServletResponse response){
        JSONObject jsonObject  = new JSONObject();
        if(type.equals("drug")){
            String drugnumber  = request.getParameter("drugnumber");
            String drugname  = request.getParameter("drugname");
            double drugPice  = Double.parseDouble(request.getParameter("drugPice"));
            int  quantity  = Integer.parseInt(request.getParameter("quantity"));
            String producer  = request.getParameter("producer");
            String categoryname  = request.getParameter("categoryname");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            Drug drug = new Drug(drugnumber,drugname,drugPice,quantity,df.format(new Date()),producer,categoryname);
            if(drugManageService.addObject(drug)==true){
                jsonObject.put("isLogin",1);
                return jsonObject.toJSONString();
            }
        }else if(type.equals("category")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String cname = request.getParameter("cname");
            String description = request.getParameter("description");
            Category category = new Category(cname,df.format(new Date()),description);
            if(categoryManageService.addObject(category)==true){
                jsonObject.put("isLogin",1);
                return jsonObject.toJSONString();
            }
        }
        jsonObject.put("isLogin",0);
        return jsonObject.toJSONString();
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
        if(likeValue!=null && likeValue.trim().length()>0){
            likeValue = request.getParameter("likevalue");
            if(type.equals("drug")){
                return result = drugManageService.getAllMesByValue(Drug.class,likeValue,(limit-1),(page-1)*10);
            }else if(type.equals("category")){
                return result = categoryManageService.getAllMesByValue(Category.class,likeValue,(limit-1),(page-1)*10);
            }
        }else{
            if(type.equals("drug"))
                result = drugManageService.getAllMes(Drug.class,limit,(page-1)*10);
            else if(type.equals("category"))
                result = categoryManageService.getAllMes(Category.class,limit,(page-1)*10);
            return result;
        }
        return result;
    }
}
