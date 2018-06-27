package com.Medicine.controller;

import com.Medicine.service.DrugManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DrugManageController {
    @Autowired
    DrugManageService drugManageService;

    @RequestMapping(path = {"/Drug_selectByPage"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String Drug_selectByPage(){
        return "Drug_selectByPage";
    }

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
                result = drugManageService.getAllMesByValue(likeValue,(limit-1),(page-1)*10);
            else
                result = drugManageService.getAllMes(limit,(page-1)*10);
            return result;
        }else{
            String result = drugManageService.getAllMes(limit,(page-1)*10);
            return result;
        }
    }
}
