package com.Medicine.controller;

import com.Medicine.model.Drug;
import com.Medicine.model.HostHolder;
import com.Medicine.service.DrugManageService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Controller
public class DrugManageController {
    @Autowired
    DrugManageService drugManageService;
    @Autowired
    HostHolder hostHolder;
    @RequestMapping(path = "/Drug_toEdit{drugid}",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView EditDrug(
                           @PathVariable("drugid") int drugid,
                           HttpServletResponse response){
        HashMap<String,String> map = new HashMap<String, String>();
        ModelAndView modelAndView = new ModelAndView();
        Drug  drug = drugManageService.selectById(drugid);
        modelAndView.setViewName("/Drug_toEdit");
        modelAndView.addObject("drug",drug);
        return modelAndView;
    }
}
