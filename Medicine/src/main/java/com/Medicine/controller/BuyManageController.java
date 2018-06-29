package com.Medicine.controller;

import com.Medicine.model.Drug;
import com.Medicine.model.HostHolder;
import com.Medicine.model.Sale;
import com.Medicine.service.BuyManageService;
import com.Medicine.service.DrugManageService;
import com.Medicine.utils.JSONUtil;
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
public class BuyManageController {
    @Autowired
    BuyManageService buyManageService;
    @Autowired
    HostHolder hostHolder;
    @RequestMapping(path = {"/editBuy.buy"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String selectByPage(Model model,
                               @RequestParam("id") int drugid,
                               @RequestParam("editQuantity") int editQuantity,
                               @RequestParam("drugnumber") String drugnumber,
                               @RequestParam("drugPice") double drugPice,
                               HttpServletResponse response){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());
        double price = editQuantity * drugPice;
        Sale sale = new Sale(date,drugnumber,editQuantity,price,hostHolder.getUser().getId());
        if(buyManageService.selectQuantity(drugid)>=editQuantity){
            buyManageService.updateQuantity(editQuantity,drugid);
            buyManageService.addObject(sale);
            return JSONUtil.getObjectJSONString(1,"quantity",buyManageService.selectQuantity(drugid));
        }else {
            return JSONUtil.getStateString(0);
        }
    }
}
