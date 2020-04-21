package com.payudon.base;

import com.payudon.base.sys.entity.User;
import com.payudon.base.sys.service.UserService;
import com.payudon.base.sys.util.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseApplicationTests {
    @Resource
    UserService userService;
    @Test
    public void contextLoads(){
        User user = new User ();
        user.setAccount ("admin");
        user.setName ("超级管理员");
        user.setPassword (MD5Util.encrypt(user.getAccount (), "123456"));
        try {
            userService.save (user);
        }catch (Exception e){
            e.printStackTrace ();
        }
    }
}
