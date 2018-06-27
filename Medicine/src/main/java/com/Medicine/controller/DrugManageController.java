package com.Medicine.controller;

import com.Medicine.model.Drug;
import com.Medicine.model.HostHolder;
import com.Medicine.service.DrugManageService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DrugManageController {
    @Autowired
    DrugManageService drugManageService;
    @Autowired
    HostHolder hostHolder;


    @RequestMapping(path = {"/selectByPage.drug"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String selectByPage(Model model,
                               HttpServletRequest request,
                               HttpServletResponse response){

        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        String likeValue;
        if(request.getParameter("likevalue")!=null){
            likeValue = request.getParameter("likevalue");
            String result = null;
            if(likeValue.trim().length()>0 || likeValue != null)
                result = drugManageService.getAllMesByValue(Drug.class,likeValue,(limit-1),(page-1)*10);
            else
                result = drugManageService.getAllMes(Drug.class,limit,(page-1)*10);
            return result;
        }else{
            String result = drugManageService.getAllMes(Drug.class,limit,(page-1)*10);
            return result;
        }
    }
    @RequestMapping(path = {"/addDrug.drug"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String addCategory(Model model,
                              HttpServletRequest request,
                              HttpServletResponse response){
        JSONObject jsonObject  = new JSONObject();
        int drugid = Integer.parseInt(request.getParameter("drugid"));
        String drugnumber  = request.getParameter("drugnumber");
        String drugname  = request.getParameter("drugname");
        double drugPice  = Double.parseDouble(request.getParameter("drugPice"));
        int  quantity  = Integer.parseInt(request.getParameter("quantity"));
        String producer  = request.getParameter("producer");
        String categoryname  = request.getParameter("categoryname");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Drug drug = new Drug(drugid,drugnumber,drugname,drugPice,quantity,df.format(new Date()),producer,categoryname);
        if(hostHolder.getUser()!=null){
            jsonObject.put("isLogin",1);
            drugManageService.addObject(drug);
        }else{
            jsonObject.put("isLogin",0);
        }
        return jsonObject.toJSONString();
    }
}
