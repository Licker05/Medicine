package com.Medicine.controller;

import com.Medicine.dao.UserDAO;
import com.Medicine.model.Drug;
import com.Medicine.model.User;
import com.Medicine.service.ManageService;
import com.Medicine.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
@Controller
public class UserManageController {
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserManageService userManageService;
    @RequestMapping(path = "/User_toEdit{id}",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView EdiUser(@PathVariable("id") int id,
            HttpServletResponse response){
        try{
            HashMap<String,String> map = new HashMap<String, String>();
            ModelAndView modelAndView = new ModelAndView();
            User user = (User)userManageService.selectById(Class.forName("com.Medicine.model.User"),id);
            modelAndView.setViewName("/User_toEdit");
            modelAndView.addObject("userInfo",user);
            return modelAndView;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
