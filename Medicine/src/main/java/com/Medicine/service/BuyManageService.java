package com.Medicine.service;

import com.Medicine.dao.DrugDAO;
import com.Medicine.dao.SaleDAO;
import com.Medicine.model.HostHolder;
import com.Medicine.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BuyManageService extends AbstractManageService {
    @Autowired
    DrugDAO drugDAO;

    public int selectQuantity(int drugid){
        return  drugDAO.selectQtityById(drugid);
    }
    public void updateQuantity(int editQuantity,int drugid){
        int quantity;
        quantity=drugDAO.selectQtityById(drugid)-editQuantity;
        drugDAO.updateQtityById(quantity,drugid);
    }
}
