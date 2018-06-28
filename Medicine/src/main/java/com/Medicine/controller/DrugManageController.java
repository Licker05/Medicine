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

}
