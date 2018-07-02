package com.Medicine.controller;

import com.Medicine.dao.UserDAO;
import com.Medicine.model.Drug;
import com.Medicine.model.User;
import com.Medicine.service.ManageService;
import com.Medicine.service.QiniuService;
import com.Medicine.service.UserManageService;
import com.Medicine.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
@Controller
public class UserManageController {
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserManageService userManageService;
    @Autowired
    QiniuService qiniuService;
    @RequestMapping(path = {"/user/info/{userId}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model,
                        @PathVariable("userId") int userId) {
        return "info";
    }

    @RequestMapping(path = {"/updatepass"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String UpdatePass() {
        return "updatepass";
    }

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


    @RequestMapping(path = {"/image"}, method = {RequestMethod.GET})
    @ResponseBody
    public void getImage(@RequestParam("name") String imageName,
                         HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");
            StreamUtils.copy(new FileInputStream(new
                    File(JSONUtil.IMAGE_DIR + imageName)), response.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @RequestMapping(path = {"/inform"},method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String inform(Model model, HttpServletRequest request,
                         HttpServletResponse res){
        String username = request.getParameter("username");
        String nickname = request.getParameter("nickname");
        String headurl = request.getParameter("headurl");
        String cellphone = request.getParameter("cellphone");
        String email = request.getParameter("email");
        User user = new User(username,nickname,headurl,cellphone,email);
        return userManageService.updateSelfInfo(user);

    }

    @RequestMapping(path = {"/uploadImage/"}, method = {RequestMethod.POST})
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = qiniuService.saveImage(file);
            if (fileUrl == null) {
                return JSONUtil.getJSONString(1, "上传图片失败");
            }
            System.out.println(fileUrl);
            return JSONUtil.getJSONString(0, fileUrl);
        } catch (Exception e) {
            return JSONUtil.getJSONString(1, "上传失败");
        }
    }

    @RequestMapping(path = {"/user/updatePass"},method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String updatePass(Model model,
                             @RequestParam("userId") int userId,
                             @RequestParam("oldPass") String oldPass,
                             @RequestParam("newPass") String newPass,
                             HttpServletResponse response){
        User user = userDAO.selectById(userId);
        String salt = user.getSalt();
        if(JSONUtil.MD5(oldPass+salt).equals(user.getPassword())){
            user.setPassword(JSONUtil.MD5(newPass+salt));
            userDAO.updatePassword(user);
            return JSONUtil.getJSONString(0,"修改成功");
        }else{
            return JSONUtil.getJSONString(-1,"旧密码错误");
        }
    }
}
