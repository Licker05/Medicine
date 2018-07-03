package com.nowcoder;

import com.Medicine.ToutiaoApplication;
import com.Medicine.model.User;
import com.Medicine.service.UserManageService;
import com.Medicine.service.UserService;
import com.Medicine.utils.JSONUtil;
import com.Medicine.utils.JedisAdapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ToutiaoApplication.class)
public class userTest {
    @Autowired
    UserManageService userManageService;
    @Autowired
    private JedisAdapter jedisAdapter;
    @Test
    public void insertUser(){
        System.out.println(jedisAdapter.get("hello"));
//        User user = new User();
//        for(int i=2;i<10000;i++){
//            user.setName("testuser"+i);
//            user.setSalt(UUID.randomUUID().toString().substring(0,5));
//            user.setPassword(JSONUtil.MD5("testpass1")+user.getSalt());
//            user.setNickname("testuser"+i);
//            user.setHeadurl("http://p8wefufch.bkt.clouddn.com/295660dbcf514ab49bde6f31e6286fad.jpg");
//            user.setPhone("18900379376");
//            user.setEmail("1@qq.com");
//            user.setLevel("普通用户");
//            userManageService.addObject(user);
//        }

    }
}
