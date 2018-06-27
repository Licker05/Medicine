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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ManageController {
    @Autowired
    CategoryManageService categoryManageService;
    @Autowired
    HostHolder hostHolder;
    @RequestMapping(path = {"/Category_selectByPage"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String Category_selectByPage(){
        return "Category_selectByPage";
    }
    @RequestMapping(path = {"/Category_toAdd"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String Category_toAdd(){
        return "Category_toAdd";
    }
    @RequestMapping(path = {"/selectByPage.category"}, method = {RequestMethod.GET, RequestMethod.POST})
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
            if(likeValue.trim().length()>0)
                result = categoryManageService.getAllMesByValue(likeValue,(limit-1),(page-1)*10);
            else
                result = categoryManageService.getAllMes(limit,(page-1)*10);
            return result;
        }else{
            String result = categoryManageService.getAllMes(limit,(page-1)*10);
            return result;
        }
    }
    @RequestMapping(path = {"/deleteInfo.category"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String DeleteById(Model model,
                             @RequestParam("id") int id,
                             HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        if(categoryManageService.DeleteById(id)){
            jsonObject.put("state",1);
            return jsonObject.toJSONString();
        }else{
            jsonObject.put("state",0);
            return jsonObject.toJSONString();
        }
    }

    //addCategory.category
    @RequestMapping(path = {"/addCategory.category"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String addCategory(Model model,
                              @RequestParam("cname") String cname,
                              @RequestParam("description") String description,
                              HttpServletResponse response){
        JSONObject jsonObject  = new JSONObject();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Category category = new Category(cname,df.format(new Date()),description);
        if(hostHolder.getUser()!=null){
            jsonObject.put("isLogin",1);
            categoryManageService.addCategory(category);
        }else{
            jsonObject.put("isLogin",0);
        }

        return jsonObject.toJSONString();
    }
    @RequestMapping(path = {"/editCategory.category"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String EditById(Model model,
                               @RequestParam("cname") String cname,
                               @RequestParam("description") String description,
                               @RequestParam("id") int id,
                               HttpServletResponse response){
        Category category = new Category(id,cname,"none",description);
        JSONObject jsonObject = new JSONObject();
        if(categoryManageService.updateNameAndDesc(category)){
            jsonObject.put("state",1);
            return jsonObject.toJSONString();
        }else{
            jsonObject.put("state",0);
            return jsonObject.toJSONString();
        }

    }
}
