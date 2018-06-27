package com.Medicine.service;

import com.Medicine.model.Category;

public interface IManageService {
    public String getAllMes(int limit, int offset);
    public boolean DeleteById(int id);
    public String getAllMesByValue(String likeValue,int limit, int offset);

}
