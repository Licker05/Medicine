package com.Medicine.utils;

import com.Medicine.model.Category;
import com.Medicine.model.Drug;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetModelInfoUtil {
    public static Object getModelInfo(String type,HttpServletRequest request){
        try {
            Class typeClass = Class.forName("com.Medicine.model."+type);
            if(typeClass.getSimpleName().equals("Category")){
                return getCategoryInfo(request);
            }else if(typeClass.getSimpleName().equals("Drug")){
                return getDrugInfo(request);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Category getCategoryInfo(HttpServletRequest request){
        int flag = 0, id = 0;
        Category category;
        if(request.getParameter("id")!=null)
            flag=1;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        if(flag==1)
        id = Integer.parseInt(request.getParameter("id"));
        String cname = request.getParameter("cname");
        String description = request.getParameter("description");
        if(flag==1)
            category = new Category(id,cname,df.format(new Date()),description);
        else
            category = new Category(cname,df.format(new Date()),description);
        return category;
    }
    public static Drug getDrugInfo(HttpServletRequest request) {
        int flag = 0 ,drugid = 0;
        Drug drug;
        if(request.getParameter("drugid")!=null)
            flag = 1;
        if(flag==1)
            drugid = Integer.parseInt(request.getParameter("drugid"));
        String drugnumber  = request.getParameter("drugnumber");
        String drugname  = request.getParameter("drugname");
        double drugPice  = Double.parseDouble(request.getParameter("drugPice"));
        int  quantity  = Integer.parseInt(request.getParameter("quantity"));
        String producer  = request.getParameter("producer");
        String categoryname  = request.getParameter("categoryname");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        if(flag==0)
            drug = new Drug(drugid,drugnumber,drugname,drugPice,quantity,df.format(new Date()),producer,categoryname);
        else
            drug = new Drug(drugnumber,drugname,drugPice,quantity,df.format(new Date()),producer,categoryname);
        return drug;
    }
}
