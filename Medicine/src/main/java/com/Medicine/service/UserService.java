package com.Medicine.service;

import com.Medicine.dao.LoginTicketDAO;
import com.Medicine.dao.UserDAO;
import com.Medicine.model.LoginTicket;
import com.Medicine.utils.JSONUtil;
import com.Medicine.model.User;
import com.Medicine.utils.JedisAdapter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService{
    @Autowired
    UserDAO userDAO;
    @Autowired
    LoginTicketDAO loginTicketDAO;
    @Autowired
    JedisAdapter jedisAdapter;
    public Map<String, Object> register(String username, String password,String cellphone,String email) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }
        if(username.length()<6){
            map.put("msg","用户名必须大于等于6位");
            return map;
        }
        if(username.length()>16){
            map.put("msg","用户名必须小于等于16位");
            return map;
        }
        if(password.length()<6){
            map.put("msg","密码必须大于6位");
            return map;
        }
        User user = userDAO.selectByName(username);
        if (user != null) {
            map.put("msg", "用户名已经被注册");
            return map;
        }

        // 密码强度
        user = new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
        user.setHeadurl(head);
        user.setPassword(JSONUtil.MD5(password+user.getSalt()));
        user.setPhone(cellphone);
        user.setEmail(email);

        if(userDAO.addUser(user)!=0){
            map.put("RegCode",1);
        }else{
            map.put("RegCode",0);
        }
        return map;
    }
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = null;
        if (StringUtils.isBlank(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }
        if(jedisAdapter.exits("userset")==false){
            List<User> userlist = userDAO.selectAll();
            for(User subuser :userlist){
                jedisAdapter.sadd("userset",subuser.getName());
            }
        }else{
            if(jedisAdapter.sismember("userset",username)==false){
                map.put("msg", "该用户名不存在");
                return map;
            }
        }
        user = userDAO.selectByName(username);
        if (!JSONUtil.MD5(password+user.getSalt()).equals(user.getPassword())) {
            map.put("msg", "密码不正确");
            return map;
        }
        if(username.length()<6){
            map.put("msg","用户名必须大于等于6位");
            return map;
        }
        if(username.length()>16){
            map.put("msg","用户名必须小于等于16位");
            return map;
        }
        if(password.length()<6){
            map.put("msg","密码必须大于6位");
            return map;
        }
        map.put("userId", user.getId());
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        return map;
    }
    public String addLoginTicket(int userId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000*3600*24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDAO.addTicket(ticket);
        return ticket.getTicket();
    }
    public User getUserById(int id) {
        return userDAO.selectById(id);
    }
    public User getUserByName(String name) {
        return userDAO.selectByName(name);
    }
    public void logout(String ticket) {
        loginTicketDAO.updateStatus(ticket, 1);
    }
}
