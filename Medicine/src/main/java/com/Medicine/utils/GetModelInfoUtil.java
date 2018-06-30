package com.Medicine.utils;

import com.Medicine.dao.UserDAO;
import com.Medicine.model.Category;
import com.Medicine.model.Drug;
import com.Medicine.model.Sale;
import com.Medicine.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class GetModelInfoUtil {
    public static Object getModelInfo(String type,HttpServletRequest request){
        try {
            Class typeClass = Class.forName("com.Medicine.model."+type);
            if(typeClass.getSimpleName().equals("Category")){
                return getCategoryInfo(request);
            }else if(typeClass.getSimpleName().equals("Drug")){
                return getDrugInfo(request);
            }else if(typeClass.getSimpleName().equals("Sale")){
                return getSaleInfo(request);
            }else if(typeClass.getSimpleName().equals("User")){
                return getUserInfo(request);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Category getCategoryInfo(HttpServletRequest request){
        int flag = 0, id = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Category category;
        if(request.getParameter("id")!=null)
            flag=1;
        if(flag==1)
        id = Integer.parseInt(request.getParameter("id"));
        String cname = request.getParameter("cname");
        String description = request.getParameter("description");
        if(flag==1)
            category = new Category(id,cname,sdf.format(new Date()),description);
        else
            category = new Category(cname,sdf.format(new Date()),description);
        return category;
    }
    public static Drug getDrugInfo(HttpServletRequest request) {
        int flag = 0 ,drugid = 0;
        Drug drug;
        if(request.getParameter("drugid")!=null)
            flag = 1;
        if(flag==1)
            drugid = Integer.parseInt(request.getParameter("drugid"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String drugnumber  = request.getParameter("drugnumber");
        String drugname  = request.getParameter("drugname");
        double drugPice  = Double.parseDouble(request.getParameter("drugPice"));
        int  quantity  = Integer.parseInt(request.getParameter("quantity"));
        String producer  = request.getParameter("producer");
        String categoryname  = request.getParameter("categoryname");
        if(flag==1)
            drug = new Drug(drugid,drugnumber,drugname,drugPice,quantity,sdf.format(new Date()),producer,categoryname);
        else
            drug = new Drug(drugnumber,drugname,drugPice,quantity,sdf.format(new Date()),producer,categoryname);
        return drug;
    }
    public static Sale getSaleInfo(HttpServletRequest request){
        int sellrecordnumber  = Integer.parseInt(request.getParameter("sellrecordnumber"));
        String selldate = request.getParameter("selldate");
        String drugnumber  = request.getParameter("drugnumber");
        int sellquantity  = Integer.parseInt(request.getParameter("sellquantity"));
        double sellPrice  = Double.parseDouble(request.getParameter("sellPrice"));
        int userid  = Integer.parseInt(request.getParameter("userid"));
        Sale sale = new Sale(sellrecordnumber,selldate,drugnumber,sellquantity,sellPrice,userid);
        return sale;
    }
    public static User getUserInfo(HttpServletRequest request){
        if(request.getParameter("id")!=null){
            if(request.getParameter("name")==null){
                int id = Integer.parseInt(request.getParameter("id"));
                String newPass = request.getParameter("pass");
                String level = request.getParameter("level");
                User user = new User();
                user.setId(id);
                user.setPassword(newPass);
                user.setLevel(level);
                return user;
            }else{
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String nickname = request.getParameter("nickname");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String level = request.getParameter("level");
                User user = new User(id,name,nickname,phone,email,level);
                return user;
            }

        }else{
            String name = request.getParameter("name");
            String nickname = request.getParameter("nickname");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String level = request.getParameter("level");
            String salt = UUID.randomUUID().toString().substring(0,5);
            String headurl = "http://p8wefufch.bkt.clouddn.com/295660dbcf514ab49bde6f31e6286fad.jpg";
            password = JSONUtil.MD5(password+salt);
            User user = new User(name,nickname,password,salt,headurl,phone,email,level);
            return user;
        }
    }
}
