package com.Medicine.service;

import com.Medicine.dao.UserDAO;
import com.Medicine.model.User;
import com.Medicine.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManageService extends AbstractManageService{
    @Autowired
    UserDAO userDAO;
    public String updateSelfInfo(User user){
        try{
            userDAO.updateSeleInfo(user);
            return JSONUtil.getJSONString(0, "成功");
        }catch (Exception e){
            e.printStackTrace();
            return JSONUtil.getJSONString(1, "失败");
        }


    }
}
