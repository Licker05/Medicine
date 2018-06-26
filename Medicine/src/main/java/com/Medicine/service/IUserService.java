package com.Medicine.service;

import com.Medicine.model.User;

import java.util.Map;

public interface IUserService  {
    public Map<String, Object> register(String username, String password, String cellphone, String email);
    public Map<String, Object> login(String username, String password);
    public String addLoginTicket(int userId);
    public User getUserById(int id);
    public User getUserByName(String name);
    public void logout(String ticket);
}
